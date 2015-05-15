/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.DocBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Doc;
import kz.azan.ihlas.model.DocType;
import kz.azan.ihlas.util.Util;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class indigentDocs extends Controller<Doc> {

    @Inject
    private DocBean bean;

    public indigentDocs() {
        super(Doc.class);
    }
    
    public Doc getSel() {
        return null;
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
    protected Bean getBean() {
        return bean;
    }
}
