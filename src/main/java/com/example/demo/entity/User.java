package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
@Table(name = "user_info")
public class User extends RepresentationModel<User>{

    public User(){

    }
    @JsonCreator
    public User(@JsonProperty("username") String username) {
        this.username = username;
    }

    @Id
    //@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(sequenceName = "user_id_seq", name = "user_id_seq", allocationSize = 1)
    private Integer id;

    //@Column(name = "username")
    private String username;
}
