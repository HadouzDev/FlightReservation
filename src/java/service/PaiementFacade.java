/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Paiement;
import bean.Ticket;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Omar
 */
@Stateless
public class PaiementFacade extends AbstractFacade<Paiement> {

    @PersistenceContext(unitName = "FlightReservationPU")
    private EntityManager em;


    
    public String getNumber(String cin){
        Integer  i = getEntityManager().createQuery("SELECT op.numberofpass FROM Reservation op WHERE op.passenger.id='" + cin + "'").getFirstResult();
        String a= i.toString();
        return a;
    }
     public List<Paiement> findPayement(String cin){
        List<Paiement>  i = getEntityManager().createQuery("SELECT op FROM Paiement op WHERE op.passenger.id='" + cin + "'").getResultList();
        return i;
    }
    public List<Ticket> FindTiket(String cin){
        List<Ticket>  i = getEntityManager().createQuery("SELECT op FROM Ticket op WHERE ticket.id='" + cin + "'").getResultList();
        return i;
    }
    
 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaiementFacade() {
        super(Paiement.class);
    }
    
}
