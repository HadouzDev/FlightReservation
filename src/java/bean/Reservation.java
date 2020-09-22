/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Omar
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numberofpass;
    private int allerrouteur;
    //private int etat;//1 si confirmé 0 si non 
    @OneToOne
    private Passenger passenger;
    @OneToOne
    private Flight flight;

    public Reservation() {
    }

    public Reservation(Long id) {
        this.id = id;
    }

    public Reservation(int numberofpass, int allerrouteur, Passenger passenger, Flight flight) {
        this.numberofpass = numberofpass;
        this.allerrouteur = allerrouteur;
        this.passenger = passenger;
        this.flight = flight;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberofpass() {
        return numberofpass;
    }

    public void setNumberofpass(int numberofpass) {
        this.numberofpass = numberofpass;
    }

    public int getAllerrouteur() {
        return allerrouteur;
    }

    public void setAllerrouteur(int allerrouteur) {
        this.allerrouteur = allerrouteur;
    }

    public Passenger getPassenger() {
        if (passenger ==null){
            passenger =new Passenger();
        }
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

//    public int getEtat() {
//        return etat;
//    }
//
//    public void setEtat(int etat) {
//        this.etat = etat;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Reservation[ id=" + id + " ]";
    }
    
}
