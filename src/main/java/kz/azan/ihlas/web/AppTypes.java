/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import kz.azan.ihlas.data.AppTypeBean;
import kz.azan.ihlas.data.Bean;
import kz.azan.ihlas.model.AppType;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class AppTypes extends Controller<AppType> {

    @Inject
    private AppTypeBean bean;

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected AppType createEntity() {
        return new AppType();
    }

    @FacesConverter(forClass = AppType.class)
    public static class AppTypesConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AppTypes controller = (AppTypes) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "appTypes");
            return controller.bean.find(Integer.valueOf(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AppType) {
                AppType o = (AppType) object;
                return String.valueOf(o.getId());
            } else {
                return null;
            }
        }
    }
}
