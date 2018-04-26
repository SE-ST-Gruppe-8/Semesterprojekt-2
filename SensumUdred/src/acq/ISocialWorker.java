package acq;

import business.Case;
import business.Citizen;
import business.Inquiry;
import business.Reference;
import business.SocialWorker;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 *
 * @author Robin
 */
public interface ISocialWorker {

    public ICase createCase(String id, String des, String process, ISocialWorker sw, ICitizen c);

    public boolean deleteCase(ICase newCase, ObservableList<ICase> cases);

    public boolean createReference(String id, String socialInstance, String description);

    public boolean informCitizen(Case c);

    public Set<Case> getCases();

    public Set<Inquiry> getInquiries();

    public Set<Reference> getReferences();

    public boolean addCase(Case c);

    public boolean addInquiry(Inquiry inq);

    public boolean addReference(Reference ref);

    public boolean removeCase(Case c);

    public boolean removeInquiry(Inquiry inq);

    public boolean removeReference(Reference ref);

    public int getRole();

    public ICitizen createCitizen(String name, String id, String needs);

    public boolean deleteCitizen(ICitizen citizen, ObservableList<ICitizen> citizens);

    public IInquiry createInquiry(String id, String origin, boolean informed, ICitizen citizen, String description);

    public boolean deleteInquiry(IInquiry inquiry, ObservableList<IInquiry> inquiries);

}
