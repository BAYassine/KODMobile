package Entities;

import java.util.Objects;

public class Reservation {
    private static final long serialVersionUID = 1L;

    public Reservation() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(event_id, that.event_id) &&
                Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_id, event_id, participants);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", event_id=" + event_id +
                ", participants=" + participants +
                '}';
    }

    private  Integer id;

    public Reservation(Integer id, Integer user_id, Integer event_id, Integer participants) {
        this.id = id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.participants = participants;
    }

    private  Integer user_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    private  Integer event_id;
    private  Integer participants;
}
