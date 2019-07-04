package com.project.authserver.dao;

import com.project.authserver.bean.Screct;
import org.apache.ibatis.annotations.Select;
public interface ScrectDao {
    @Select("select * from private_key")
    public Screct getKey();
}
