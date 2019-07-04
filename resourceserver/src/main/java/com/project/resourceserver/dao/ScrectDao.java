package com.project.resourceserver.dao;

import com.project.resourceserver.bean.Screct;
import org.apache.ibatis.annotations.Select;

public interface ScrectDao {
    @Select("select * from private_key")
    public Screct getKey();
}
