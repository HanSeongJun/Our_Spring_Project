package backend.map.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class City {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String cityCode;
    @Column(unique = true)
    private String cityName;

    //날씨데이터를 기반으로 한 추천등급
    private int grade;

    //svg의 vector값을 저장할 변수
    @Column(length = 5000, unique = true)
    private String vector;

    @OneToMany(mappedBy = "city")
    private List<Gu> gulist = new ArrayList<>();

    public City(){

    }

    public City(String cityCode, String cityName, int grade, String vector){
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.grade = grade;
        this.vector = vector;
    }

    public void updateGrade(int grade){
        this.grade = grade;
    }

}
