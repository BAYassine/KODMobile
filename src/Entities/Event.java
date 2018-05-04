package Entities;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Rami
 */

public class Event {
    private static final long serialVersionUID = 1L;

    public Event() {

    }


    private Photo photo;
    private Integer id;
    private String title;
    private String type;
    private Date startDate;
    private Date endDate;
    private String location;
    private Double price;
    private int places;
    private int left_Places;
    private int age;
    private int idUser;


    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getLeft_Places() {
        return left_Places;
    }

    public void setLeft_Places(int left_Places) {
        this.left_Places = left_Places;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    //new Event(sujet.getText(), type.getText(),  location.getText(), Integer.parseInt(participants.getText()), Integer.parseInt(dispo.getText()), imageFile, Integer.parseInt(age.getText()))

    public Event(String title, String type, Date startDate, Date endDate, String location, int Places, int left_Places, Photo photo, int age) {
        this.title = title;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.places = places;
        this.left_Places = left_Places;
        this.photo = photo;
        this.age = age;
        //this.idUser = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + ", price=" + price + ", places=" + places + ", left_places=" + left_Places + ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return places == event.places &&
                left_Places == event.left_Places &&
                age == event.age &&
                idUser == event.idUser &&
                Objects.equals(photo, event.photo) &&
                Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(type, event.type) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(location, event.location) &&
                Objects.equals(price, event.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(photo, id, title, type, startDate, endDate, location, price, places, left_Places, age, idUser);
    }

}