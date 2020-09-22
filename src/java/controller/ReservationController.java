package controller;

import bean.Flight;
import bean.Passenger;
import bean.Reservation;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import java.io.IOException;
import service.ReservationFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.sf.jasperreports.engine.JRException;
import service.PaiementFacade;

@Named("reservationController")
@SessionScoped
public class ReservationController implements Serializable {

  
    private List<Reservation> items = null;
    private Reservation selected;
     @EJB
    private ReservationFacade ejbFacade;
        @EJB
    private PaiementFacade paiementFacade;
        
    private String cin;
    private String fname;
    private String lname;
    private String email;
    private String codevol;
    private int numberpass;
    private int retour;
     
    private Passenger passenger;
    private Flight flight;
    
    public void initParam(){
         selected=new Reservation();
     }
     public String save(){
         ejbFacade.save(passenger,flight,selected);
         return "/reservation/succes";
     }
          public String Redirect(){
         return "/paiement/payer";
         
     }  
//      public String save( ){
//         ejbFacade.save(fname ,  lname,  cin,email,codevol, numberpass,  retour);
//         return "/template/Home";
//     }
          
     public String getinfolist(){
      items=ejbFacade.ReservationByCin(cin);
      return "/paiement/confirmer";
  }
     public String prixTotal(){
        Double str= ejbFacade.prixTotal(cin);
        String cmp=str.toString();
        return cmp;
     }
     public String SavePaiement(){
         System.out.println("***************fink**********");
         ejbFacade.savePaiement(cin);
          return "/paiement/paiementSucces";
     }
     
//      public void generatePdf() throws JRException, IOException{
//         System.out.println("***************fink ana hna zzzzzzzzzzzzzzzzzzzzzzzzzzz**********");
//         ejbFacade.genegeratePdf(cin);
//         FacesContext.getCurrentInstance().getResponseComplete();
//          initParam();
//     }
     
     public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Operation de reservation validé avec succes"));
    }
     
             public void succes() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Merci D'avoir Reserveé Chez Nous!", " Operation de Paiement validé avec succes"));
    }
                
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }
     
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }
    
    public ReservationController() {
    }

    public Reservation getSelected() {
        if (selected== null){
            selected =new Reservation();
        }
        return selected;
    }

    public void setSelected(Reservation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReservationFacade getFacade() {
        return ejbFacade;
    }

    public Reservation prepareCreate() {
        selected = new Reservation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReservationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReservationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReservationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reservation> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Reservation getReservation(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Reservation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reservation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reservation.class)
    public static class ReservationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservationController controller = (ReservationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservationController");
            return controller.getReservation(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reservation) {
                Reservation o = (Reservation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reservation.class.getName()});
                return null;
            }
        }

    }

    public ReservationFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ReservationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Passenger getPassenger() {
        if(passenger==null){
            passenger=new Passenger();
        }
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
         if(flight==null){
            flight=new Flight();
        }
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodevol() {
        return codevol;
    }

    public void setCodevol(String codevol) {
        this.codevol = codevol;
    }

    public int getNumberpass() {
        return numberpass;
    }

    public void setNumberpass(int numberpass) {
        this.numberpass = numberpass;
    }

    public int getRetour() {
        return retour;
    }

    public void setRetour(int retour) {
        this.retour = retour;
    }

}
