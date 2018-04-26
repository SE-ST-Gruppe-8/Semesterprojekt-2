/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.ICitizen;
import acq.IInquiry;
import java.io.Serializable;

/**
 *
 * @author Bruger
 */
public class Citizen implements ICitizen, Serializable{
    private String name;
    private String id;
    private String needs;
    private Case citizenCase;
    private Reference reference;
    private Inquiry inquiry;
    private static final long serialVersionUID = 1L;

    public Citizen(String name, String id, String needs) {
        this.name = name;
        this.id = id;
        this.needs = needs;
        this.citizenCase = null;
        this.reference = null;
        this.inquiry = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNeeds() {
        return needs;
    }

    @Override
    public Case getCase() {
        return citizenCase;
    }

    @Override
    public void setCase(Case citizenCase) {
        this.citizenCase = citizenCase;
    }

    @Override
    public Reference getReference() {
        return reference;
    }

    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }
    @Override
    public IInquiry getInquiry() {
        return (IInquiry) inquiry;
    }
    
    @Override
    public void createInquiry(String id, String origin, boolean informed, String description){
        this.inquiry = new Inquiry(id, origin, informed, this, description);
    }
    
    @Override
    public void setInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }
     public String toString(){
         return this.getName();
     }
    
}
