package com.project.authserver.bean;
import java.io.Serializable;
import java.util.Date;
public class UserToken implements Serializable {
    private Long userid;
    private String usertoken;
    private Date usertokenexpire;
    private static final long serialVersionUID = 1L;
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public Date getUsertokenexpire() {
        return usertokenexpire;
    }

    public void setUsertokenexpire(Date usertokenexpire) {
        this.usertokenexpire = usertokenexpire;
    }
}