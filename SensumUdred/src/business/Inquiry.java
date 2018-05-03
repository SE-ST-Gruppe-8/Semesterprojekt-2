/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.IInquiry;
import java.io.Serializable;

/**
 *
 * @author Bruger
 */
public class Inquiry implements IInquiry, Serializable {
    private String origin;
    private boolean isCitizenInformed;
    private String id;
    private Citizen citizen;
    private String description;
    private static final long serialVersionUID = 1L;
    
    
    public Inquiry(String id, String origin, boolean informed, Citizen citizen, String description){
        this.origin = origin;
        this.isCitizenInformed = informed;
        this.citizen = citizen;
        this.id = id;
        this.description = description;
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean isCitizenInformed() {
        return isCitizenInformed;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Citizen getCitizen() {
        return citizen;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String toString(){
        return citizen.getId()+" "+citizen.getName();
    }
    
    
}
