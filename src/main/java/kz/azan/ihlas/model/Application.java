/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author yerzhan
 */
@Entity
@Table(name = "applications")
@NamedQueries({
    @NamedQuery(name = Application.FIND_BY_INDIGENT, query = "SELECT a FROM Application a WHERE a.indigent = :indigent")
})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_INDIGENT = "Application.findByIndigent";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Column(name = "applied_on")
    @Temporal(TemporalType.DATE)
    private Date appliedOn;

    @Size(max = 2147483647)
    private String reason;

    @OneToMany(mappedBy = "application", cascade = CascadeType.REMOVE)
    private List<Doc> docList;

    @JoinColumn(name = "indigent", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Indigent indigent;

    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AppType appType;

    public Application() {
    }

    public Application(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Doc> getDocList() {
        return docList;
    }

    public void setDocList(List<Doc> docList) {
        this.docList = docList;
    }

    public Indigent getIndigent() {
        return indigent;
    }

    public void setIndigent(Indigent indigent) {
        this.indigent = indigent;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
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
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "kz.azan.ihlas.model.Application[ id=" + id + " ]";
    }

}
