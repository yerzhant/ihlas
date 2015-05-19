/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import javax.ejb.Stateless;
import kz.azan.ihlas.model.User;

@Stateless
public class UserBean extends Bean<User> {

    public UserBean() {
        super(User.class);
    }
}
