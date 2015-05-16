/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.data;

import java.util.List;
import javax.ejb.Stateless;
import kz.azan.ihlas.model.Application;
import kz.azan.ihlas.model.Doc;
import kz.azan.ihlas.model.Indigent;

@Stateless
public class DocBean extends Bean<Doc> {

    public DocBean() {
        super(Doc.class);
    }

    public List<Doc> find(Indigent indigent) {
        return em.createNamedQuery(Doc.FIND_BY_INDIGENT).setParameter("indigent", indigent).getResultList();
    }

    public List<Doc> find(Application application) {
        return em.createNamedQuery(Doc.FIND_BY_APPLICATION).setParameter("application", application).getResultList();
    }
}
