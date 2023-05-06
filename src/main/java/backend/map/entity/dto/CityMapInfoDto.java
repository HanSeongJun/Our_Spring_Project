package backend.map.entity.dto;

import lombok.Data;

@Data
public class CityMapInfoDto {

    private String cityCode;
    private String cityName;
    private int grade;
    private String vector;

    public CityMapInfoDto(){

    }

    public CityMapInfoDto(String cityCode, String cityName, int grade, String vector) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.grade = grade;
        this.vector = vector;
    }


}
