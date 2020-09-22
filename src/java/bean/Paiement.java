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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Omar
 */
@Entity
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int etatPaiement;// 0 si non  //   1 si payée
        @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePaiement;

   @OneToOne
    private Passenger passenger;
    @OneToOne
    private Reservation reservation;

    public Paiement() {
    }

    public Paiement(Long id) {
        this.id = id;
    }

    public Paiement(int etatPaiement, Date datePaiement, Passenger passenger, Reservation reservation) {
        this.etatPaiement = etatPaiement;
        this.datePaiement = datePaiement;
        this.passenger = passenger;
        this.reservation = reservation;
    }

    public Paiement(Long id, int etatPaiement, Date datePaiement, Passenger passenger, Reservation reservation) {
        this.id = id;
        this.etatPaiement = etatPaiement;
        this.datePaiement = datePaiement;
        this.passenger = passenger;
        this.reservation = reservation;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(int etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
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
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Paiement[ id=" + id + " ]";
    }
    
}
