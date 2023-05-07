package backend.map.entity.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board {

    @Id @GeneratedValue
    private Long id;

    public Board(){

    }

}
