package POJO;

import java.io.Serializable;

public class User implements Serializable {
    //proprieties
    int id;
    String loginName;
    String passWord;
    String gender;
    String city;

    public User(){}

    public User(int id, String loginName, String passWord, String gender, String city) {
        this.id = id;
        this.loginName = loginName;
        this.passWord = passWord;
        this.gender = gender;
        this.city = city;
    }
    //constructor
    public User(String loginName, String passWord, String gender, String city) {
        this.loginName = loginName;
        this.passWord = passWord;
        this.gender = gender;
        this.city = city;
    }

    public User(String loginName, String passWord) {
        this.loginName = loginName;
        this.passWord = passWord;
    }
    //getter and setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    //functions

}
