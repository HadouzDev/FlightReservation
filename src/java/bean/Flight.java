/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Omar
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private int capacite;
    private String depart;
    private String arrive;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datedepart;
    private int flighttime;
    private Double prix;
    private String societe;
    
    @OneToOne
    private Promotion promotion; 
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

    public int getFlighttime() {
        return flighttime;
    }

    public void setFlighttime(int flighttime) {
        this.flighttime = flighttime;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Flight[ id=" + id + " ]";
    }
    
}
