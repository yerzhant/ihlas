/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.data.UserBean;
import kz.azan.ihlas.model.User;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Users extends Controller<User> {

    @Inject
    private UserBean bean;

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected User createEntity() {
        return new User();
    }
}
