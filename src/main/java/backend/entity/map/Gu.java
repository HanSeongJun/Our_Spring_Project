package backend.entity.map;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Gu {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String guCode;
    @Column(unique = true)
    private String guName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    private City city;

    //spot연관관계 매핑필요 -> gu가 1개일때 spot 여러개
    @OneToMany(mappedBy = "gu")
    private List<Spot> spotlist = new ArrayList<>();

    public Gu(){

    }

    //연관관계 편의매서드
    public void changeGu(City city){
        this.city = city;
        city.getGulist().add(this);
    }

}
