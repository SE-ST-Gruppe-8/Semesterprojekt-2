/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

/**
 *
 * @author Frederik
 */
public interface ICase {

    /** 
     * Gets the citizen whom the case is about.
     * 
     * @return the citizen whom the case is about.
     */
    public ICitizen getCitizen();
    /**
     * Gets the case's description.
     * 
     * @return the case's description
     */
    public String getDescription();
    /**
     * Gets the case's id.
     * 
     * @return the case's id
     */
    public String getId();
    /**
     * Sets the description of the case.
     * 
     * @param description the description of the case
     */
    public void setDescription(String description);
    /**
     * Gets the case's process.
     * 
     * @return the case's process
     */
    public String getProcess();
    /**
     * Sets the case's process.
     * 
     * @param process 
     */
    public void setProcess(String process);
    /**
     * Gets the social worker who is assigned to the case.
     * 
     * @return the case's socialworker 
     */
    public ISocialWorker getSocialWorker();
    /**
     * Sets the case's socialworker.
     * 
     * @param socialWorker the case's socialworker
     */
    public void setSocialWorker(ISocialWorker socialWorker);
    /**
     * Sets the case's citizen
     * 
     * @param citizen the citizen
     */
    public void setCitizen(ICitizen citizen);
}
