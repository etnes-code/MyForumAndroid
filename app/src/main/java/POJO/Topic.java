package POJO;

import java.io.Serializable;

public class Topic implements Serializable {


    private int idTopic;
    private String title;
    private String author;

    public Topic(){}

    public Topic(int idTopic, int idTopic1, String title, String author) {
        this.idTopic = idTopic;
        this.idTopic = idTopic1;
        this.title = title;
        this.author = author;
    }

    public Topic(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
