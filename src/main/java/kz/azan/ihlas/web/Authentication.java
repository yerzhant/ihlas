/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import kz.azan.ihlas.data.UserBean;
import kz.azan.ihlas.util.Util;
import org.primefaces.context.RequestContext;

/**
 *
 * @author e.tylepov
 */
@Named
@SessionScoped
public class Authentication implements Serializable {

    @Inject
    private UserBean user;

    @Inject
    private FacesContext fc;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String fullName;

    public String login() {
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            req.login(username, Util.hashSha256(username + password));
            user.resetLoginTries(username);
            fullName = user.find(username).getFullName();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | ServletException e) {
            user.loginFailed(username);

            Util.addError("Доступ запрещен");
            RequestContext.getCurrentInstance().execute("jQuery('#dialog').effect('shake', { times:3 }, 500)");

            return null;
        }

        return "/user/main.xhtml";
    }

    public String logout() {
        fc.getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }
}
