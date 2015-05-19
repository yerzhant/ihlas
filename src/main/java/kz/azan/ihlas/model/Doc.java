/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yerzhan
 */
@Entity
@Table(name = "docs")
@NamedQueries({
    @NamedQuery(name = Doc.FIND_BY_INDIGENT, query = "SELECT d FROM Doc d WHERE d.indigent = :indigent"),
    @NamedQuery(name = Doc.FIND_BY_APPLICATION, query = "SELECT d FROM Doc d WHERE d.application = :application")
})
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_INDIGENT = "Doc.findByIndigent";
    public static final String FIND_BY_APPLICATION = "Doc.findByApplication";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    private byte[] image;

    @Size(max = 2147483647)
    private String notes;

    @JoinColumn(name = "indigent", referencedColumnName = "id")
    @ManyToOne
    private Indigent indigent;

    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocType docType;

    @JoinColumn(name = "application", referencedColumnName = "id")
    @ManyToOne
    private Application application;

    public Doc() {
    }

    public Doc(Integer id) {
        this.id = id;
    }

    public Doc(Integer id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Indigent getIndigent() {
        return indigent;
    }

    public void setIndigent(Indigent indigent) {
        this.indigent = indigent;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doc)) {
            return false;
        }
        Doc other = (Doc) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "kz.azan.ihlas.model.Doc[ id=" + id + " ]";
    }

}
