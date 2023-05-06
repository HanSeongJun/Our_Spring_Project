package backend.map.service;

import backend.map.entity.dto.GuMapInfoDto;
import backend.map.entity.dto.SpotInfoDto;

import java.util.List;

public interface GuService {

    // gu 기준 지도를 제공
    List<GuMapInfoDto> getGuMap();

    // gu의 spot list를 제공
    List<SpotInfoDto> getSpotList(String guCode);

}
