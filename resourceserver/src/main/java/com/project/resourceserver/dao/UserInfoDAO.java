package com.project.resourceserver.dao;
import com.project.resourceserver.bean.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserInfoDAO {
    @Insert({
            "insert into user_info(userName,userPwd) values",
            "(#{username},#{userpwd})"
    })
     public void register(UserInfo user);
    @Select({"select * from user_info where userName=#{username}"
    })
    public  UserInfo getUserByUsername(UserInfo user);  /*注册,登陆时使用*/
}