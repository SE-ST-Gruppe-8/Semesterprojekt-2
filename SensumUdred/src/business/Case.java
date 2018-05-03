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

    private final String ID;

    private String description;

    private String process;

    private ISocialWorker socialWorker;

    private ICitizen citizen;

    private static final long serialVersionUID = 1L;

    public Case(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        this.ID = id;
        this.description = des;
        this.process = process;
        this.socialWorker = sw;
        this.citizen = c;
    }

    public String toString() {
        return this.socialWorker.toString() + " " + ID + " " + citizen + " " + description;
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
    public String getId() {
        return ID;

    }
}
