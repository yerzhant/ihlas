/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.ApplicationBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Application;
import kz.azan.ihlas.web.event.AppEvent;
import kz.azan.ihlas.web.event.IndigentEvent;
import kz.azan.ihlas.web.event.Selection;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Applications extends Controller<Application> {

    @Inject
    private ApplicationBean bean;

    @Inject
    private Indigents indigents;

    @Inject
    @Selection
    private Event<AppEvent> selection;

    public void indigentSelected(@Observes @Selection IndigentEvent event) {
        items = null;
        setSelected(null);
    }

    @Override
    public void setSelected(Application selected) {
        super.setSelected(selected);
        selection.fire(new AppEvent(selected));
    }

    @Override
    public List<Application> getItems() {
        if (items == null) {
            items = bean.find(indigents.getSelected());
        }
        return items;
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected Application createEntity() {
        Application app = new Application();
        app.setIndigent(indigents.getSelected());
        return app;
    }
}
