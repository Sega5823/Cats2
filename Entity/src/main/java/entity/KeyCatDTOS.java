package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Builder
@Jacksonized
//@JsonCreator
public class KeyCatDTOS {
    @JsonProperty("key")
    private int key;
    @JsonProperty("catDTOList")
    private List<CatDTO> catDTOList;

//    public KeyCatDTOS(int key, List<CatDTO> catDTOList) {
//        this.key = key;
//        this.catDTOList = catDTOList;
//    }

    public KeyCatDTOS() {
        catDTOList = new ArrayList<>();
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public KeyCatDTOS(@JsonProperty int key, @JsonProperty List<CatDTO> catDTOList) {
        this.key = key;
        this.catDTOList = catDTOList;
    }

    public int getKey() {
        return key;
    }

    public List<CatDTO> getCatDTOList() {
        return catDTOList;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setCatDTOList(List<CatDTO> catDTOList) {
        this.catDTOList = catDTOList;
    }
}
