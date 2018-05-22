package acq;

import business.Citizen;

/**
 *
 * @author Robin
 */
public interface IInquiry {

    /**
     * Gets the origin of the inquiry.
     * 
     * @return the inquiry's origin 
     */
    public String getOrigin();
    /**
     * Check's whether or not the citizen has been informed.
     * 
     * @return whether or not the citizen has been informed 
     */
    public boolean isCitizenInformed();
    /**
     * Gets the inquiry's id.
     * 
     * @return the inquiry's id
     */
    public String getId();
    /**
     * Gets the citizen to who the inquiry is about.
     * 
     * @return the citizen to who the inquiry is about 
     */
    public Citizen getCitizen();
    /**
     * Gets the inquiry's description.
     * 
     * @return the inquiry's description
     */
    public String getDescription();
    /**
     * Sets the inquiry's description.
     * 
     * @param description 
     */
    public void setDescription(String description);
    /**
     * Sets whether or not the citizen has been informed.
     * 
     * @param isInformed whether or not the citizen has been informed 
     */
    public void setIsCitizenInformed(boolean isInformed);
}
