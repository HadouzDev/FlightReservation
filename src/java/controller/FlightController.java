package controller;

import bean.Flight;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.FlightFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("flightController")
@SessionScoped
public class FlightController implements Serializable {

 
    
    private List<Flight> items;
    private Flight selected;

    
       @EJB
    private FlightFacade ejbFacade;
    private String depart;
    private String arrive;
    private Date datedep;
       private List<Flight> find;
    public void initParam(){
         selected=new Flight();
     }
    public String  findFlights(){
    return "/flight/vols";
    }
    
    public String search(){
         find= ejbFacade.getFlights(depart, arrive, datedep); 
        
            return "/flight/vols";       
    }
    public FlightController() {
    }

    public Flight getSelected() {
        if(selected==null){
            selected= new Flight();
        }
        return selected;
    }
    public void setSelected(Flight selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FlightFacade getFacade() {
        return ejbFacade;
    }

    public Flight prepareCreate() {
        selected = new Flight();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FlightCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FlightUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FlightDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Flight> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public FlightFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(FlightFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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

    public Flight getFlight(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Flight> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Flight> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Flight.class)
    public static class FlightControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FlightController controller = (FlightController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "flightController");
            return controller.getFlight(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Flight) {
                Flight o = (Flight) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Flight.class.getName()});
                return null;
            }
        }

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

    public Date getDatedep() {
        return datedep;
    }

    public void setDatedep(Date datedep) {
        this.datedep = datedep;
    }

    public List<Flight> getFind() {
        return find;
    }

    public void setFind(List<Flight> find) {
        this.find = find;
    }
    
}
