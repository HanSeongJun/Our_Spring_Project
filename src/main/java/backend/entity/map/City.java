package backend.entity.map;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "city")
    private List<Gu> gulist = new ArrayList<>();

    public City(){

    }

    public City(String cityCode, String cityName, int grade){
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.grade = grade;
    }

    public void updateGrade(int grade){
        this.grade = grade;
    }

}
