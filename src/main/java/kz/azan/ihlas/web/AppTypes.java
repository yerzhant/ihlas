/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import kz.azan.ihlas.data.AppTypeBean;
import kz.azan.ihlas.model.AppType;

/**
 *
 * @author yerzhan
 */
@Model
public class AppTypes {

    @Inject
    private AppTypeBean bean;

    private List<AppType> items;

    public List<AppType> getItems() {
        if (items == null) {
            items = bean.findAll();
        }
        return items;
    }
}
