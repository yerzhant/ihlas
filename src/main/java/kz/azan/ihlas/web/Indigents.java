/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.IndigentBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Indigent;
import kz.azan.ihlas.web.event.IndigentEvent;
import kz.azan.ihlas.web.event.Selection;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Indigents extends Controller<Indigent> {

    @Inject
    private IndigentBean bean;

    @Inject
    @Selection
    private Event<IndigentEvent> selection;

    private String filter;

    public void filter() {
        items = bean.filter(filter);
    }

    @Override
    public void setSelected(Indigent selected) {
        super.setSelected(selected);
        selection.fire(new IndigentEvent(selected));
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected Indigent createEntity() {
        return new Indigent();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
