package Entities;

import java.util.ArrayList;
import java.util.Objects;

public class Child {

    private int id;
    private Integer parentId;
    private String name;
    private int age;
    private boolean gender;
    private Photo photo;
    private ArrayList<Integer> blockedGames = new ArrayList<>();

    public Child(Integer parentId, String name, int age, boolean gender, Photo photo) {
        this.parentId = parentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.photo = photo;
    }

    public Child(int id, Integer parentId, String name, int age, boolean gender, Photo photo) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.photo = photo;
    }

    public Child() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    
    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    
    
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child that = (Child) o;
        return id == that.id &&
                age == that.age &&
                gender == that.gender &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parentId, name, age, gender, photo);
    }

    public ArrayList<Integer> getBlockedGames() {
        return blockedGames;
    }
}
