package business;

import acq.ICase;
import acq.ICitizen;
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

    private Set<Reference> references;

    private Set<Citizen> citizens;

    private static final long serialVersionUID = 1L;

    public SocialWorker(String name, String id, String username, String password, String email) {
        super(name, id, username, password, email);
        this.cases = new HashSet<>();
        this.inquiries = new HashSet<>();
        this.references = new HashSet<>();
    }

    @Override
    public ICase createCase(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        ICase newCase = null;
        newCase = (ICase) new Case(id, des, process, sw, c);
        return newCase;
    }

    public boolean deleteCase(ICase newCase, ObservableList<ICase> cases) {
        boolean deleted = false;
        if (cases.contains(newCase)) {
            deleted = cases.remove(newCase);
        }
        return deleted;
    }

    public boolean createReference(String id, String socialInstance, String description) {
        // TODO
        return false;
    }

    public boolean informCitizen(Case c) {
        // TODO
        return false;
    }

    public Set<Case> getCases() {
        return this.cases;
    }

    public Set<Inquiry> getInquiries() {
        return this.inquiries;
    }

    public Set<Reference> getReferences() {
        return this.references;
    }

    public boolean addCase(Case c) {
        try {
            this.cases.add(c);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean addInquiry(Inquiry inq) {
        try {
            this.inquiries.add(inq);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean addReference(Reference ref) {
        try {
            this.references.add(ref);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean removeCase(Case c) {
        try {
            if (this.cases.contains(c)) {
                this.cases.remove(c);
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    public boolean removeInquiry(Inquiry inq) {
        try {
            if (this.inquiries.contains(inq)) {
                this.inquiries.remove(inq);
                return true;
            } else {
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    public boolean removeReference(Reference ref) {
        try {
            if (this.references.contains(ref)) {
                this.references.remove(ref);
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    @Override
    public int getRole() {
        return 1;
    }

    @Override
    public ICitizen createCitizen(String name, String id, String needs) {
        Citizen citizen = new Citizen(name, id, needs);
        return citizen;
    }

    public boolean deleteCitizen(ICitizen citizen, ObservableList<ICitizen> citizens) {
        boolean citizenRemoved = false;
        if (citizens.contains(citizen)) {
            citizenRemoved = citizens.remove(citizen);
        }
        return citizenRemoved;
    }
    
     
}
