/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import java.util.List;
import javax.ejb.Stateless;
import kz.azan.ihlas.model.User;
import kz.azan.ihlas.model.UsersGroup;

@Stateless
public class UsersGroupBean extends Bean<UsersGroup> {

    public UsersGroupBean() {
        super(UsersGroup.class);
    }

    public List<UsersGroup> find(User user) {
        return em.createNamedQuery(UsersGroup.FIND_BY_USER).setParameter("user", user).getResultList();
    }
}
