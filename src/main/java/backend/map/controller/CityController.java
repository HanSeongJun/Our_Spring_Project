package backend.map.controller;

import backend.map.entity.dto.ApiInfoDto;
import backend.map.entity.dto.CityMapInfoDto;
import backend.map.service.ApiMapServiceImpl;
import backend.map.service.CityServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
@Slf4j
public class CityController {

    private final CityServiceImpl cityService;
    private final ApiMapServiceImpl apiMapService;

    @GetMapping("/citymap")
    public HashMap<String, HashMap> cityMap(){
        List<CityMapInfoDto> cityMap = cityService.getCityMap();
        ApiInfoDto apiData = apiMapService.getApiData();

        HashMap<String , List> cityMapResult = new HashMap<>();
        HashMap<String, ApiInfoDto> apiDataResult = new HashMap<>();
        cityMapResult.put("city_data", cityMap);
        apiDataResult.put("api_data", apiData);

        HashMap<String , HashMap> Result = new HashMap<>();
        Result.put("city_data", cityMapResult);
        Result.put("api_data", apiDataResult);

        return Result;
    }

}
