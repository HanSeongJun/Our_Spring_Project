package backend.map.controller;

import backend.map.entity.dto.GuMapInfoDto;
import backend.map.entity.dto.SpotInfoDto;
import backend.map.service.GuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class GuController {

    private final GuServiceImpl guService;

    @GetMapping("/gumap")
    public HashMap<String, List>  guMap(){
        List<GuMapInfoDto> guMap = guService.getGuMap();
        HashMap<String, List> guMapResult = new HashMap<>();
        guMapResult.put("data",guMap);

        return guMapResult;
    }

    @GetMapping("/spotlist")
    public HashMap<String, List> guSpotList(@RequestParam String guCode){
        List<SpotInfoDto> spotInfoDtoList = guService.getSpotList(guCode);
        HashMap<String, List> spotListResult = new HashMap<>();
        spotListResult.put("data",spotInfoDtoList);

        return spotListResult;
    }



}
