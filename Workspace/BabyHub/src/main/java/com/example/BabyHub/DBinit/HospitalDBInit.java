package com.example.BabyHub.DBinit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BabyHub.Entities.City;
import com.example.BabyHub.Entities.Hospital;
import com.example.BabyHub.repository.CityRepository;
import com.example.BabyHub.repository.HospitalRepository;
import com.example.BabyHub.DBinit.CityCodeInit;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Supplier;


@RestController
public class HospitalDBInit {
	int id = 0;
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private CityRepository cityRepository;
	
	private JSONObject getJson(int page, String sggCd) {
		StringBuffer result = new StringBuffer();
       
        try {
            String apiUrl = "https://apis.data.go.kr/1790387/orglist2/getOrgList2?" +
                    "ServiceKey=2Z0GjJZfRTpCTKHQgVOPt4gpS%2BU4S%2FkQicxfh7J6jj7J6cMtOj8iFIaC25CW6k1qIHGkIb6KAXEpILrgK1zC0Q%3D%3D"+
                    "&pageNo="+ Integer.toString(page) +
                    "&numOfRows=100" +
                    "&brtcCd=1100000000" + 
                    "&sggCd=" + sggCd;
	            URL url = new URL(apiUrl);
	            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	            
	            try (InputStream in = urlConnection.getInputStream();
	                 BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
	                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"))) {
	                
	                String returnLine;
	                while ((returnLine = bufferedReader.readLine()) != null) {
	                    result.append(returnLine);
	                }
	                urlConnection.disconnect();
	                String tmp = result.toString();
	                
	                JSONObject jsonObject = XML.toJSONObject(tmp.replaceAll("[&]",""));
	                return jsonObject;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	            }
        
	}
	private int getMaxPage(JSONObject obj) {
		int maxPage;
		JSONObject body;
		if(obj.has("response")) {
			body = obj.getJSONObject("response").getJSONObject("body");
			maxPage  = body.getInt("maxPage");
		}
		else {
			body = obj.getJSONObject("body");
			maxPage  = body.getInt("maxPage");
		}
		return maxPage;
	}
	private JSONArray parseJson(JSONObject obj) {
		JSONArray itemList;
		JSONObject body;
		if(obj.has("response")) {
			body = obj.getJSONObject("response").getJSONObject("body");
			if(body.has("items")) {
				itemList  = body.getJSONObject("items").getJSONArray("item");
			} else return null;
		}
		else {
			body =obj.getJSONObject("body");
			if(body.has("items")) {
				itemList =body.getJSONObject("items").getJSONArray("item");
			} else return null;
		}
		return itemList;
	}
    
	private void insertData(int cd, JSONArray data) {
		JSONObject set;
		for(int i=0; i<data.length();i++) {
			id++;
			set = data.getJSONObject(i);
			Hospital hospitalTmp = new Hospital(id, cd, set.getString("orgnm"), set.getString("orgTlno"), set.getString("orgAddr"), set.getInt("expnYmd"));
			hospitalRepository.save(hospitalTmp);
		}
	}
	
	private String[] getCd() {
		String[] cd = new String[24];
		for(int k= 1; k < 25; k++) {
				City cityTmp = cityRepository.findById(k).orElseThrow(new Supplier<IllegalArgumentException>() {
					@Override
					public IllegalArgumentException get() {
					return new IllegalArgumentException("해당 유저는 없습니다. " + id );}
				});
				cd[k-1] = Integer.toString(cityTmp.getCd());		
				}
		return cd;
	}
	
	@GetMapping("/jsonapi")
    public void callApiWithJson() { 
		String[] cd = getCd();
		int maxPage;
		for(int i = 0; i < 24; i++) {
			JSONObject maxPageCheck = getJson(1,cd[i]);
			maxPage = getMaxPage(maxPageCheck);
			for(int j=1;j<=maxPage;j++) {
				JSONObject rawData = getJson(j,cd[i]);
				JSONArray data = parseJson(rawData);
				insertData(i+1, data);
			}
			}
	}
	
}

