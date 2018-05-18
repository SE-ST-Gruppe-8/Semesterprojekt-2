package acq;

import business.Case;
import business.Inquiry;

/**
 *
 * @author J
 */
public interface ICitizen {

    public IInquiry getInquiry();

    public String getName();

    public String getId();

    public String getNeeds();

    public Case getCase();

    public void setCase(Case citizenCase);

    public void createInquiry(String id, String origin, boolean informed, String description);

    public void setInquiry(Inquiry inquiry);

    public void setNeeds(String needs);
}
