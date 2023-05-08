package backend.map.service;

import backend.map.api.ParticulateMatter;
import backend.map.entity.ApiData;
import backend.map.entity.City;
import backend.map.entity.dto.CityMapInfoDto;
import backend.map.entity.dto.ParticulatePredictInfoDto;
import backend.map.repository.ApiDataRepository;
import backend.map.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiMapServiceImpl implements ApiMapService {

    private final ApiDataRepository apiDataRepository;
    private final ParticulateMatter particulateMatter;


    public ParticulatePredictInfoDto getApiData() {

        // api를 활용해 grade를 판단하는 로직은 다른 thread 에서 하루에 한번씩 데이터가 들어가도록 설정 예정

        Optional<ApiData> apiData = apiDataRepository.findById(0L);
        ApiData getApiData = apiData.get();

        ParticulatePredictInfoDto particulatePredictInfoDto = new ParticulatePredictInfoDto(getApiData().getInformCause(), getApiData.getInformOverall());
        log.info("ApiMapServiceImpl/getApiData/particulatePredictInfoDto = {}", particulatePredictInfoDto);

        return particulatePredictInfoDto;
    }

    @Transactional
    @Scheduled(cron = "0 0 6 * * *" ) //매일 새벽 6시
//    @Scheduled(cron = "30 * * * * *" ) //30초 반복 -> test용으로 사용한다.
    public void updateApiDataParti() throws IOException, ParseException {

        HashMap<String,String> particulatePredictInfo = particulateMatter.extractParticulatePredictInfo("2023-05-08");

        Optional<ApiData> apiData = apiDataRepository.findById(0L);
        ApiData updateApiData = apiData.get();
        ParticulatePredictInfoDto particulatePredictInfoDto = new ParticulatePredictInfoDto(updateApiData.getInformCause(), updateApiData.getInformOverall());
        updateApiData.updateParticul(particulatePredictInfoDto);

        return;

    }
}
