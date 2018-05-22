package com.tinnkm.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 用户表，对接微信
 */
@Entity
public class User {
    // 如果是微信用户openid自己获取，如果是后台用户系统生成
    @Id
    private UUID id;
    private String name;
    private String nickname;
    private Integer phone;
    private String password;
    // 标识用户来源
    private int fromWhere;

    //region getter/setter


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
    //endregion
}
