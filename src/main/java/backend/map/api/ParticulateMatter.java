package backend.map.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import backend.utils.ApiCall;

import java.io.IOException;
import java.util.HashMap;

import static backend.utils.BuildUrl.*;

@Slf4j
@Component
public class ParticulateMatter extends ApiCall {

    private final String serviceKey;
    private final String endPoint;
    private final String predictDataEndPoint;

    private int totalGrade;
    private int totalCount;

    public ParticulateMatter(@Value("${api.particulate-matter.serviceKey}") String serviceKey,
                             @Value("${api.particulate-matter.endPoint}") String endPoint,
                             @Value("${api.particulate-matter.predict.endPoint}") String predictDataEndPoint){
        this.serviceKey = serviceKey;
        this.endPoint = endPoint;
        this.predictDataEndPoint = predictDataEndPoint;
    }

    public int extractParticulateCityGrade(String sidoName) throws IOException, ParseException {

        String buildUrl = buildUrl(serviceKey, endPoint, sidoName);
        StringBuilder response = getResponse(buildUrl);

        log.info("ParticulateMatter/extractParticulateCityGrade/response = {}" + response.toString());

        // JSONParser로 JSONObject로 변환
        JSONArray items = getJsonArray(response);

        for (Object itemInfo : items) {
            Object pm10Grade1 = ((JSONObject) itemInfo).get("pm10Grade");

            try{
                int pm10Grade =  Integer.parseInt((String) pm10Grade1);
                log.info("ParticulateMatter/extractParticulateCityGrade/stationName = {}", ((JSONObject) itemInfo).get("stationName"));
                log.info("ParticulateMatter/extractParticulateCityGrade/dataTime = {}",  ((JSONObject) itemInfo).get("dataTime"));
                log.info("ParticulateMatter/extractParticulateCityGrade/pm10Grade = {}", pm10Grade);

                totalGrade += pm10Grade;
                totalCount++;
            }catch ( Exception e){
                log.error("ParticulateMatter/extractParticulateCityGrade/error = {}",e);
            }

        }

        log.info("미세먼지 측정 서비스의 grade 최종값 : {}",totalGrade/totalCount);

        return totalGrade/totalCount;

    }

    public HashMap<String, String> extractParticulatePredictInfo() throws IOException, ParseException {

        String buildUrl = buildPredictDataUrl(serviceKey, predictDataEndPoint);
        StringBuilder response = getResponse(buildUrl);

        // JSONParser로 JSONObject로 변환
        JSONArray items = getJsonArray(response);

        //가장 마지막 데이터를 활용
        Object itemInfo = items.get(items.size()-1);

        Object informCauseData = ((JSONObject) itemInfo).get("informCause");
        Object informOverallData = ((JSONObject) itemInfo).get("informOverall");

        String informCause = "";
        String informOverall ="";

        try{
            informCause =  (String) informCauseData;
            informOverall =  (String) informOverallData;
            log.info("ParticulateMatter/extractParticulatePredictInfo/informCause = {} " , informCause);
            log.info("ParticulateMatter/extractParticulatePredictInfo/informOverall = {} " , informOverall);
        }catch ( Exception e) {
            log.error("ParticulateMatter/extractParticulateCityGrade/error = {}",e);
        }

        HashMap<String, String> ParticulatePredictInfo = new HashMap<>();
        ParticulatePredictInfo.put("informCause",informCause);
        ParticulatePredictInfo.put("informOverall",informOverall);

        log.info("ParticulateMatter/extractParticulatePredictInfo/ParticulatePredictInfo = {} " , ParticulatePredictInfo);

        return ParticulatePredictInfo;
    }

    private static JSONArray getJsonArray(StringBuilder response) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
        JSONObject extraResponse= (JSONObject) jsonObject.get("response");
        JSONObject extraBody= (JSONObject) extraResponse.get("body");
        JSONArray items = (JSONArray) extraBody.get("items");
        return items;
    }

}
