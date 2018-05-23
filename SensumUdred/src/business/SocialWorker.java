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
 * @author Nikolaj Filipsen
 */
public class SocialWorker extends User implements ISocialWorker {

    /**
     * A Set of cases which the social worker is responsible for.
     */
    private Set<Case> cases;
    /**
     * A Set of inquiries which the social worker is responsible for.
     */
    private Set<Inquiry> inquiries;

    /**
     * A static final integer for SocialWorker. The integer is unique from other
     * subclasses of User.
     */
    private static final int ROLE = 1;

    /**
     * An ID which makes sure that problems won't occure with binary files when
     * using Serilization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A Constructor used for creating a SocialWorker.
     *
     * @param name the social worker's name.
     * @param id the social worker's ID.
     * @param username the social worker's username.
     * @param password the social worker's password.
     * @param email the social worker's email.
     */
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

    @Override
    public int getRole() {
        return 1;
    }

    /**
     * A static method that returns the integer representing the role of
     * SocialWorker.
     *
     * @return the role of a SocialWorker.
     */
    public static int getSWRole() {
        return ROLE;
    }

    @Override
    public ICitizen createCitizen(String name, String id, String needs) {
        Citizen citizen = new Citizen(name, id, needs);
        return citizen;
    }

    @Override
    public boolean deleteCitizen(ICitizen citizen, ObservableList<ICitizen> citizens) {
        if (citizens.contains(citizen)) {
            return citizens.remove(citizen);
        }
        return false;
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
