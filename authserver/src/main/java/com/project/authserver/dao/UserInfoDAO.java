package com.project.authserver.dao;
import com.project.authserver.bean.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.Date;
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