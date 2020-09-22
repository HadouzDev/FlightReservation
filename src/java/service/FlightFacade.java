/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Flight;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Omar
 */
@Stateless
public class FlightFacade extends AbstractFacade<Flight> {

    @PersistenceContext(unitName = "FlightReservationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
public  List<Flight> getFlights(String depart,String arrive, Date datedepart){
        String query = "SELECT e FROM Flight e WHERE 1=1";
        query += SearchUtil.addConstraint("e", "arrive", "=", arrive);
       query += SearchUtil.addConstraint("e", "depart", "=", depart);
       query += SearchUtil.addConstraintDate("e", "datedepart","=", datedepart);
        List l = getEntityManager().createQuery(query).getResultList();
         
       if (l.isEmpty() || l == null) {
           System.out.println("*****************nothing*********************");
            return null;
        } else {
                      System.out.println("*****************hhhhhhh*********************");

            return l;
        }
}
        
        
    public FlightFacade() {
        super(Flight.class);
    }
    
}
