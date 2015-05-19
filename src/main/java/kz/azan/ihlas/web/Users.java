/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.data.UserBean;
import kz.azan.ihlas.model.User;
import kz.azan.ihlas.util.Util;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Users extends Controller<User> {

    @Inject
    private UserBean bean;

    private boolean isPasswordChanged;

    public void passwordChanged() {
        isPasswordChanged = true;
    }

    @Override
    public void setSelected(User selected) {
        isPasswordChanged = false;
        super.setSelected(selected);
    }

    @Override
    public void save() {
        if (getSelected().getId() == null || isPasswordChanged) {
            try {
                getSelected().setPassword(Util.hashSha256(getSelected().getName() + getSelected().getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Util.addError(ex);
            }
        }

        super.save();

        isPasswordChanged = false;
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected User createEntity() {
        return new User();
    }
}
