/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.ICase;
import acq.ICitizen;
import acq.ISocialWorker;
import java.io.Serializable;

/**
 *
 * @author Bruger
 */
public class Case implements ICase, Serializable {

    /**
     * The case's id.
     */
    private final String ID;
    /**
     * The case's description & process.
     */
    private String description, process;
    /**
     * The socialworker who is assigned to the case.
     */
    private ISocialWorker socialWorker;
    /**
     * The citizen whom the case belongs to.
     */
    private ICitizen citizen;
    /**
     * An ID which makes sure that problems won't occur with binary files when
     * using serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Takes multiple variables that are required to create a case.
     *
     * @param id the id of the case
     * @param des the description of the case
     * @param process the process of the case
     * @param sw the socialworker assigned to the case
     * @param c the citizen whom the case is about
     */
    public Case(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        this.ID = id;
        this.description = des;
        this.process = process;
        this.socialWorker = sw;
        this.citizen = c;
    }

    @Override
    public String toString() {
        return String.format("%-22s %-30s %-30s", "SagsID: " + ID, citizen, "Sagsbehandler: " + this.socialWorker.getName());
//        return "SagsID: " + ID + " \t" + citizen + "\tSagsbehandler: " + this.socialWorker.getName();
    }

    @Override
    public ICitizen getCitizen() {
        return this.citizen;
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
    public String getProcess() {
        return process;
    }

    @Override
    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public ISocialWorker getSocialWorker() {
        return socialWorker;
    }

    @Override
    public void setSocialWorker(ISocialWorker socialWorker) {
        this.socialWorker = socialWorker;
    }

    @Override
    public void setCitizen(ICitizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public String getID() {
        return ID;

    }
}
