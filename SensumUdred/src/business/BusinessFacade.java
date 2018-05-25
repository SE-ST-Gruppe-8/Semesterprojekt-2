package business;

import acq.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nikolaj Filipsen
 */
public class BusinessFacade implements IBusiness {

    /**
     * The only allowed length of ID's for users and citizens.
     */
    private static final int ID_LENGTH = 10;

    /**
     * The shortest allowed length of passwords for users.
     */
    private static final int PASSWORD_MIN_LENGTH = 4;
    /**
     * The longest allowed length of passwords for users.
     */
    private static final int PASSWORD_MAX_LENGTH = 16;

    /**
     * The shortest allowed length of usernames for users.
     */
    private static final int USERNAME_MIN_LENGTH = 4;

    /**
     * The longest allowed length of usernames for users.
     */
    private static final int USERNAME_MAX_LENGTH = 16;

    /**
     * The shortest allowed length of names for users and citizens.
     */
    private static final int NAME_MIN_LENGTH = 3;

    /**
     * The longest allowed length of names for users and citizens.
     */
    private static final int NAME_MAX_LENGTH = 100;

    /**
     * The longest allowed length of mails for users and citizens.
     */
    private static final int MAIL_MAX_LENGTH = 50;

    /**
     * The data layer.
     */
    private IData data;

    /**
     * A SecurityHandler.
     */
    private SecurityHandler security;

    /**
     * A list of all IUser objects.
     */
    private ObservableList<IUser> users;

    /**
     * A list of all IInquiry objects.
     */
    private ObservableList<IInquiry> inquiries = FXCollections.observableArrayList();

    /**
     * A list of alle ICitizen objects.
     */
    private ObservableList<ICitizen> citizens = FXCollections.observableArrayList();

