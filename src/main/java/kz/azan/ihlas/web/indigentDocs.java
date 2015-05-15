/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.DocBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Doc;
import org.primefaces.event.FileUploadEvent;

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
    
    public void handleFile(FileUploadEvent e) {
        getSelected().setImage(e.getFile().getContents());
    }

    @Override
    protected Bean getBean() {
        return bean;
    }
}
