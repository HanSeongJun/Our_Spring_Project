package backend.entity.dto.map;

import lombok.Data;

@Data
public class GuMapInfoDto {

    private String guCode;
    private String guName;

    public GuMapInfoDto(){

    }

    public GuMapInfoDto(String guCode, String guName) {
        this.guCode = guCode;
        this.guName = guName;
    }
}
