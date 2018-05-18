package acq;

import business.Case;
import business.Inquiry;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 *
 * @author Robin
 */
public interface ISocialWorker extends IUser {

    /**
     * Creates a new ICase.
     *
     * @param id the ID of the ICase.
     * @param des the description of the ICase.
     * @param process the process of the ICase.
     * @param sw the ISocialWorker responsible for the ICase.
     * @param c the ICitizen belonging to the ICase.
     * @return a newly created ICase.
     */
    public ICase createCase(String id, String des, String process, ISocialWorker sw, ICitizen c);

    public boolean deleteCase(ICase newCase);

    public boolean informCitizen(Case c);

    public Set<Case> getCases();

    public Set<Inquiry> getInquiries();

    public boolean addCase(Case c);

    public boolean addInquiry(Inquiry inq);

//    public boolean removeCase(Case c);
//
//    public boolean removeInquiry(Inquiry inq);
//
//    public boolean removeReference(Reference ref);
    @Override
    public int getRole();

    public ICitizen createCitizen(String name, String id, String needs);

    public boolean deleteCitizen(ICitizen citizen, ObservableList<ICitizen> citizens);

    public IInquiry createInquiry(String id, String origin, boolean informed, ICitizen citizen, String description);

    public boolean deleteInquiry(IInquiry inquiry);

}
