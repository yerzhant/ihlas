/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
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

    @FacesConverter(forClass = DocType.class)
    public static class DocTypesConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DocTypes controller = (DocTypes) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "docTypes");
            return controller.bean.find(Integer.valueOf(value));
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DocType) {
                DocType o = (DocType) object;
                return String.valueOf(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DocType.class.getName()});
                return null;
            }
        }

    }
}
