/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author yerzhan
 */
public class Util {

    public static void addError(String s) {
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s);
        FacesContext.getCurrentInstance().addMessage(s, m);
    }
}
