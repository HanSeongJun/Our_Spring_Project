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

    //spot연관관계 매핑필요
    @OneToMany(mappedBy = "spot")
    private List<Spot> spotlist = new ArrayList<>();

    public Gu(){

    }


}
