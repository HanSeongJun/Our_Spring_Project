package backend.map.entity;

import backend.map.entity.dto.ParticulatePredictInfoDto;
import backend.map.entity.dto.WeatherInfoDto;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class ApiData {

    @Id @GeneratedValue
    private Long id;
    //미세먼지 data
    private String informCause;
    private String informOverall;

    // 날씨 data
    private String informSky; //하늘 상태
    private String informPty; //강수 상태


    public ApiData() {

    }

    public void updateParticul(ParticulatePredictInfoDto particulatePredictInfoDto){
        this.informCause = particulatePredictInfoDto.getInformCause();
        this.informOverall = particulatePredictInfoDto.getInformOverall();
    }

    public void updateWeather(WeatherInfoDto weatherInfoDto){
        this.informSky = weatherInfoDto.getInformSky();
        this.informPty = weatherInfoDto.getInformPty();
    }

}
