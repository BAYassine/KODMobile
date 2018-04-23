package Entities;

import java.util.Objects;

public class Game {

    public static String noPhoto = "file:/assets/images/default-game.png";

    private int id;
//    private Photo icon;
    private String name;
    private String url;
    private int age;
    private String device;
//    private Category category;
    private int gender;

    public Game(){

    }

    public Game(String name, String url) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Game(int id,String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return id == that.id &&
                age == that.age &&
                gender == that.gender &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, url, age, device, gender);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", age=" + age +
                ", device='" + device + '\'' +
                ", gender=" + gender +
                '}';
    }
}
