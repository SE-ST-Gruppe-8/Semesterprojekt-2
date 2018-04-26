/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.ICase;
import acq.ICitizen;
import acq.IInquiry;
import acq.IUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Robin
 */
public class FXMLDocumentController implements Initializable {

    private IBusiness ib;
    private AlertBox ab = new AlertBox();

    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private TextField loginPasswordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private RadioButton createSocialWorkerRadioButton;
    @FXML
    private RadioButton createAdminRadioButton;
    @FXML
    private ToggleGroup createUserToggleGroup;
    @FXML
    private TextField adminUsernameTextField;
    @FXML
    private TextField adminPasswordTextField;
    @FXML
    private TextField adminRepeatPasswordTextField;
    @FXML
    private TextField adminFirstNameTextField;
    @FXML
    private TextField adminLastNameTextField;
    @FXML
    private TextField adminEmailTextField;
    @FXML
    private Button createUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Label adminInfoLabel;
    @FXML
    private Button updateUserListButton;
    @FXML
    private ListView<IUser> adminUserListView;
    @FXML
    private ListView<ICase> caseListView;
    @FXML
    private ListView<ICitizen> citizenListView;
    @FXML
    private Tab loginTab;
    @FXML
    private Tab adminTab;
    @FXML
    private Tab casesTab;
    @FXML
    private Tab inquiriesTab;
    @FXML
    private Tab referenceTab;
    @FXML
    private Label loginInfoLabel;
    @FXML
    private Button createCaseButton;
    @FXML
    private Button deleteCaseButton;
    @FXML
    private Button updateCaseButton;
    @FXML
    private Label caseLabel;
    @FXML
    private Button editCaseButton;
    @FXML
    private Button deleteCitizenButton;
    @FXML
    private Button updateCitizenButton;
    @FXML
    private Button createInquiryButton;
    @FXML
    private Button editInquiryButton;
    @FXML
    private Button deleteInquiryButton;
    @FXML
    private Label inquiryLabel;
    @FXML
    private ListView<IInquiry> inquiryListView;
    @FXML
    private Button updateInquiryListButton;
    @FXML
    private Label inquiriesLabel;
    @FXML
    private Button createCitizenButton;
    @FXML
    private Button editCitizenButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ib = PresentationFacade.getIData().getIBusiness();
        casesTab.setDisable(true);
        inquiriesTab.setDisable(true);
        referenceTab.setDisable(true);
        adminTab.setDisable(true);
        logoutButton.setDisable(true);
        updateAllLists();

    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        // TODO
        boolean iscorrect = ib.validateUser(loginUsernameTextField.getText(), loginPasswordTextField.getText());

        if (iscorrect) {
            loginInfoLabel.setText("Succesfully logged in as: " + ib.getActiveUser().getName());
            loginButton.setDisable(true);
            logoutButton.setDisable(false);
            if (ib.getRole() == 1) {
                casesTab.setDisable(false);
                inquiriesTab.setDisable(false);
                referenceTab.setDisable(false);
            } else if (ib.getRole() == 0) {
                adminTab.setDisable(false);
            }
        } else {
            loginInfoLabel.setText("Wrong input");
        }

    }

    @FXML
    private void logoutButtonAction(ActionEvent event) {
        // TODO

        ib.logOutActiveUser();
        adminTab.setDisable(true);
        casesTab.setDisable(true);
        inquiriesTab.setDisable(true);
        referenceTab.setDisable(true);
        loginInfoLabel.setText("You have logged out.");
        logoutButton.setDisable(true);
        loginButton.setDisable(false);

    }

    @FXML
    private void createUserButtonAction(ActionEvent event) {
        // TODO
        int value;
        if (createAdminRadioButton.isSelected()) {
            value = 0;
        } else {
            value = 1;
        }
        ib.createUser(adminFirstNameTextField.getText() + " " + adminLastNameTextField.getText(), "test ID",
                adminUsernameTextField.getText(), adminPasswordTextField.getText(),
                adminEmailTextField.getText(), value);
//        updateUserList();
    }

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
        updateUserList();
    }

    @FXML
    private void createCaseAction(ActionEvent event) {
        updateCaseList();
        if (citizenListView.getSelectionModel().getSelectedItem() != null) {
            ab.display("Create case", ib, (ICitizen) citizenListView.getSelectionModel().getSelectedItem());
        } else {
            caseLabel.setText("you must select a Citizen from the list to create a case");
        }
        updateCaseList();

    }

    @FXML
    private void editCaseAction(ActionEvent event) {
    }

    @FXML
    private void deleteCaseAction(ActionEvent event) {
        ib.deleteCase(caseListView.getSelectionModel().getSelectedItem());
        updateCaseList();
    }

    @FXML
    private void createCitizenButtonAction(ActionEvent event) {

        updateCitizenList();
        ab.display("Create citizen", ib);
        updateCitizenList();
    }

    @FXML
    private void editCitizenButtonAction(ActionEvent event) {
    }

    @FXML
    private void deleteCitizenAction(ActionEvent event) {
        ib.deleteCitizen(citizenListView.getSelectionModel().getSelectedItem());
        updateCitizenList();
    }

    @FXML
    private void createInquiryAction(ActionEvent event) {
    }

    @FXML
    private void editInquiryAction(ActionEvent event) {
    }

    @FXML
    private void deleteInquiryAction(ActionEvent event) {

    }

    public void updateInquiryList() {
        if (ib.getInquiries() == null) {
            inquiryLabel.setText("no Inquiries installed");
        } else {
            inquiryListView.setItems(ib.getInquiries());
        }
    }

    public void updateCitizenList() {
        if (ib.getCitizen() == null) {
            inquiryLabel.setText("no Inquiries installed");
        } else {
            citizenListView.setItems(ib.getCitizen());
        }
    }

    public void updateUserList() {
        if (ib.getUsers() == null) {
            adminInfoLabel.setText("no Users installed");
        } else {
            adminUserListView.setItems(ib.getUsers());
        }
    }

    public void updateCaseList() {
        if (ib.getCases() == null) {
            caseLabel.setText("no Cases installed");
        } else {
            caseListView.setItems(ib.getCases());
        }
    }

    @FXML
    private void updateInquiryListAction(ActionEvent event) {
        updateInquiryList();
    }

    @FXML
    private void updateCaseListAction(ActionEvent event) {
        updateCaseList();
    }

    @FXML
    private void updateUserListAction(ActionEvent event) {
        updateUserList();
    }

    @FXML
    private void updateCitizenListAction(ActionEvent event) {
        updateCitizenList();
    }

    public void updateAllLists() {
        updateUserList();
        updateCitizenList();
        updateCaseList();
        updateInquiryList();
    }

}
