package backend.service.map;

import backend.entity.dto.map.CityMapInfoDto;
import backend.entity.map.City;
import backend.repository.map.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public List<CityMapInfoDto> getCityMap() {

        // api를 활용해 grade를 판단하는 로직은 다른 thread 에서 하루에 한번씩 데이터가 들어가도록 설정 예정

        List<City> cityList = cityRepository.findAll();
        List<CityMapInfoDto> cityMapInfoDtoList = new ArrayList<>();
        for(int i=0; i<cityList.size(); i++){
            City city = cityList.get(0);
            CityMapInfoDto cityMapInfoDto = new CityMapInfoDto(city.getCityCode(), city.getCityName(), city.getGrade());
            cityMapInfoDtoList.add(cityMapInfoDto);
        }
        log.info("CityServiceImpl/getCityMap/cityMapInfoDtoList = {}", cityMapInfoDtoList);

        return cityMapInfoDtoList;
    }
}
