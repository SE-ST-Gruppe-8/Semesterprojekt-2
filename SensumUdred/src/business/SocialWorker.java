package business;

import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;

/**
 *
 * @author Søren Bendtsen
 */
public class SocialWorker extends User {

    private Set<Case> cases;
    private Set<Inquiry> inquiries;
    private Set<Reference> references;
    private static final long serialVersionUID = 1L;

    public SocialWorker(String name, String id, String username, String password, String email) {
        super(1, name, id, username, password, email);
        this.cases = new HashSet<>();
        this.inquiries = new HashSet<>();
        this.references = new HashSet<>();
    }

    public boolean createCase(String id, String description, String process,
            SocialWorker sw, Citizen c, Order o) {
        // TODO
        return false;
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

}
