package com.devopsbuddy9.backend.persistence.domain.backend;

import com.devopsbuddy9.enums.RolesEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kb on 7/19/2017.
 */
@Entity
public class Role implements Serializable {
    /** The Serial Version UID for Serializable classes. */
    private static final long serialVersionUID =1L;

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role(){

    }

    public Role(RolesEnum rolesEnum){
        this.id = rolesEnum.getId();
        this.name = rolesEnum.getRoleName();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set <UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set <UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
