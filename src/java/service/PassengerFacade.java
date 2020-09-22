/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Passenger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Omar
 */
@Stateless
public class PassengerFacade extends AbstractFacade<Passenger> {

    @PersistenceContext(unitName = "FlightReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PassengerFacade() {
        super(Passenger.class);
    }
    
}
