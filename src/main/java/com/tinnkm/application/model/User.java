package com.tinnkm.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户表
 * @author tinnkm
 */
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String nickname;
    private Integer phone;
    private String password;
    /**
     * 标识用户来源
     */
    private int fromWhere;

    /**
     * 权限逗号分隔
     */
    private String roles;
    //region getter/setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(int fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
//endregion
}
