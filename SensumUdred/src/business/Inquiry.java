/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;

/**
 *
 * @author Bruger
 */
public class Inquiry {
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

    public String getOrigin() {
        return origin;
    }

    public boolean isCitizenInformed() {
        return isCitizenInformed;
    }

    public String getId() {
        return id;
    }

    public Citizen getCitizen() {
        return citizen;
    }
}
