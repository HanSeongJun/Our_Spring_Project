package backend.entity.dto.map;

import backend.entity.map.City;
import lombok.Data;

@Data
public class CityGradeDto {

    private String cityCode;
    private String cityName;
    private int grade;

    public CityGradeDto(){

    }

    public void CityGradeDto(String cityCode, String cityName, int grade){
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.grade = grade;
    }


}
