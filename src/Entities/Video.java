package Entities;

import java.sql.Date;
import java.util.Objects;

public class Video {

    private int id;
    private Integer subjectId;
    private String url;
    private Date date;
    private int views;
    private Integer cartoonId;
    private Integer categoryId;
    private String titre;
    private String urlyout;

    public Video() {
    }

    public Video(Integer subjectId, String url, Date date, int views, Integer cartoonId, Integer categoryId, String titre,String urlyout) {
        this.subjectId = subjectId;
        this.url = url;
        this.date = date;
        this.views = views;
        this.cartoonId = cartoonId;
        this.categoryId = categoryId;
        this.titre = titre;
        this.urlyout = urlyout;

    }

    public Video(int id, Integer subjectId, String url, Date date, int views, Integer cartoonId, Integer categoryId, String titre) {
        this.id = id;
        this.subjectId = subjectId;
        this.url = url;
        this.date = date;
        this.views = views;
        this.cartoonId = cartoonId;
        this.categoryId = categoryId;
        this.titre = titre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }


    public String getUrl() {
        return url;
    }

    public String geturlyout() {
        return urlyout;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public Integer getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Integer cartoonId) {
        this.cartoonId = cartoonId;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setUrlyout(String urlyout) {
        this.urlyout = urlyout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video that = (Video) o;
        return id == that.id &&
                views == that.views &&
                Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(url, that.url) &&
                Objects.equals(date, that.date) &&
                Objects.equals(cartoonId, that.cartoonId) &&
                Objects.equals(urlyout, that.urlyout)  &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(titre, that.titre);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subjectId, url, date, views, cartoonId, categoryId, titre,urlyout);
    }

}
