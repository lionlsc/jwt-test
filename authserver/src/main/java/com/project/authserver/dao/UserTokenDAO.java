package com.project.authserver.dao;

import com.project.authserver.bean.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserTokenDAO {
    @Insert({
            "insert into user_token ",
            "values(#{userid},#{usertoken},#{usertokenexpire})"
    })
    public void addToken(UserToken token);
    @Update("update user_token set userToken=#{usertoken},userTokenExpire=#{usertokenexpire} where userId=#{userid}")
    public void updateToken(UserToken token);
}
