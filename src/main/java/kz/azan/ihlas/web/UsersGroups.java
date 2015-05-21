/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.data.UsersGroupBean;
import kz.azan.ihlas.model.UsersGroup;
import kz.azan.ihlas.web.event.Selection;
import kz.azan.ihlas.web.event.UserEvent;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class UsersGroups extends Controller<UsersGroup> {

    @Inject
    private UsersGroupBean bean;

    @Inject
    private Users users;

    public void userSelected(@Observes @Selection UserEvent event) {
        items = null;
        setSelected(null);
    }

    public UsersGroup.Group[] getGroups() {
        return UsersGroup.Group.values();
    }

    @Override
    public List<UsersGroup> getItems() {
        if (items == null) {
            items = bean.find(users.getSelected());
        }
        return items;
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected UsersGroup createEntity() {
        UsersGroup group = new UsersGroup();
        group.setUser(users.getSelected());
        return group;
    }
}
