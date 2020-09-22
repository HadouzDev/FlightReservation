/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Flight;
import bean.Paiement;
import bean.Passenger;
import bean.Promotion;
import bean.Reservation;
import controller.util.PdfUtil;
import controller.util.SearchUtil;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Omar
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "FlightReservationPU")
    private EntityManager em;
    @EJB
    private FlightFacade flightFacade ;
    @EJB
    private PassengerFacade passengerFacade;
      @EJB
    private PaiementFacade paiementFacade;
      
       
       
        public  List<Reservation> ReservationByCin(String cin){
        
           String query = "SELECT e FROM Reservation e WHERE 1=1";
        query += SearchUtil.addConstraint("e", "passenger.id", "=", cin);
        List l = getEntityManager().createQuery(query).getResultList();
         
       if (l.isEmpty() || l == null) {
           System.out.println("*****************nothing*********************");
            return null;
        } else {
                      System.out.println("*****************hhhhhhh*********************");

            return l;
        }
}
//        public  List<Flight> departandarive(Flight flighte){
//        
//           String query = "SELECT e FROM Flight e WHERE 1=1";
//        query += SearchUtil.addConstraint("e", "depart", "=", flighte.getDepart());
//                query += SearchUtil.addConstraint("e", "arrive", "=", flighte.getArrive());
//
//        List l = getEntityManager().createQuery(query).getResultList();
//         
//       if (l.isEmpty() || l == null) {
//           System.out.println("*****************nothing*********************");
//            return null;
//        } else {
//                      System.out.println("*****************hhhhhhh*********************");
//
//            return l;
//        }
//}
        
        // trouver le promotion associé *********************************
//           public  List<Promotion> findpromo(Flight flighte){
//              List<Flight>l =departandarive(flighte);
//              Flight f= l.get(0);
//                String query = "SELECT e FROM Promotion e WHERE 1=1";
//        query += SearchUtil.addConstraint("e", "depart", "=", flighte.getDepart());
//                query += SearchUtil.addConstraint("e", "arrive", "=", flighte.getArrive());
//                        List res= getEntityManager().createQuery(query).getResultList();
//
//                     if (res.isEmpty() || l == null) {
//            return null;
//        } else {
//            return res;
//        }
//           }
           
           
           
    public Double getNumberofplaces(String cin){
        List<Reservation> rs=ReservationByCin(cin);
        Reservation g=rs.get(0);
        
        
        int n=g.getNumberofpass();
        Double nbr=new Double(n);
        return nbr;
    }
    public Double getPriceByReservation(String cin){
        List<Reservation> rs=ReservationByCin(cin);
        Reservation g=rs.get(0);
        
        Double n=g.getFlight().getPrix();
        
        return n;
    }
    
    public Double prixTotal(String cin){
        Double n=getPriceByReservation(cin)*getNumberofplaces(cin);
        return n;
    }//***********************
      public void createTicket(String cin){
          
      }
    
    
    
    public int save(Passenger passenger ,Flight flighte, Reservation reservation){
        Passenger p = new Passenger();
        Reservation r =new Reservation();
        
     Flight f=flightFacade.find(flighte.getId());
        
        if (f !=null ){
        
           p.setFirstName(passenger.getFirstName());
           p.setLastName(passenger.getLastName());
           p.setId(passenger.getId());
           p.setEmail(passenger.getEmail());
            passengerFacade.create(p);
            f.setCapacite(f.getCapacite()-reservation.getNumberofpass());
           r.setNumberofpass(reservation.getNumberofpass());
           r.setAllerrouteur(reservation.getAllerrouteur());
           //******edit flight **************
           
            r.setPassenger(p);
             r.setFlight(f);
             create(r);
             return 1;
        }
        return 0;
    }
       //******************************
       
//      public void genegeratePdf(String cin) throws JRException, IOException{
//      Map<String, Object> params= new HashMap();
//      params.put("respo", "OmarHadouz");
//      PdfUtil.generatePdf(paiementFacade.FindTiket(cin), params, "PaimentPdf", "/jasper/test.jasper");
//              
//  }
       
       
//           public void generateCertificatPdf(Reservation res) throws JRException, IOException
//    {
//        Map<String,Object> params = new HashMap();
//
//        if ( res != null )
//        { params.put("respor", "omar");   
//            System.out.println(params);
//        }
//        PdfUtil.generatePdf(new ArrayList<>(), params, "certificatScolaire_"+res.getId(), "/jasper/test.jasper");
//    }
    
//      public int savePaiement(Passenger passenger,Reservation reservation){
//          Paiement paiement= new Paiement();
//          Passenger p= passengerFacade.find(passenger.getId());
//          Reservation r= find(reservation.getId());
//          System.out.println("*********ana hna asa7bi***************");
//          if (p != null && r !=null){
//              paiement.setDatePaiement(new Date());
//              paiement.setEtatPaiement(1);
//              paiement.setPassenger(passenger);
//              paiement.setReservation(reservation);
//                        System.out.println("*********hana ta ncrayeer fiih***************");
//
//              paiementFacade.create(paiement);
//                        System.out.println("*********wacreatooo***************");
//
//              return 1;
//          }
//          return 0;
//      }
       
       
          public int savePaiement(String cin){
          Paiement paiement= new Paiement();
           System.out.println("*********ana hna asa7bi***************");
          Passenger p= passengerFacade.find(cin);
           System.out.println("*********ana hna hhhhhh***************");
            List<Reservation> l=ReservationByCin(cin);
          Reservation r= l.get(0);
          System.out.println("*********ana hna asa7bi***************");
          if (p != null ||r !=null){
              paiement.setDatePaiement(new Date());
              paiement.setEtatPaiement(1);
              paiement.setPassenger(p);
              paiement.setReservation(r);
                        System.out.println("*********hana ta ncrayeer fiih***************");

              paiementFacade.create(paiement);
                        System.out.println("*********wacreatooo***************");
    
              return 1;
          }
          return 0;
      }

    
// public int save(String  fname ,String  lname,String  cin,String  email,String  codevol,int numberpass, int retour){
//        Passenger p = new Passenger();
//        Reservation r =new Reservation();
//        
//     Flight f=flightFacade.find(codevol);
//        System.out.println("***************ana hna assa7bi*********");
//        if (f !=null ){
//                System.out.println("***************ana hna assa7bi l9itoo*********");
//
//           p.setFirstName(fname);
//           p.setLastName(lname);
//           p.setId(cin);
//           p.setEmail(email);
//            passengerFacade.create(p);
//                    System.out.println("***************ana hna assa7bi creatooo*********");
//
//           r.setNumberofpass(numberpass);
//           r.setAllerrouteur(retour);
//           //******edit flight **************
//           
//            r.setPassenger(p);
//             r.setFlight(f);
//             create(r);
//            System.out.println("***************ana hna assa7bi creat ta reservation*********");
//
//             return 1;
//        }
//        return 0;
//    }
//    
   
       
       @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }
    
}
