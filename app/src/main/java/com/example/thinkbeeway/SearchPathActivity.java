package com.example.thinkbeeway;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchPathActivity extends AppCompatActivity {

    public static final int SEARCH_ONE_ACTIVITY = 1;
    public static final int START_TEXT = 10;
    public static final int END_TEXT = 20;
    private TextView startText;
    private TextView endText;
    private double lat; // 현재 위치 위도
    private double lon; // 현재 위치 경도

    // 출발 도착 위도 경도 설정
    private double startLat = 0;    // 출발지
    private double startLon = 0;
    private double endLat = 0;      // 도착지
    private double endLon= 0;

    private String startName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_path);

        startText = findViewById(R.id.startText);
        endText = findViewById(R.id.endText);

        Intent intent = getIntent();
        // gps가 켜져 있으면 현재 위치를 출발지로 설정
        lat = startLat = intent.getDoubleExtra("startLat",0);
        lon = startLon = intent.getDoubleExtra("startLon",0);

        Thread geoCoding = null;

        // 현재 위도 경도가 있다면 현 위치 주소로 변환
        if(lat != 0 && lon != 0){
            geoCoding = new ReverseGeocodingHandler(lat,lon);
            geoCoding.start();
        }

        endLat = intent.getDoubleExtra("endLat",0);
        endLon = intent.getDoubleExtra("endLon",0);
        String endName = intent.getStringExtra("endName");

        // 장소 검색을 통해 목적지를 정했을 때
        if (endName != null) {
            endText.setText(endName);
        }

        // 출발지와 도착지 이미 다 입력 되어 있으면 바로 검색
        if(geoCoding != null && endName != null){
            try {
                geoCoding.join();
                findPath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 경로 찾았으면 액티비티 끝내고 메인 액티비티로 돌아가기
    public void findPath() {

        Intent intent = new Intent();
        intent.putExtra("startLat",startLat);
        intent.putExtra("startLon",startLon);
        String start = startText.getText().toString();
        intent.putExtra("startName", startName);
        intent.putExtra("endLat",endLat);
        intent.putExtra("endLon",endLon);
        intent.putExtra("endName",endText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }

    // 출발지 도착지 텍스트뷰 터치하면 주소, 장소 검색
    public void searchPlace(View view) {
        Intent intent  = new Intent(this,SearchOneActivity.class);
        if(lat != 0 && lon !=0){
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
        }
        // 출발지, 도착지에 따라 다른 값 전송
        if (view.getId() == R.id.startText){
            intent.putExtra("startEnd",START_TEXT);
        } else {
            intent.putExtra("startEnd",END_TEXT);
        }
        startActivityForResult(intent,SEARCH_ONE_ACTIVITY);
    }

    // 장소 검색 결과 받아오기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(START_TEXT == data.getIntExtra("startEnd",9999)){

                startName = data.getStringExtra("name");
                startText.setText(startName);
                startLat = data.getDoubleExtra("lat",0);
                startLon = data.getDoubleExtra("lon",0);
                // 도착지가 이미 입력되어 있으면 바로 검색
                if(endText.getText().toString() != null && !endText.getText().toString().trim().equals("")){
                    findPath();
                }
            }
            else if(END_TEXT == data.getIntExtra("startEnd",9999)){
                endText.setText(data.getStringExtra("name"));
                endLat = data.getDoubleExtra("lat",0);
                endLon = data.getDoubleExtra("lon",0);
                // 출발지가 이미 입력되어 있으면 바로 검색
                if(startText.getText().toString() != null && !startText.getText().toString().trim().equals("")){
                    findPath();
                }
            }
        }
    }

    // 현 위치 위도 경도 받아서 주소로 변환해주는 것
    public class ReverseGeocodingHandler extends Thread {
        String url;
        String result;
        public ReverseGeocodingHandler(double lat, double lng)
        {
            this.url  = getString(R.string.reverseGeoCodingURL)+"?version=1"+
                        "&lat="+lat+"&lon="+lng+
                        "&appKey="+getString(R.string.tMapKey);
        }

        public void run() {
            try{
                HttpURLConnection conn =
                        (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true); // 서버에서 오는 데이터 수신

                int status = conn.getResponseCode();
                InputStream is ;
                if(status != HttpURLConnection.HTTP_OK){
                    is = conn.getErrorStream();
                }
                else {
                    is = conn.getInputStream();
                }
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is,getString(R.string.encoding)) );

                StringBuffer buffer = new StringBuffer();
                String line = br.readLine();
                while(line != null){
                    buffer.append(line);
                    line = br.readLine();
                }
                br.close();
                result = buffer.toString();
                Log.d("DATA",result);
                if(status != HttpURLConnection.HTTP_OK) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                // 에러가 발생하지 않은 경우
                JSONObject json = new JSONObject(result);
                final JSONObject addressInfo = json.getJSONObject("addressInfo");
                startName = addressInfo.getString("fullAddress");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startText.setText(startName);
                    }
                });
            }
            catch(Exception e ){e.printStackTrace();}
        }
    }
}
