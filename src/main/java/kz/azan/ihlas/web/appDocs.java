/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.DocBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Doc;
import kz.azan.ihlas.util.Util;
import kz.azan.ihlas.web.event.AppEvent;
import kz.azan.ihlas.web.event.Selection;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class appDocs extends Controller<Doc> {

    @Inject
    private DocBean bean;

    @Inject
    private Applications applications;

    public void appSelected(@Observes @Selection AppEvent event) {
        items = null;
        setSelected(null);
    }

    public void handleFile(FileUploadEvent e) {
        getSelected().setImage(e.getFile().getContents());
    }

    public StreamedContent getImage() {
        if (getSelected() != null && getSelected().getImage() != null) {
            return new ByteArrayContent(getSelected().getImage());
        } else {
            return null;
        }
    }

    @Override
    public void save() {
        try {
            super.save();
        } catch (Exception ex) {
            Util.addError("Не указан документ");
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    @Override
    public List<Doc> getItems() {
        if (items == null) {
            items = bean.find(applications.getSelected());
        }
        return items;
    }

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected Doc createEntity() {
        Doc doc = new Doc();
        doc.setApplication(applications.getSelected());
        return doc;
    }
}
