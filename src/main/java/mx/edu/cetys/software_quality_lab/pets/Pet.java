package mx.edu.cetys.software_quality_lab.pets;

import jakarta.persistence.*;

@Entity
public class Pet {

    // Dummy text para push a git

    // classic POJO
    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private String race;

    private String color;

    private Integer age;


    public Pet(){

    }
    public Pet(String name, String race, String color, Integer age) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.age = age;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}