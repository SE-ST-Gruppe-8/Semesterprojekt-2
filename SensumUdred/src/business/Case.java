/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.ICase;

/**
 *
 * @author Bruger
 */
public class Case implements ICase {

    private final String ID;

    private String description;

    private String process;

    private SocialWorker socialWorker;

    private Citizen citizen;

    public Case(String id, String des, String process, SocialWorker sw, Citizen c) {
        this.ID = id;
        this.description = des;
        this.process = process;
        this.socialWorker = sw;
        this.citizen = c;
    }

}
