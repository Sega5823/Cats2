package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CatDTO {
    private String name;
    private String breed;
    private String color;
    private Date date;
    private int catID;
    private int ownerID;
}
