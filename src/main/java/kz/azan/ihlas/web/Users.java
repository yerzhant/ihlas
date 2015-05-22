/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.data.UserBean;
import kz.azan.ihlas.model.User;
import kz.azan.ihlas.util.Util;
import kz.azan.ihlas.web.event.Selection;
import kz.azan.ihlas.web.event.UserEvent;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Users extends Controller<User> {

    private static final short MAX_LOGIN_TRIES = 3;

    @Inject
    private UserBean bean;

    @Inject
    @Selection
    private Event<UserEvent> selection;

    private boolean rehash;

    public void rehash() {
        rehash = true;
    }

    public void resetLoginTries() {
        getSelected().setLeftLoginTries(MAX_LOGIN_TRIES);
        super.save();
    }

    @Override
    public void setSelected(User selected) {
        rehash = false;
        super.setSelected(selected);
        selection.fire(new UserEvent(selected));
    }

    @Override
    public void save() {
        if (getSelected().getId() == null || rehash) {
            try {
                getSelected().setPassword(Util.hashSha256(getSelected().getName() + getSelected().getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Util.addError(ex);
            }
        }

        super.save();

        rehash = false;
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected User createEntity() {
        return new User(MAX_LOGIN_TRIES);
    }
}
