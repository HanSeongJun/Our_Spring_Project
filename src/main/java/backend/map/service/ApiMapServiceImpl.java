package backend.map.service;

import backend.map.api.ParticulateMatter;
import backend.map.api.Weather;
import backend.map.entity.ApiData;
import backend.map.entity.City;
import backend.map.entity.dto.ApiInfoDto;
import backend.map.entity.dto.CityMapInfoDto;
import backend.map.entity.dto.ParticulatePredictInfoDto;
import backend.map.entity.dto.WeatherInfoDto;
import backend.map.repository.ApiDataRepository;
import backend.map.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;


@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiMapServiceImpl implements ApiMapService {

    private final CityRepository cityRepository;
    private final ApiDataRepository apiDataRepository;
    private final ParticulateMatter particulateMatter;
    private final Weather weather;


    public ApiInfoDto getApiData() {

        List<ApiData> apiData = apiDataRepository.findAll();
        ApiData getApiData = apiData.get(0);

        ParticulatePredictInfoDto particulatePredictInfoDto = new ParticulatePredictInfoDto(getApiData.getInformCause(), getApiData.getInformOverall());
        log.info("ApiMapServiceImpl/getApiData/particulatePredictInfoDto = {}", particulatePredictInfoDto);

        WeatherInfoDto weatherInfoDto = new WeatherInfoDto(getApiData.getInformSky(), getApiData.getInformPty());
        log.info("ApiMapServiceImpl/getApiData/weatherInfoDto = {}", weatherInfoDto);

        ApiInfoDto apiInfoDto = new ApiInfoDto(particulatePredictInfoDto,weatherInfoDto);
        log.info("ApiMapServiceImpl/getApiData/apiInfoDto = {}", apiInfoDto);

        return apiInfoDto;
    }

    @Transactional
    @Scheduled(cron = "0 0 6 * * *" ) //매일 새벽 6시
//    @Scheduled(cron = "10 * * * * *" ) //10초 반복 -> test용으로 사용한다.
    public void updateApiDataParti() throws IOException, ParseException {


        HashMap<String,String> particulatePredictInfo = particulateMatter.extractParticulatePredictInfo();

        List<ApiData> apiData = apiDataRepository.findAll();
        ApiData updateApiData = apiData.get(0);

        ParticulatePredictInfoDto particulatePredictInfoDto = new ParticulatePredictInfoDto(particulatePredictInfo.get("informCause"),particulatePredictInfo.get("informOverall"));
        updateApiData.updateParticul(particulatePredictInfoDto);

    }

    @Transactional
    @Scheduled(cron = "0 0 6 * * *" )
//    @Scheduled(cron = "10 * * * * *" ) //10초 반복 -> test용으로 사용한다.
    public void updateApiDataWeather() throws IOException, ParseException {

        String informSky = "";
        String informPty = "";

        List<City> cityList = cityRepository.findAll();
        HashMap<String, String> weatherDataForAllCity = getWeatherDataForAllCity(informSky, informPty, cityList);

        List<ApiData> apiData = apiDataRepository.findAll();
        ApiData updateApiData = apiData.get(0);

        WeatherInfoDto weatherInfoDto = new WeatherInfoDto(weatherDataForAllCity.get("informSky"),weatherDataForAllCity.get("informPty"));
        updateApiData.updateWeather(weatherInfoDto);

    }

    private HashMap<String, String> getWeatherDataForAllCity(String informSky, String informPty, List<City> cityList) throws IOException, ParseException {
        for(int i = 0; i< cityList.size(); i++){
            City city = cityList.get(i);
            String xCode = city.getXCode();
            String yCode = city.getXCode();
            String cityName = city.getCityName();
            HashMap<String, String> weatherState = weather.extractWeatherState(xCode, yCode);

            String informSkyTemp  = cityName + " : " + weatherState.get("informSky") + " ";
            String informPtyTemp =  cityName + " : "  + weatherState.get("informPty") + " ";
            informSky = informSky.concat(informSkyTemp);
            informPty = informPty.concat(informPtyTemp);
        }

        HashMap<String, String> weatherDataForAllCity = new HashMap<>();
        weatherDataForAllCity.put("informSky", informSky);
        weatherDataForAllCity.put("informPty", informPty);

        return weatherDataForAllCity;

    }
}
