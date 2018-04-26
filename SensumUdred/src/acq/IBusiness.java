/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import business.Citizen;
import business.SocialWorker;
import business.User;
import javafx.collections.ObservableList;

/**
 *
 * @author Bruger
 */
public interface IBusiness {

    void injectData(IData dataLayer);

    public void createUser(String name, String id, String userName, String password, String email, int type);

    public void createCase(String id, String des, String process, ISocialWorker sw, ICitizen c);

    public void deleteUser(IUser user);

    public boolean validateUser(String username, String password);

    public ObservableList<IUser> getUsers();

    public ObservableList<IInquiry> getInquiries();

    public void saveInquiry(IInquiry inquiry);

    public void logOutActiveUser();

    public int getRole();

    public User getActiveUser();

    public ObservableList<ICase> getCases();

    public void deleteCase(ICase newCase);

    public void createCitizen(String name, String id, String needs);

    public ObservableList<ICitizen> getCitizen();

    public void deleteCitizen(ICitizen citizen);

}
