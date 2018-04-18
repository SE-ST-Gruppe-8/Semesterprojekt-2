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

    public SocialWorker(String name, String id, String username, String password, String email) {
        super(name, id, username, password, email);
        this.cases = new HashSet<>();
        this.inquiries = new HashSet<>();
        this.references = new HashSet<>();
    }

    public String createCase(/*String id, String description, String process,
            SocialWorker socialWorker, Citizen citizen, Order order*/) {
        // TODO
        return "testing";
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

    public Pair<Boolean, String> removeCase(Case c) {
        String trueMessage;
        String falseMessage;
        try {
            if (this.cases.contains(c)) {
                this.cases.remove(c);
                trueMessage = "The case was removed";
                return new Pair<>(true, trueMessage);
            } else {
                falseMessage = "The case was not found";
            }
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
        }
        return new Pair<>(false, falseMessage);
    }

    public boolean removeInquiry(Inquiry inq) {
        try {
            if (this.inquiries.contains(inq)) {
                this.inquiries.remove(inq);
                return true;
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
