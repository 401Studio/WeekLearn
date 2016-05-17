package com.demo.dao;


import com.demo.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    String SELECT_FIELDS = "name,pwd";

    @Select("select "+ SELECT_FIELDS +" from bj_user limit 1")
    public User getUser();


}
