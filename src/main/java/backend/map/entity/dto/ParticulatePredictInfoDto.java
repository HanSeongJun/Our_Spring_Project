package backend.map.entity.dto;

import lombok.Data;

@Data
public class particulatePredictInfo {

    private String informCause;
    private String informOverall;

    private particulatePredictInfo(String informCause, String informOverall){
        this.informCause = informCause;
        this.informOverall = informOverall;
    }
}
