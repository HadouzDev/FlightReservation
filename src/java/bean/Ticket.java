/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Omar
 */
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
     private String depart;
    private String arrive;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datedepart;
    private String firstName;
    private String lastName;
    private Double prix;

    public Ticket() {
    }

    public Ticket(String id) {
        this.id = id;
    }

    public Ticket(String id, String depart, String arrive, Date datedepart, String firstName, String lastName, Double prix) {
        this.id = id;
        this.depart = depart;
        this.arrive = arrive;
        this.datedepart = datedepart;
        this.firstName = firstName;
        this.lastName = lastName;
        this.prix = prix;
    }
    
    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
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
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ticket[ id=" + id + " ]";
    }
    
}
