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

    /**
     * The person who sent in the inquiry.
     */
    private String origin;
    /**
     * Whether or not the citizen has been informed.
     */
    private boolean isCitizenInformed;
    /**
     * The inquiry's id.
     */
    private String id;
    /**
     * The citizen whom the inquiry is about
     */
    private Citizen citizen;
    /**
     * The inquiry's description.
     */
    private String description;
    /**
     * An ID which makes sure that problems won't occur with binary files when
     * using serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an inquiry with the neccessary information.
     *
     * @param id
     * @param origin
     * @param informed
     * @param citizen
     * @param description
     */
    public Inquiry(String id, String origin, boolean informed, Citizen citizen, String description) {
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

    @Override
    public String toString() {
        if (isCitizenInformed) {
            return String.format("%-30s %-35s %-40s", "HendvendelsesID: " + this.id, "Borger: " + citizen.getName(), "Borgeren er informeret om henvendelsen");
//            return "HendvendelsesID: " + this.id + "\tBorger: " + citizen.getName() + "\tBorgeren er informeret om henvendelsen";
        } else {
            return String.format("%-30s %-30s %-45s", "HendvendelsesID: " + this.id, "Borger: " + citizen.getName(), "Borgeren er ikke informeret om henvendelsen");
//            return "HendvendelsesID: " + this.id + "\tBorger: " + citizen.getName() + "\tBorgeren er ikke informeret om henvendelsen";
        }
    }

    @Override
    public void setIsCitizenInformed(boolean isInformed) {
        this.isCitizenInformed = isInformed;
    }

}
