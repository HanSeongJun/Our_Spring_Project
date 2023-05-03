package backend.service.map;

import backend.entity.dto.map.CityMapInfoDto;
import backend.entity.dto.map.GuMapInfoDto;
import backend.entity.map.City;
import backend.entity.map.Gu;
import backend.repository.map.GuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GuServiceImpl implements GuService{

    private final GuRepository guRepository;

    @Override
    public List<GuMapInfoDto> getGuMap() {

        List<Gu> guList = guRepository.findAll();
        List<GuMapInfoDto> GuMapInfoDtoList = new ArrayList<>();
        for(int i=0; i<guList.size(); i++){
            Gu gu = guList.get(0);
            GuMapInfoDto guMapInfoDto = new GuMapInfoDto(gu.getGuCode(), gu.getGuName());
            GuMapInfoDtoList.add(guMapInfoDto);
        }
        log.info("CityServiceImpl/getCityMap/guMapInfoDtoList = {}", GuMapInfoDtoList);

        return GuMapInfoDtoList;

    }
}
