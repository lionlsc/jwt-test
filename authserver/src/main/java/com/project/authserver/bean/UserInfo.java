package com.project.authserver.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class UserInfo implements Serializable {
    private Long userid;
    private String username;
    private String userpwd;
    private String usersex;
    private String userphone;
    private String usermail;
    private String useraddress;
    private BigDecimal userbalance;
    private String useravatarurl;
    private Date usercreatetime;
    private static final long serialVersionUID = 1L;
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public BigDecimal getUserbalance() {
        return userbalance;
    }

    public void setUserbalance(BigDecimal userbalance) {
        this.userbalance = userbalance;
    }

    public String getUseravatarurl() {
        return useravatarurl;
    }

    public void setUseravatarurl(String useravatarurl) {
        this.useravatarurl = useravatarurl;
    }

    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }
}