package backend.map.entity.dto;

import lombok.Data;

@Data
public class WeatherInfoDto {

    private String informSky; //하늘 상태
    private String informPty; //강수 상태

    public WeatherInfoDto(String informSky, String informPty){
        this.informSky = informSky;
        this.informPty = informPty;
    }
}
