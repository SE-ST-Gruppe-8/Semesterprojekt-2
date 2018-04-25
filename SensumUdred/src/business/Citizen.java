/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Bruger
 */
public class Citizen {
    private String name;
    private String id;
    private String needs;
    private Case CitizenCase;
    private Reference reference;

    public Citizen(String name, String id, String needs) {
        this.name = name;
        this.id = id;
        this.needs = needs;
        this.CitizenCase = null;
        this.reference = null;
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
    
    
    
}
