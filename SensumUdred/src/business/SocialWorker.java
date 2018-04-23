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

    public Pair<Boolean, String> createCase(String id, String description, String process,
            SocialWorker sw, Citizen c, Order o) {
        // TODO
        return new Pair<>(false, "Missing code");
    }

    public Pair<Boolean, String> createReference(String id, String socialInstance, String description) {
        // TODO
        return new Pair<>(false, "Missing code");
    }

    public Pair<Boolean, String> informCitizen(Case c) {
        // TODO
        return new Pair<>(false, "Missing code");
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

    public Pair<Boolean, String> addCase(Case c) {
        String trueMessage;
        String falseMessage;
        try {
            this.cases.add(c);
            trueMessage = "The case was added";
            return new Pair<>(true, trueMessage);
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
            return new Pair<>(false, falseMessage);
        }
    }

    public Pair<Boolean, String> addInquiry(Inquiry inq) {
        String trueMessage;
        String falseMessage;
        try {
            this.inquiries.add(inq);
            trueMessage = "The inquiry was added";
            return new Pair<>(true, trueMessage);
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
            return new Pair<>(false, falseMessage);
        }
    }

    public Pair<Boolean, String> addReference(Reference ref) {
        String trueMessage;
        String falseMessage;
        try {
            this.references.add(ref);
            trueMessage = "The reference was added";
            return new Pair<>(true, trueMessage);
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
            return new Pair<>(false, falseMessage);
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

    public Pair<Boolean, String> removeInquiry(Inquiry inq) {
        String trueMessage;
        String falseMessage;
        try {
            if (this.inquiries.contains(inq)) {
                this.inquiries.remove(inq);
                trueMessage = "The inquiry was removed";
                return new Pair<>(true, trueMessage);
            } else {
                falseMessage = "The inquiry was not found";
            }
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
        }
        return new Pair<>(false, falseMessage);
    }

    public Pair<Boolean, String> removeReference(Reference ref) {
        String trueMessage;
        String falseMessage;
        try {
            if (this.references.contains(ref)) {
                this.references.remove(ref);
                trueMessage = "The reference was removed";
                return new Pair<>(true, trueMessage);
            } else {
                falseMessage = "The reference was not found";
            }
        } catch (NullPointerException ex) {
            falseMessage = "An error occured";
        }
        return new Pair<>(false, falseMessage);
    }

}
