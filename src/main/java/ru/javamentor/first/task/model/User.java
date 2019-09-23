package ru.javamentor.first.task.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "mail")
    private String mail;

    public User(){}
    public User(Long id, String login, String password, String name, String secondName, String mail) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.mail = mail;
    }
    public User(String login, String password, String name, String secondName, String mail) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.mail = mail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMail() {
        return mail;
    }
}
