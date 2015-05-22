/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import java.util.List;
import javax.ejb.Stateless;
import kz.azan.ihlas.model.User;

@Stateless
public class UserBean extends Bean<User> {

    public UserBean() {
        super(User.class);
    }

    public void loginFailed(String name) {
        User u = find(name);

        if (u != null) {
            short t = u.getLeftLoginTries();
            if (t > 0) {
                u.setLeftLoginTries((short) (t - 1));
                edit(u);
            }
        }
    }

    public void resetLoginTries(String name) {
        User u = find(name);

        if (u != null) {
            u.resetLeftLoginTries();
            edit(u);
        }
    }

    public User find(String name) {
        List<User> l = em.createNamedQuery(User.FIND_BY_NAME).setParameter("name", name).getResultList();
        return l.size() == 1 ? l.get(0) : null;
    }
}
