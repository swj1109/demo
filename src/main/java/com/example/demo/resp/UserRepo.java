package com.example.demo.resp;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RepositoryRestResource(path="userRepo")
public interface UserRepo extends JpaRepository<User, Integer> {

    //插入语句, 使用原生的sql语句
    @Transactional
    @Modifying
    @Query(value = "insert into user_info(username) values (?1)",
            nativeQuery = true)
    void addUser(String userName);

    //插入语句, 使用原生的sql语句
    @Transactional
    @Modifying
    @Query(value = "delete from user_info where id = ?1",
            nativeQuery = true)
    void delUser(Integer id);
}


