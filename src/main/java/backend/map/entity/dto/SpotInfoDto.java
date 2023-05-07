package backend.map.entity.dto;

import lombok.Data;

@Data
public class SpotInfoDto {

    private String spotName;
    private String comment;

    public SpotInfoDto(){

    }

    public SpotInfoDto(String spotName, String comment){
        this.spotName = spotName;
        this.comment = comment;
    }

}
