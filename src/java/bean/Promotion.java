/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Omar
 */
@Entity
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String codepromo;
    private String depart;
    private String arrive;
    private Double  porcentage;// le porcentage du promotion de vol 

    public String getCodepromo() {
        return codepromo;
    }

    public void setCodepromo(String codepromo) {
        this.codepromo = codepromo;
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

    public Double getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(Double porcentage) {
        this.porcentage = porcentage;
    }
    
 

   

    @Override
    public String toString() {
        return "bean.Promotion[ codepromo=" + codepromo + " ]";
    }
    
}
