package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity //Объект таблицы
@Table(name = "cats")
public class Cat {
    private String name;
    private String breed;
    private String color;
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "owner_id")
    private int owner_id;
//    @ManyToOne
//    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
//    @JsonManagedReference
//    private Owner owner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catID;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "catid", referencedColumnName = "catID"),
            inverseJoinColumns = @JoinColumn(name = "friendid", referencedColumnName = "catID"))
    @JsonIgnore
    private Set<Cat> friends;

    public Cat(String name, String breed, String color, Date date, int owner_id) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.date = date;
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public Date getDate() {
        return date;
    }

    public int getOwner_id() {
        return owner_id;
    }

//    public Owner getOwner() {
//        return owner;
//    }

    public int getCatID() {
        return catID;
    }

    public Set<Cat> getFriends() {
        //return null;
        return friends;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDate(String date) {
        //this.date = new Date(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        try {
            this.date = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public void setFriends(Set<Cat> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return new String();
    }
}

//http://localhost:8080/api/cats/getCatByID?id=2
