/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author yerzhan
 */
@Entity
@Table(name = "users_groups")
@NamedQueries({
    @NamedQuery(name = UsersGroup.FIND_BY_USER, query = "SELECT u FROM UsersGroup u WHERE u.user = :user")
})
public class UsersGroup implements Serializable {

    public static enum Group {

        admin,
        user
    };

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_USER = "UsersGroup.findByUser";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    private Group name;

    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public UsersGroup() {
    }

    public UsersGroup(Integer id) {
        this.id = id;
    }

    public UsersGroup(Integer id, Group name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getName() {
        return name;
    }

    public void setName(Group name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof UsersGroup)) {
            return false;
        }
        UsersGroup other = (UsersGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.azan.ihlas.model.UsersGroup[ id=" + id + " ]";
    }

}
