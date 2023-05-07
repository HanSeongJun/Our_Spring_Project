package backend.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Optional;

@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yaml" +
        ",classpath:/api.yml"
)
public class ParticulateMatter extends ApiCall{

    private final String serviceKey;
    private final String endPoint;

    private int totalGrade = 0;
    private int totalCount = 0;

    public ParticulateMatter(@Value("${api.particulate-matter.serviceKey}") String serviceKey,
                             @Value("${api.particulate-matter.endPoint}") String endPoint){
        this.serviceKey = serviceKey;
        this.endPoint = endPoint;
    }

    @Test
    @DisplayName("시도별 실시간 측정정보 조회 상세기능명세 api call test")
    public void ParticulateMatterApiCallTest() throws IOException, ParseException {


        String buildUrl = buildUrl();

        StringBuilder sb = getResponse(buildUrl);

        System.out.println(sb.toString());
        // JSONParser로 JSONObject로 변환
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());
        System.out.println("jsonObject"  + jsonObject);

        JSONObject extraResponse= (JSONObject) jsonObject.get("response");
        System.out.println("extractResponse"  + extraResponse);

        JSONObject extraBody= (JSONObject) extraResponse.get("body");
        System.out.println("extractBody"  + extraBody);

        JSONArray items = (JSONArray) extraBody.get("items");
        System.out.println("items"  + items);

        for (Object itemInfo : items) {
            Object pm10Grade1 = ((JSONObject) itemInfo).get("pm10Grade");

            try{
                int pm10Grade =  Integer.parseInt((String) pm10Grade1);
                System.out.println("stationName : " + ((JSONObject) itemInfo).get("stationName"));
                System.out.println("dataTime : " + ((JSONObject) itemInfo).get("dataTime"));
                System.out.println("pm10Grade : " + pm10Grade);
                totalGrade += pm10Grade;
                totalCount++;
            }catch ( Exception e){
                System.out.println("error : " + e);
                continue;
            }

        }

        System.out.println("totalCount" + totalCount + "totalGrade : " + totalGrade);
        int gradeResult = totalGrade / totalCount; // 우선은 그냥 나누는 전략을 사용한다.
        System.out.println("최종 api에 사용할 미세먼지 grade : " + gradeResult);


    }

    private String buildUrl() throws UnsupportedEncodingException {

        StringBuilder urlBuilder = new StringBuilder(endPoint); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8")); /*sidoName*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*serviceKey*/
        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /*version*/
        return urlBuilder.toString();
    }

}
