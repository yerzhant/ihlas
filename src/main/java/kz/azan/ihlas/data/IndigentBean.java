/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import java.util.List;
import javax.ejb.Stateless;
import kz.azan.ihlas.model.Indigent;

@Stateless
public class IndigentBean extends Bean<Indigent> {

    public IndigentBean() {
        super(Indigent.class);
    }

    public List<Indigent> filter(String filter) {
        return em.createNamedQuery(Indigent.FILTER).setParameter("code", filter).getResultList();
    }
}
