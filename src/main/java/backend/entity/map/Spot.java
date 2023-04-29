package backend.entity.map;

import javax.persistence.*;

@Entity
public class Spot {

    @Id @GeneratedValue
    private Long Id;

    public Spot(){

    }
    //연관관계 편의메서드 필요
}
