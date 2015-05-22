/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yerzhan
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_NAME, query = "SELECT u FROM User u WHERE u.name = :name")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final short MAX_LOGIN_TRIES = 3;

    public static final String FIND_BY_NAME = "User.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String password;

    @Basic(optional = false)
    @NotNull
    @Column(name = "left_login_tries")
    private short leftLoginTries;

    @Size(max = 2147483647)
    @Column(name = "full_name")
    private String fullName;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<UsersGroup> usersGroups;

    public User() {
        resetLeftLoginTries();
    }

    public final void resetLeftLoginTries() {
        leftLoginTries = MAX_LOGIN_TRIES;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getLeftLoginTries() {
        return leftLoginTries;
    }

    public void setLeftLoginTries(short leftLoginTries) {
        this.leftLoginTries = leftLoginTries;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<UsersGroup> getUsersGroups() {
        return usersGroups;
    }

    public void setUsersGroups(List<UsersGroup> usersGroups) {
        this.usersGroups = usersGroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "kz.azan.ihlas.model.User[ id=" + id + " ]";
    }
}
