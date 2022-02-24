package com.cn.emio.sl.lblue.test.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertest")
/**
 * @author Silence_Lurker
 */
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
    private String mail;

    public void setId(String id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
