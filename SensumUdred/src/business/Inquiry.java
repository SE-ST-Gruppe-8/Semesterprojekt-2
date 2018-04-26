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
    
    
    public Inquiry(String id, String origin, boolean informed, Citizen citizen){
        this.origin = origin;
        this.isCitizenInformed = informed;
        this.citizen = citizen;
        this.id = id;
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
}
