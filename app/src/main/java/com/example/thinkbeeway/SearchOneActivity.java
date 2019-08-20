package com.example.thinkbeeway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thinkbeeway.vo.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchOneActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private EditText searchText;

    private List<Integer> distanceList;
    private List<Place> placeList;
    private ListView placeView;
    private PlaceAdapter placeAdapter;
    private double lat; // 현재 위치 위도
    private double lon; // 현재 위치 경도
    private int fromWhat;   // 출발지에서 누른건지 도착지에서 누른건지 체크
    private List<Map<String,Double>> locationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_one);

        searchText = findViewById(R.id.searchText);
        searchText.setOnEditorActionListener(this);

        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat",0);
        lon = intent.getDoubleExtra("lon",0);
        fromWhat = intent.getIntExtra("startEnd",-1);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        if(searchText.getText().toString().trim().equals("")) return false;

        // 데이터 생성
        distanceList = new ArrayList<>();
        placeList = new ArrayList<>();
        searchPlace();
//        calcDistance();

        // 리스트뷰와 어댑터 연결
        placeView = findViewById(R.id.placeView);
        placeAdapter = new PlaceAdapter(placeList, distanceList,this);
        placeView.setAdapter(placeAdapter);

        // 아이템에 클릭 이벤트 처리
        placeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // adapterView : 아이템이 포함된 부모 뷰: 리스트뷰
            // view : 클릭된 아이템
            // i : 선택된 항목의 번호 ( position )
            // l : 일반적으로 position과 같은 개념 ( id )
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 클릭하면 선택한 걸로 반환하고 액티비티 끝내기
                Place place = placeList.get(i);

                Intent intent = new Intent();
                intent.putExtra("name",place.getName());
                intent.putExtra("lat",place.getNoorLat());
                intent.putExtra("lon",place.getNoorLon());
                intent.putExtra("startEnd",fromWhat);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        return false;
    }

    public void searchPlace() {

        // 장소 검색 스레드
        Thread t1 = null;
        try {
            t1 = new SearchPlaceThread(getString(R.string.searchPlaceURL)+"?version=1"+
                    "&searchKeyword="+ URLEncoder.encode(searchText.getText().toString().trim(),getString(R.string.encoding))+
                    "&resCoorType=WGS84GEO"+
                    "&searchType=all"+
                    "&appKey="+getString(R.string.tMapKey));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calcDistance() {
        // 거리 계산 스레드
        if(lat !=0 && lon != 0 && locationList != null){
            Thread t2 = null;
            try {
                t2 = new DistanceHandler("http://192.168.30.244:8080/api/map/distanceAll"+
                        "?locationList="+ URLEncoder.encode(new JSONArray(locationList).toString(),getString(R.string.encoding)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 장소 검색 스레드
    private class SearchPlaceThread extends Thread {
        String url;
        String result;
        public SearchPlaceThread(String url)
        {
            this.url  = url;
        }

        public void run() {
            try{
                HttpURLConnection conn =
                        (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true); // 서버에서 오는 데이터 수신

                int status = conn.getResponseCode();

                InputStream is ;
                if(status == HttpURLConnection.HTTP_NO_CONTENT){
                    placeList.add(new Place("검색 결과가 없어요"));
                    return;
                }
                else if(status != HttpURLConnection.HTTP_OK){
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
                            Toast.makeText(getApplicationContext(),"장소 검색"+ result, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                // 에러가 발생하지 않은 경우
                JSONObject jsonObject = new JSONObject(result);
                JSONObject searchPoinInfo = jsonObject.getJSONObject("searchPoiInfo");
                JSONObject pois = searchPoinInfo.getJSONObject("pois");
                final JSONArray poi = pois.getJSONArray("poi");

               locationList = new ArrayList<>();

                JSONObject temp ;

                // 출발지 먼저 입력하기
                Map<String, Double> start = new HashMap<>();
                start.put("lat",lat);
                start.put("lon",lon);
                locationList.add(start);

                Map<String, Double> location;
                for(int i=0; i<poi.length(); i++){
                    try {
                        temp = (JSONObject) poi.get(i);
                        location = new HashMap<>();
                        location.put("lat",temp.getDouble("noorLat"));
                        location.put("lon",temp.getDouble("noorLon"));
                        locationList.add(location);
                        placeList.add(new Place(temp.getString("id"), temp.getString("name"), temp.getString("telNo"),
                                temp.getDouble("noorLat"), temp.getDouble("noorLon"),
                                temp.getString("upperAddrName"), temp.getString("middleAddrName"), temp.getString("lowerAddrName"),
                                temp.getString("detailAddrName"), temp.getString("roadName"),temp.getString("rpFlag")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception e )
            {
                e.printStackTrace();
            }
        }
    }

    // 거리 계산 클래스
    private class DistanceHandler extends Thread {
        String url;
        String result;
        public DistanceHandler(String url)
        {
            this.url  = url;
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
                            Toast.makeText(getApplicationContext(), "거리 계산"+result, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                // 에러가 발생하지 않은 경우
                JSONArray jsonArray  = new JSONArray(result);
                for(int i=0; i<jsonArray.length(); i++){
                    distanceList.add((Integer) jsonArray.get(i));
                }
            }
            catch(Exception e )
            {
                e.printStackTrace();
            }
        }
    }
}
