package acq;

import business.Citizen;
import business.InquiryMaker;

/**
 *
 * @author Robin
 */
public interface IInquiry {
    public String getOrigin();

    public boolean isCitizenInformed();

    public String getId();

    public Citizen getCitizen();
    
    public void setInquiryMaker(InquiryMaker inquiryMaker);
    
    public boolean hasInquiryMaker();
    
    public InquiryMaker getInquiryMaker();
}
