/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import java.util.List;
import javax.ejb.Stateless;
import kz.azan.ihlas.model.Application;
import kz.azan.ihlas.model.Indigent;

@Stateless
public class ApplicationBean extends Bean<Application> {

    public ApplicationBean() {
        super(Application.class);
    }

    public List<Application> find(Indigent indigent) {
        return em.createNamedQuery(Application.FIND_BY_INDIGENT).setParameter("indigent", indigent).getResultList();
    }
}
