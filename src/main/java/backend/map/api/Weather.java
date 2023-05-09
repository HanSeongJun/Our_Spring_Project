package backend.map.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static backend.utils.ApiCall.getResponse;
import static backend.utils.BuildUrl.buildUrl;

@Component
@Slf4j
public class Weather {

    private final String serviceKey;
    private final String endPoint;

    private int valuePty = 0;
    private int valueSky = 0;
    private int valueRealPty = 0;
    private int totalGrade = 0;

    private String informSky;
    private String informPty;


    public Weather(@Value("${api.weather.serviceKey}") String serviceKey,
                             @Value("${api.weather.endPoint}") String endPoint){
        this.serviceKey = serviceKey;
        this.endPoint = endPoint;
    }

    public int extractWeatherCityGrade(String xCode, String yCode) throws IOException, ParseException {

        extractWeather(xCode,yCode);

        totalGrade = ( valueSky + valuePty ) /2;
        log.info("기상청 단기예보 서비스의 환경 grade 최종값 : {}",totalGrade);

        return totalGrade;

    }

    private void normalizePTY(Object fcstValue) {

        //8단계로 구성됨.
        if (fcstValue.equals("0") || fcstValue.equals("1")) {
            this.valuePty = 1;
        } else if (fcstValue.equals("2") || fcstValue.equals("3")) {
            this.valuePty = 2;
        } else if (fcstValue.equals("4") || fcstValue.equals("5")) {
            this.valuePty = 3;
        } else {//6 or 7일때
            this.valuePty = 4;
        }
    }

    public HashMap<String, String> extractWeatherState(String xCode, String yCode) throws IOException, ParseException {

        log.info("Weather/extractWeatherState/start");

        extractWeather(xCode, yCode);

        if(valueSky == 1 ||valueSky == 2  ){
            this.informSky = "맑음";
        }else if(valueSky == 3){
            this.informSky = "구름맑음";
        }else{
            this.informSky = "흐림";
        }

        if(valueRealPty == 0 ){
            this.informPty = "없음";
        }else if(valueRealPty == 1){
            this.informPty = "비";
        }else if(valueRealPty == 2){
            this.informPty = "비/눈";
        }else if(valueRealPty == 3){
            this.informPty = "눈";
        }else if(valueRealPty == 4 || valueRealPty == 5){
            this.informPty = "빗방울";
        }else if(valueRealPty == 6){
            this.informPty = "빗방울눈날림";
        }else if(valueRealPty == 7){
            this.informPty = "눈날림";
        }

        HashMap<String, String> weatherState = new HashMap<>();
        weatherState.put("informSky",informSky);
        weatherState.put("informPty",informPty);

        log.info("Weather/extractWeatherState/done");

        return weatherState;


    }

    public void extractWeather(String xCode, String yCode) throws IOException, ParseException {
        String buildUrl = buildUrl(serviceKey, endPoint, "60", "120");
        StringBuilder response = getResponse(buildUrl);

        log.info("Weather/extractWeatherCityGrade/response = {}" + response.toString());

        // JSONParser로 JSONObject로 변환
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

        JSONArray items = getJsonArray(jsonObject);

        for (Object itemInfo : items) {
            Object category = ((JSONObject) itemInfo).get("category");
            Object fcstValue = ((JSONObject) itemInfo).get("fcstValue");
            if(category.toString().equals("SKY") ){
                valueSky = Integer.parseInt(fcstValue.toString());
            } else if (category.toString().equals("PTY")) {
                //다른 속성처럼 4단계로 정규화
                normalizePTY(fcstValue);
                valueRealPty = Integer.parseInt((String)fcstValue);
            }

        }
    }

    private static JSONArray getJsonArray(JSONObject jsonObject) {
        JSONObject extraResponse= (JSONObject) jsonObject.get("response");
        JSONObject extraBody= (JSONObject) extraResponse.get("body");
        JSONObject extraItems= (JSONObject) extraBody.get("items");
        JSONArray items = (JSONArray) extraItems.get("item");
        return items;
    }


}
