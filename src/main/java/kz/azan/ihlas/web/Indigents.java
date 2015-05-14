/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.IndigentBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.Indigent;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Indigents extends Controller<Indigent> {

    @Inject
    private IndigentBean bean;

    public Indigents() {
        super(Indigent.class);
    }

    @Override
    protected Bean getBean() {
        return bean;
    }
}
