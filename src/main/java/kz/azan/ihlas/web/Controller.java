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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author yerzhan
 * @param <T>
 */
public abstract class Controller<T> implements Serializable {

    private final Class<T> entityClass;

    private List<T> items;

    private T selected;

    public void create() {
        try {
            selected = entityClass.newInstance();
            items.add(selected);
        } catch (InstantiationException | IllegalAccessException ex) {
            Util.addError(ex);
        }
    }

    public void save(RowEditEvent e) {
        getBean().save((T) e.getObject());
    }

    public void delete() {
        getBean().delete(selected);
        items.remove(selected);
    }

    public void refresh() {
        getBean().refresh();
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
    }
}
