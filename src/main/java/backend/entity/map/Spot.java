package backend.entity.map;

import backend.entity.board.Board;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Spot {

    @Id @GeneratedValue
    private Long Id;
    @NotNull
    private String spotName;
    private String comment;

    //spot:gu -> 다:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="gu_id")
    private Gu gu;

    //spot:board -> 1:다
//    @OneToMany(mappedBy = "board")
//    private List<Board> baordList = new ArrayList<>();

    public Spot(){

    }

    //연관관계 편의메서드
    public void changeGu(Gu gu){
        this.gu = gu;
        gu.getSpotlist().add(this);
    }
}
