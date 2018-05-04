package Entities;

import java.util.Objects;

public class Notification {
    private int id;
    private User user;
    private String message;
    private boolean state;
    //false ken machefhech true ken chefha

    public Notification() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id &&
                state == that.state &&
                Objects.equals(user, that.user) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, message, state);
    }

    public Notification(User user, String message, boolean state) {
        this.user = user;
        this.message = message;
        this.state = state;
    }

    public Notification(int id, User user, String message, boolean state) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return message;
    }


}
