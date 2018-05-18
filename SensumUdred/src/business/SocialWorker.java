package business;

import acq.ICase;
import acq.ICitizen;
import acq.IInquiry;
import acq.ISocialWorker;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 *
 * @author Søren Bendtsen
 */
public class SocialWorker extends User implements ISocialWorker {

    private Set<Case> cases;
    private Set<Inquiry> inquiries;
    private Set<Citizen> citizens;
    private static final int ROLE = 1;
    private static final long serialVersionUID = 1L;

    public SocialWorker(String name, String id, String username, String password, String email) {
        super(name, id, username, password, email);
        this.cases = new HashSet<>();
        this.inquiries = new HashSet<>();
    }

    @Override
    public ICase createCase(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        ICase newCase = null;
        newCase = (ICase) new Case(id, des, process, sw, c);
        return newCase;
    }

    @Override
    public boolean deleteCase(ICase newCase) {
        boolean deleted = false;
        if (newCase.getCitizen() != null) {
            newCase.getCitizen().setCase(null);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public boolean informCitizen(Case c) {
        // TODO
        return false;
    }

    @Override
    public Set<Case> getCases() {
        return this.cases;
    }

    @Override
    public Set<Inquiry> getInquiries() {
        return this.inquiries;
    }

    @Override
    public boolean addCase(Case c) {
        try {
            this.cases.add(c);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    @Override
    public boolean addInquiry(Inquiry inq) {
        try {
            this.inquiries.add(inq);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }


//    public boolean removeCase(Case c) {
//        try {
//            if (this.cases.contains(c)) {
//                this.cases.remove(c);
//                return true;
//            }
//        } catch (NullPointerException ex) {
//        }
//        return false;
//    }
//
//    public boolean removeInquiry(Inquiry inq) {
//        try {
//            if (this.inquiries.contains(inq)) {
//                this.inquiries.remove(inq);
//                return true;
//            } else {
//            }
//        } catch (NullPointerException ex) {
//        }
//        return false;
//    }


    @Override
    public int getRole() {
        return 1;
    }
    
    public static int getSWRole(){
        return ROLE;
    }

    @Override
    public ICitizen createCitizen(String name, String id, String needs) {
        Citizen citizen = new Citizen(name, id, needs);
        return citizen;
    }

    @Override
    public boolean deleteCitizen(ICitizen citizen, ObservableList<ICitizen> citizens) {
        boolean citizenRemoved = false;
        if (citizens.contains(citizen)) {
            citizenRemoved = citizens.remove(citizen);
        }
        return citizenRemoved;
    }

    @Override
    public IInquiry createInquiry(String id, String origin, boolean informed, ICitizen citizen, String description) {
        Inquiry inquiry = new Inquiry(id, origin, informed, (Citizen) citizen, description);
        return inquiry;
    }

    @Override
    public boolean deleteInquiry(IInquiry inquiry) {
        if (inquiry != null) {
            inquiry.getCitizen().setInquiry(null);
            return true;
        }
        return false;
    }
}
