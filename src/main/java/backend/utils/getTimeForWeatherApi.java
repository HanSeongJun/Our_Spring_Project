package backend.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public class getTimeForWeatherApi {

    public static String getTodayDate(){
        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
        LocalDateTime now = LocalDateTime.now();

        // 현재 날짜 구하기(seoul)
        ZoneId zid = ZoneId.of("Asia/Seoul");
        LocalDate Now = LocalDate.now(zid);

        // 결과 출력

        String year = Integer.toString(Now.getYear());
        String month = Integer.toString(Now.getMonthValue());
        String day = Integer.toString(Now.getDayOfMonth());

        month = changeMonthFormat(month);

        String date  = year.concat(month).concat(day);
        log.info("getTimeForWeatherApi/getTodayDate/{}",date);

        return date;
    }

    public static String getParSearchDate(){
        String tempDate = getTimeForWeatherApi.getTodayDate();

        String searchDate = tempDate.substring(0,4) + "-" + tempDate.substring(4,6) + "-" + tempDate.substring(6,8);
        return searchDate;
    }



    private static String changeMonthFormat(String month) {
        if (month.length() == 1){
            month = "0".concat(month);
        }
        return month;
    }


}
