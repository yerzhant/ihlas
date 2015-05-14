/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.io.Serializable;
import java.util.List;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.util.Util;

/**
 *
 * @author yerzhan
 * @param <T>
 */
public abstract class Controller<T> implements Serializable {

    private final Class<T> entityClass;

    protected List<T> items;

    private T selected;

    private boolean isNew = false;

    public void create() {
        try {
            selected = entityClass.newInstance();
            isNew = true;
        } catch (InstantiationException | IllegalAccessException ex) {
            Util.addError(ex);
        }
    }

    public void save() {
        getBean().edit(selected);
        if (isNew) {
            selected = null;
            isNew = false;
            items = null;
        }
    }

    public void cancel() {
        if (isNew) {
            selected = null;
            isNew = false;
        }
    }

    public void delete() {
        getBean().delete(selected);
        selected = null;
        isNew = false;
        items = null;
    }

    public void refresh() {
        getBean().refresh();
        selected = null;
        isNew = false;
        items = null;
    }

    public Controller() {
        entityClass = null;
    }

    public Controller(Class<T> c) {
        entityClass = c;
    }

    protected abstract Bean getBean();

    public List<T> getItems() {
        if (items == null) {
            items = getBean().findAll();
        }
        return items;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
        isNew = false;
    }
}
