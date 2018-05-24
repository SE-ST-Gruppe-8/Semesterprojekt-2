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
    /**
     * the name of the citizen
     */
    private String name;
    
    /**
     * the id of the citizen
     */
    private final String ID;
    
    /**
     * the needs of the citizen
     */
    private String needs;
    
    /**
     * the case that belongs to the citizen
     */
    private Case citizenCase;
    
    /**
     * the inquiry that belongs to the citizen
     */
    private Inquiry inquiry;
    
    /**
     * An ID which makes sure that problems won't occure with binary files when using Serilization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor for a citizen
     * @param name the name of the citizen
     * @param id the id of the citizen
     * @param needs the needs of the citizen
     */
    public Citizen(String name, String id, String needs) {
        this.name = name;
        this.ID = id;
        this.needs = needs;
        this.citizenCase = null;
        this.inquiry = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getNeeds() {
        return needs;
    }
    
    @Override
    public void setNeeds(String needs){
        this.needs=needs;
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
    public IInquiry getInquiry() {
        return (IInquiry) inquiry;
    }
    
    @Override
    public void setInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }
    /**
     * the toString method that prints the name and the id for the citizen
     * @return formatted text with the name and id of the citizen
     */
    @Override
     public String toString(){
         return String.format("%-35s %-25s","Borgernavn: "+this.getName() , "BorgerID: " + this.ID);
//         return "Borgernavn: "+this.getName() + "\tBorgerID: " + this.id;
     }

    
}
