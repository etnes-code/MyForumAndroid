package POJO;

import java.io.Serializable;

public class Post implements Serializable {
    int id;
    String content;
    String date;
    String author;
    String gender;

    public Post(){}

    public Post(int id, String content, String date, String author, String gender) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.author = author;
        this.gender = gender;
    }

    public Post(String content, String date, String author, String gender) {
        this.content = content;
        this.date = date;
        this.author = author;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
