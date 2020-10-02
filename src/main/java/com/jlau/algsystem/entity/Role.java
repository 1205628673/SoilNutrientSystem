package com.jlau.algsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cxr1205628673 on 2020/5/1.
 */
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority{
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private String zhName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