    /**
     * A list of all ICase objects.
     */
    private ObservableList<ICase> cases = FXCollections.observableArrayList();

    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
        security = new SecurityHandler(data);
    }

    @Override
    public ObservableList<ICase> getCases() {
        return this.cases;
    }

    @Override
    public ObservableList<IUser> getUsers() {
        ArrayList<IUser> users = new ArrayList<>();
//          FileManager
//          data.loadData(users, "users");
//          return this.users = FXCollections.observableArrayList(users);
//          SQL
        List<String[]> list = data.readUsers();
        for (String[] array : list) {
            User user = null;
            if (Integer.parseInt(array[5]) == SystemAdmin.getAdminRole()) {
                user = new SystemAdmin(array[0], array[1], array[2], array[3], array[4]);
            } else if (Integer.parseInt(array[5]) == SocialWorker.getSWRole()) {
                user = new SocialWorker(array[0], array[1], array[2], array[3], array[4]);
            }
            if (user != null) {
                users.add(user);
            }
        }
        return this.users = FXCollections.observableArrayList(users);
    }

    @Override
    public ObservableList<ICitizen> getCitizen() {
        return this.citizens;
    }

    @Override
    public ObservableList<IInquiry> getInquiries() {
        return this.inquiries;
    }

    @Override
    public void logOutActiveUser() {
        security.logData("Logged out.");
        security.logOutActiveUser();
    }

    @Override
    public void createUser(String name, String id, String userName, String password, String email, int type) {
        IUser user;
        if (security.getActiveUser() instanceof SystemAdmin) {
            user = ((SystemAdmin) security.getActiveUser()).createUser(name, id, userName, password, email, type);
            users.add(user);
            data.saveUsers(user);
//            if (user != null) {
//                ArrayList<IUser> users = data.readUsers();
//                users.add(user);
//                data.saveUsers(users);
//                users.add(user);
//                data.saveData((ArrayList<IUser>) users.stream().collect(Collectors.toList()), "users");
            security.logData("Created user: " + user.toString());
//            }
        } else {
            System.out.println("Error: Could not create user");
        }
    }

    @Override
    public void deleteUser(IUser user) {
        if (security.getActiveUser() instanceof SystemAdmin) {
            if (((SystemAdmin) security.getActiveUser()).deleteUser(user, users)) {
                security.logData("Deleted user: " + user.toString());
                data.deleteUser(user);
            } else {
                System.out.println("User does not exist");
            }
        }
    }

    @Override
    public boolean validateUser(String username, String password) {
        /*ArrayList<IUser> users = new ArrayList<>();
        data.loadData(users, "users");
        if (security.validateUserLogin(users, username, password)) {
            security.logData("Logged in.");
            return true;

        } else {

            return false;
        }*/

        String[] array = data.loadUser(username);;
        if (array[0] != null) {
            array[3] = array[3].trim();
            password = password.trim();
            if (security.validateUserlogin(array, password)) {
                security.logData("Logged in.");
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void saveInquiry(IInquiry inquiry) {
//        ArrayList<ICitizen> citizenList = new ArrayList<>();
//        data.loadData(citizenList, "citizens");
//        Citizen c = inquiry.getCitizen();
//        citizenList.remove(c);
//        c.setInquiry((Inquiry) inquiry);
//        citizenList.add(c);
//        security.logData("Saved inquiry: " + c.toString());
//        data.saveData(citizenList, "citizens");
//    }
    @Override
    public int getRole() {
        return security.getActiveUser().getRole();
    }

//                  Outdated createCase method.
//    @Override
//    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c) {
//        String s = "error, could not create case";
//        ICase newCase;
//        if (security.getActiveUser() instanceof SocialWorker) {
//            newCase = ((ISocialWorker) security.getActiveUser()).createCase(id, des, process, sw, c);
//            c.setCase((Case) newCase);
//            if (newCase != null) {
//                if (c.getCase() == null) {
//                    cases.add(newCase);
//                    c.setCase((Case) newCase);
//                } else {
//
//                    cases.remove(c.getCase());
//                    c.setCase((Case)newCase);
//                    cases.add(newCase);
//                }
//
//                data.saveData((ArrayList<ICitizen>) citizenList.stream().collect(Collectors.toList()), "citizenList");
//                security.logData("Created case with id: " + id);
//            } else {
//                System.out.println(s);
//            }
//
//        }
//        System.out.println(c.getName() + id);
//    }
    @Override
    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c) {
        String s = "Error, could not create case";
        ICase newCase;
        if (security.getActiveUser() instanceof SocialWorker) {
            newCase = ((ISocialWorker) security.getActiveUser()).createCase(id, des, process, sw, c);
            c.setCase((Case) newCase);
            cases.add(newCase);
            data.saveCase(newCase);
            security.logData("Created case with id: " + id);
        }
    }

    @Override
    public User getActiveUser() {
        return security.getActiveUser();
    }

    @Override
    public void deleteCase(ICase newCase) {
        if (security.getActiveUser() instanceof SocialWorker) {
            if (((SocialWorker) security.getActiveUser()).deleteCase(newCase)) {
                security.logData("Deleted case " + newCase.toString());
                cases.remove(newCase);
                data.deleteCase(newCase);
            } else {
                System.out.println("Case did not exist");
            }
        }
    }

    @Override
    public void createCitizen(String name, String id, String needs) {
//        ICitizen citizen;
//        String s = "Error with Citizen";
//        if (security.getActiveUser() instanceof SocialWorker) {
//            citizen = ((ISocialWorker) security.getActiveUser()).createCitizen(name, id, needs);
//            if (citizen != null) {
//                citizenList.add(citizen);
//                data.saveData((ArrayList<ICitizen>) citizenList.stream().collect(Collectors.toList()), "citizenList");
//                security.logData("Created Citizen: " + citizen.toString());
//            } else {
//                System.out.println(s);
//            }
//
//        }

        ICitizen citizen;
        String s = "Error with Citizen";
        if (security.getActiveUser() instanceof SocialWorker) {
            System.out.println(id);
            citizen = ((ISocialWorker) security.getActiveUser()).createCitizen(name, id, needs);
            if (citizen != null) {
                citizens.add(citizen);
                data.saveCitizen(citizen);
                security.logData("Created Citizen: " + citizen.toString());
            } else {
                System.out.println(s);
            }
        }
    }

    @Override
    public void deleteCitizen(ICitizen citizen
    ) {
        String s = "Error with Citizen";
        if (security.getActiveUser() instanceof SocialWorker) {
            if (((SocialWorker) security.getActiveUser()).deleteCitizen(citizen, citizens)) {
                security.logData("Deleted citizens: " + citizen.toString());
                data.deleteCitizen(citizen);
            } else {
                System.out.println(s);
            }
        }
    }

    @Override
    public void createInquiry(String id, String origin,
            boolean informed, ICitizen citizen,
            String description
    ) {
//        IInquiry inquiry;
//        String s = "Error with Citizen";
//        if (security.getActiveUser() instanceof SocialWorker) {
//            inquiry = ((ISocialWorker) security.getActiveUser()).createInquiry(id, origin, informed, citizen, description);
//            if (citizen != null) {
//                citizen.setInquiry((Inquiry) inquiry);
//                data.saveData((ArrayList<ICitizen>) citizenList.stream().collect(Collectors.toList()), "Citizens");
//                security.logData("Created Inquiry: " + citizen.getInquiry().toString());
//            } else {
//                System.out.println(s);
//            }
//
//        }

        IInquiry inquiry;
        String s = "Error";
        int index = inquiries.indexOf(citizen.getInquiry());

        if (security.getActiveUser() instanceof SocialWorker) {
            inquiry = ((ISocialWorker) security.getActiveUser()).createInquiry(id, origin, informed, citizen, description);
            if (citizen.getInquiry() == null) {
                citizen.setInquiry((Inquiry) inquiry);
                inquiries.add(inquiry);
                data.saveInquiry(inquiry);
                security.logData("Created Inquiry: " + citizen.getInquiry().toString());
            } else {
                System.out.println(s);
            }
        }
    }

    @Override
    public void deleteInquiry(IInquiry i
    ) {
        String s = "Error with Citizen";
        if (security.getActiveUser() instanceof SocialWorker) {
            if (((SocialWorker) security.getActiveUser()).deleteInquiry(i)) {
                // delete the case connected to the inquiry
                if (i.getCitizen().getCase() != null) {
                    deleteCase(i.getCitizen().getCase());
                }

                inquiries.remove(i);
                data.deleteInquiry(i);
                security.logData("Deleted inquiry: " + i.toString());
            } else {
                System.out.println(s);
            }
        }
    }

    @Override
    public void editCase(String description, String process,
            ICase c
    ) {
        c.setDescription(description);
        c.setProcess(process);
        security.logData("Edited case: " + c.toString());
        data.updateCase(c);
    }

    @Override
    public void editCitizen(String needs, ICitizen c
    ) {
        c.setNeeds(needs);
        security.logData("Edited citizen: " + c.toString());
        data.updateCitizen(c);
    }

    @Override
    public void editInquiry(String description, IInquiry i,
            boolean isInformed
    ) {
        i.setDescription(description);
        i.setIsCitizenInformed(isInformed);
        security.logData("Edited inquiry: " + i.toString());
        data.updateInquiry(i);
    }

    @Override
    public boolean hasUniqueUserID(String id
    ) {
        return data.hasUniqueUserID(id);
    }

    @Override
    public boolean hasUnqiueCitizenID(String id
    ) {
        return data.hasUniqueCitizenID(id);
    }

    @Override
    public boolean hasUniqueUsername(String username
    ) {
        return data.hasUniqueUsername(username);
    }

    @Override
    public boolean hasAcceptableID(String id
    ) {
        try {
            if (id.length() == ID_LENGTH) {
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    @Override
    public boolean hasAcceptablePassword(String password, String repeatedPassword
    ) {
        try {
            if (password.equals(repeatedPassword)) {
                if (password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH) {
                    return true;
                }
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    @Override
    public boolean hasAcceptableUsername(String username
    ) {
        try {
            if (username.length() >= USERNAME_MIN_LENGTH && username.length() <= USERNAME_MAX_LENGTH) {
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    @Override
    public boolean hasAcceptableMail(String mail
    ) {
        try {
            if (mail.length() <= MAIL_MAX_LENGTH && mail.contains("@")) {
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    @Override
    public boolean hasAcceptableName(String name
    ) {
        try {
            if (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH) {
                return true;
            }
        } catch (NullPointerException ex) {
        }
        return false;
    }

    /**
     * Returns an array of static final integers. The array contains: ID_LENGTH,
     * PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH, USERNAME_MIN_LENGTH,
     * USERNAME_MAX_LENGTH, NAME_MIN_LENGTH, NAME_MAX_LENGTH, MAIL_MAX_LENGTH.
     *
     * @return an array of static final integers.
     */
    @Override
    public int[] getFinalInts() {
        return new int[]{ID_LENGTH, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH, USERNAME_MIN_LENGTH,
            USERNAME_MAX_LENGTH, NAME_MIN_LENGTH, NAME_MAX_LENGTH, MAIL_MAX_LENGTH};
    }

    @Override
    public int getSocialWorkerRoleInt() {
        return SocialWorker.getSWRole();
    }

    @Override
    public int getAdminRoleInt() {
        return SystemAdmin.getAdminRole();
    }

    @Override
    public void processStuff() {
        // clear lists to avoid duplicates
        clearLists();
        // pull data from database
        List<String[]> rawData = data.getCitizenData();
        // for every row of data obtained
        for (String[] s : rawData) {
            // classes to be instantiated with data from db
            SocialWorker sw;
            Citizen c;
            Inquiry i;
            Case ca;
            // create citizen
            c = new Citizen(s[1], s[0], s[2]);
            // create socialworker
            if (s[3] == null || s[3].equals("null")) { // if citizen does not have an inquiry
                i = null; // set citizen's inquiry to null
            } else {
                // instantiate inquiry
                i = new Inquiry(s[3], s[6], Boolean.getBoolean(s[5]), c, s[4]);
                c.setInquiry(i);
                inquiries.add(i); //add to list of inquiries
                // check if the citizen/inquiry has a case
                if (s[7] == null || s[7].equals("null")) {
                    System.out.println("meow " + s[7]);
                    ca = null;
                } else {
                    // a socialworker will be connected to he case if it exists
                    sw = new SocialWorker(s[11], s[10], s[13], s[14], s[12]);
                    ca = new Case(s[7], s[8], s[9], sw, c);
                    c.setCase(ca);
                    cases.add(ca);
                }
            }
            // finally - add citizen to list of citizens
            citizens.add(c);
        }
    }

    @Override
    public void clearLists() {
        citizens.clear();
        inquiries.clear();
        cases.clear();
    }

    @Override
    public List<String> getLog() {
        return data.getLog();
    }

}
