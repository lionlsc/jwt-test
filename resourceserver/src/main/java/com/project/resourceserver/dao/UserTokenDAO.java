package com.project.resourceserver.dao;

import com.project.resourceserver.bean.UserToken;
import org.apache.ibatis.annotations.Insert;

public interface UserTokenDAO {
    @Insert({
            "insert into user_token ",
            "values(#{userid},#{usertoken},#{usertokenexpire})"
    })
    public void addToken(UserToken token);
}