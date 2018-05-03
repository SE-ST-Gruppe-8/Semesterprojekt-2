package acq;

import business.Citizen;

/**
 *
 * @author Robin
 */
public interface IInquiry {
    public String getOrigin();

    public boolean isCitizenInformed();

    public String getId();

    public Citizen getCitizen();
    
    public String getDescription();

    public void setDescription(String description);
}
