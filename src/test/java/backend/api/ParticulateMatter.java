package backend.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yaml" +
        ",classpath:/api.yml"
)
public class ParticulateMatter extends ApiCall{

    private final String serviceKey;
    private final String endPoint;

    public ParticulateMatter(@Value("${api.particulate-matter.serviceKey}") String serviceKey,
                             @Value("${api.particulate-matter.endPoint}") String endPoint){
        this.serviceKey = serviceKey;
        this.endPoint = endPoint;
    }

    @Test
    @DisplayName("시도별 실시간 측정정보 조회 상세기능명세 api call test")
    public void ParticulateMatterApiCallTest() throws IOException {

        String buildUrl = buildUrl();

        StringBuilder sb = getResponse(buildUrl);

        System.out.println(sb.toString());


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
