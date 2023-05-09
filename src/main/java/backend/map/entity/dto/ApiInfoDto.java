package backend.map.entity.dto;

import lombok.Data;

@Data
public class ApiInfoDto {

    //미세먼지 data
    private String informCause;
    private String informOverall;

    // 날씨 data
    private String informSky; //하늘 상태
    private String informPty; //강수 상태

    public ApiInfoDto(ParticulatePredictInfoDto particulatePredictInfoDto,
                      WeatherInfoDto weatherInfoDto){
        this.informCause = particulatePredictInfoDto.getInformCause();
        this.informOverall = particulatePredictInfoDto.getInformOverall();
        this.informPty = weatherInfoDto.getInformPty();
        this.informSky = weatherInfoDto.getInformSky();
    }

}
