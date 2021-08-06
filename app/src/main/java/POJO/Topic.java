package POJO;

import java.io.Serializable;

public class Topic implements Serializable {
    private int idTopic;
    private String title;
    private String auhtor;

    public Topic(int idTopic, String title, String auhtor) {
        this.idTopic = idTopic;
        this.title = title;
        this.auhtor = auhtor;
    }

    public Topic(String title, String auhtor) {
        this.title = title;
        this.auhtor = auhtor;
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

    public String getAuhtor() {
        return auhtor;
    }

    public void setAuhtor(String auhtor) {
        this.auhtor = auhtor;
    }
}
