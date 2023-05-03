package backend.entity.dto.map;

import lombok.Data;

@Data
public class CityMapInfoDto {

    private String cityCode;
    private String cityName;
    private int grade;

    public CityMapInfoDto(){

    }

    public CityMapInfoDto(String cityCode, String cityName, int grade) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.grade = grade;
    }


}
