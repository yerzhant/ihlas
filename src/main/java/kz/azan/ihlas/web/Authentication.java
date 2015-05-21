/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import kz.azan.ihlas.util.Util;
import org.primefaces.context.RequestContext;

/**
 *
 * @author e.tylepov
 */
@Named
@RequestScoped
public class Authentication {

    @Inject
    private FacesContext fc;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String login() {
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            req.login(username, Util.hashSha256(password));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | ServletException e) {
            Util.addError("Доступ запрещен");
            RequestContext.getCurrentInstance().execute("jQuery('#dialog').effect('shake', { times:3 }, 100)");
            return null;
        }

        return "/user/main.xhtml";
    }

    public String logout() {
        fc.getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
