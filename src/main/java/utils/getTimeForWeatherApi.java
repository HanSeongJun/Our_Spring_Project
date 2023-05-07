package utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public class getTimeForWeatherApi {

    public static String getHourTimeCode(){
        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDateTime now = LocalDateTime.now();

        // 현재 날짜 구하기(seoul)
        ZoneId zid = ZoneId.of("Asia/Seoul");
        LocalDate Now = LocalDate.now(zid);

        // 결과 출력
        String hour = Now.toString().substring(11,13);
        log.info("getTimeForWeatherApi/Now = {}"+ hour); //2022-02-20T16:00:26.857433+09:00[Asia/Seoul]

        return hour;
    }

}
