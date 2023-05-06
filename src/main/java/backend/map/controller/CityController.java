package backend.map.controller;

import backend.map.entity.dto.CityMapInfoDto;
import backend.map.service.CityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class CityController {

    private final CityServiceImpl cityService;

    @GetMapping("/citymap")
    public HashMap<String, List> cityMap(){
        List<CityMapInfoDto> cityMap = cityService.getCityMap();
        HashMap<String , List> cityMapResult = new HashMap<>();
        cityMapResult.put("data", cityMap);

        return cityMapResult;
    }

}
