package Entities;

import Core.Config;

import java.io.File;
import java.util.Objects;

public class Photo {

    public Photo(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    public Photo(int id, String url, String alt) {
        this.id = id;
        this.url = url;
        this.alt = alt;
    }

    private int id;
    private String url;
    private String alt;

    public Photo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo that = (Photo) o;
        return id == that.id &&
                Objects.equals(url, that.url) &&
                Objects.equals(alt, that.alt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, alt);
    }

    public String getName(){
        return this.getId() + "." + getUrl();
    }

    @Override
    public String toString() {
        return getUrl();
    }
}
