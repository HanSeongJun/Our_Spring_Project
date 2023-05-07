package backend.map.entity.dto;

import lombok.Data;

@Data
public class GuMapInfoDto {

    private String guCode;
    private String guName;

    private String vector;

    private int grade;

    public GuMapInfoDto(){

    }

    public GuMapInfoDto(String guCode, String guName, String vector, int grade) {
        this.guCode = guCode;
        this.guName = guName;
        this.vector = vector;
        this.grade = grade;
    }
}
