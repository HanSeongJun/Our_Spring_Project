package backend.service.map;

import backend.entity.dto.map.GuMapInfoDto;
import backend.entity.dto.map.SpotInfoDto;

import java.util.List;

public interface GuService {

    // gu 기준 지도를 제공
    List<GuMapInfoDto> getGuMap();

    // gu의 spot list를 제공
    List<SpotInfoDto> getSpotList(String guCode);

}
