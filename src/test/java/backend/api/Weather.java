package backend.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yaml" +
        ",classpath:/api.yml"
)
public class Weather extends ApiCall{

    private final String serviceKey;
    private final String endPoint;

    private int valuePty = 0;
    private int valueSky = 0;
    private int totalGrade = 0;

    public Weather(@Value("${api.weather.serviceKey}") String serviceKey,
                   @Value("${api.weather.endPoint}") String endPoint){
        this.serviceKey = serviceKey;
        this.endPoint = endPoint;
    }

    @Test
    public void WeatherApiCallTest() throws IOException, ParseException {

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

        JSONObject extraItems= (JSONObject) extraBody.get("items");
        System.out.println("extraItems"  + extraItems);

        JSONArray items = (JSONArray) extraItems.get("item");
        System.out.println("items"  + items);

        for (Object itemInfo : items) {
            Object category = ((JSONObject) itemInfo).get("category");
            Object fcstValue = ((JSONObject) itemInfo).get("fcstValue");
            if(category.toString().equals("SKY") ){
                System.out.println("category : " + category+ "  "+ "fcstValue : " + fcstValue);
                valueSky = Integer.parseInt(fcstValue.toString());

            } else if (category.toString().equals("PTY")) {

                System.out.println("category : " + category + "  " + "fcstValue : " + fcstValue);
                //8단계로 구성됨.
                if (fcstValue.equals("0") || fcstValue.equals("1")) {
                    valuePty = 1;
                } else if (fcstValue.equals("1") || fcstValue.equals("3")) {
                    valuePty = 2;
                } else if (fcstValue.equals("1") || fcstValue.equals("3")) {
                    valuePty = 3;
                } else {
                    valuePty = 4;
                }
                //다른 속성처럼 4단계로 정규화
            }

        }

        totalGrade = ( valueSky + valuePty ) /2;
        System.out.println("기상청 단기예보 서비스의 환경 grade 최종값 : " + totalGrade);


    }

    private String buildUrl() throws UnsupportedEncodingException {

        StringBuilder urlBuilder = new StringBuilder(endPoint); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20230506", "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값 (60 : 서울)*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값 (127 : 서울)*/
        return urlBuilder.toString();
    }
}