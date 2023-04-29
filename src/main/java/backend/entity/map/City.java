package backend.entity.map;

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

    @OneToMany(mappedBy = "gu")
    private List<Gu> gulist = new ArrayList<>();

    public City(){

    }

}
