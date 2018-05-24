package acq;

import business.Case;
import business.Inquiry;

/**
 *
 * @author J
 */
public interface ICitizen {

    /**
     * get the citizen's inquiry
     * @return the citizen's inquiry
     */
    public IInquiry getInquiry();
    
    /**
     * get the citizen name
     * @return name of the citizen
     */
    public String getName();

    /**
     * get the id of the citizen
     * @return id of the citizen
     */
    public String getId();

    /**
     * get the needs of the citizen
     * @return needs of the citizen
     */
    public String getNeeds();

    /**
     * get the citizen's case
     * @return the citizen's case
     */
    public Case getCase();

    /**
     * sets the citizen's case
     * @param citizenCase the case you want to set as the case 
     */
    public void setCase(Case citizenCase);


    /**
     * set the inquiry of the citizen
     * @param inquiry the inquiry you want to set
     */
    public void setInquiry(Inquiry inquiry);
    
    /**
     * set the needs of the citizen
     * @param needs the needs you want to set
     */
    public void setNeeds(String needs);
}
