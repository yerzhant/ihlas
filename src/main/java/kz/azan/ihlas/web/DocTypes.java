/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.DocTypeBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.DocType;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class DocTypes extends Controller<DocType> {

    @Inject
    private DocTypeBean bean;

    public DocTypes() {
        super(DocType.class);
    }

    @Override
    protected Bean getBean() {
        return bean;
    }
}
