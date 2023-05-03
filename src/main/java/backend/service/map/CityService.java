package backend.service.map;

import backend.entity.dto.map.CityMapInfoDto;

import java.util.List;

public interface CityService {

    // city 기준 지도를 제공
    List<CityMapInfoDto> getCityMap();


}
