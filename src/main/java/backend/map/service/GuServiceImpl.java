package backend.map.service;

import backend.map.entity.dto.GuMapInfoDto;
import backend.map.entity.dto.SpotInfoDto;
import backend.map.entity.Gu;
import backend.map.entity.Spot;
import backend.map.repository.GuRepository;
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
public class GuServiceImpl implements GuService{

    private final GuRepository guRepository;

    @Override
    public List<GuMapInfoDto> getGuMap() {

        List<Gu> guList = guRepository.findAll();
        List<GuMapInfoDto> guMapInfoDtoList = new ArrayList<>();
        for(int i=0; i<guList.size(); i++){
            Gu gu = guList.get(1);
            GuMapInfoDto guMapInfoDto = new GuMapInfoDto(gu.getGuCode(), gu.getGuName());
            guMapInfoDtoList.add(guMapInfoDto);
        }
        log.info("CityServiceImpl/getCityMap/guMapInfoDtoList = {}", guMapInfoDtoList);

        return guMapInfoDtoList;

    }

    @Override
    public List<SpotInfoDto> getSpotList(String guCode) {

        Optional<Gu> gu = guRepository.findByGuCode(guCode);
        List<Spot> spotlist = gu.get().getSpotlist();
        log.info("CityServiceImpl/getSpotList/spotlist = {}", spotlist);
        List<SpotInfoDto> spotInfoDtoList = new ArrayList<>();
        for(int i=0; i<spotlist.size(); i++){
            Spot spot = spotlist.get(i);
            SpotInfoDto spotInfoDto = new SpotInfoDto(spot.getSpotName(), spot.getComment());
            spotInfoDtoList.add(spotInfoDto);
        }
        log.info("CityServiceImpl/getSpotList/spotInfoDtoList = {}", spotInfoDtoList);

        return spotInfoDtoList;
    }


}
