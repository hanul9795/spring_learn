package com.example.BabyHub.DBinit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BabyHub.Entities.City;
import com.example.BabyHub.repository.CityRepository;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



@RestController
public class CityCodeInit {
	
	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping("/cityDBInit")
    public void callApiWithJson() {
        StringBuffer result = new StringBuffer();
        String jsonPrintString = null;
        try {
            String apiUrl = 	"https://apis.data.go.kr/1790387/orglist2/getCondSggCd2?"
            					 	+  "ServiceKey=2Z0GjJZfRTpCTKHQgVOPt4gpS%2BU4S%2FkQicxfh7J6jj7J6cMtOj8iFIaC25CW6k1qIHGkIb6KAXEpILrgK1zC0Q%3D%3D&"
            						+  "brtcCd=1100000000";
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            JSONObject jsonObject = XML.toJSONObject(result.toString());
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject body = response.getJSONObject("body");
            JSONObject items = body.getJSONObject("items");
            JSONArray item = items.getJSONArray("item");
            JSONObject tmp;
            
            
            
            for(int i = 0; i < item.length();i++) {
            	tmp = (JSONObject)item.opt(i);
            	City cityTmp = new City(i, tmp.getString("cdNm"), tmp.getInt("cd"));
            	cityRepository.save(cityTmp);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}