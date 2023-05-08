package backend.map.entity.dto;

import lombok.Data;

@Data
public class ParticulatePredictInfoDto {

    private String informCause;
    private String informOverall;

    public ParticulatePredictInfoDto(String informCause, String informOverall){
        this.informCause = informCause;
        this.informOverall = informOverall;
    }
}
