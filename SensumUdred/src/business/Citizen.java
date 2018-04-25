/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.ICitizen;
import acq.IInquiry;

/**
 *
 * @author Bruger
 */
public class Citizen implements ICitizen{
    private String name;
    private String id;
    private String needs;
    private Case citizenCase;
    private Reference reference;
    private Inquiry inquiry;

    public Citizen(String name, String id, String needs) {
        this.name = name;
        this.id = id;
        this.needs = needs;
        this.citizenCase = null;
        this.reference = null;
        this.inquiry = null;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getNeeds() {
        return needs;
    }

    public Case getCase() {
        return citizenCase;
    }

    public void setCase(Case citizenCase) {
        this.citizenCase = citizenCase;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
    @Override
    public IInquiry getInquiry() {
        return (IInquiry) inquiry;
    }
    
    public void createInquiry(String id, String origin, boolean informed){
        this.inquiry = new Inquiry(id, origin, informed, this);
    }
    
    public void setInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }
    
    
}
