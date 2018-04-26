/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.ICase;
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
public class ControllerFXMLDocumentController implements Initializable {

    private IBusiness ib;
    private AlertBox ab;

    private TextField loginUsernameTextField;
    private TextField loginPasswordTextField;
    private Button loginButton;
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
    private ListView<IUser> adminUserListView;
    @FXML
    private ListView<ICase> caseListView;
    @FXML
    private Button UpdateList;
    private Tab socialTab;
    @FXML
    private Tab adminTab;
    private Label loginInfoLabel;

    private PresentationFacade pf;
    @FXML
    private Button swCasesCreateCaseButton;
    @FXML
    private Button swDeleteCaseListButton;
    @FXML
    private Button swUpdateCaseListButton;
    @FXML
    private Tab casesTab;
    @FXML
    private Tab inquiriesTab;
    @FXML
    private Tab referenceTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ib = PresentationFacade.getIData().getIBusiness();
        casesTab.setDisable(true);
        inquiriesTab.setDisable(true);
        referenceTab.setDisable(true);
        adminTab.setDisable(true);
        updateUserList();
        updateCaseList();
        logoutButton.setDisable(true);
    }

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
        updateUserList();
    }

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        // TODO
        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
        updateUserList();
    }

    @FXML
    private void UpdateListAction(ActionEvent event) {
        updateUserList();
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
//            sw.setText("no Users installed");
        } else {
            caseListView.setItems(ib.getCases());
        }
    }

    @FXML
    private void UpdateCaseListAction(ActionEvent event) {
    }

    @FXML
    private void createCaseAction(ActionEvent event) {
        ab = new AlertBox();
        updateCaseList();
        ab.display("Create case", ib);
        updateCaseList();

    }

    @FXML
    private void DeleteCaseAction(ActionEvent event) {
        ib.deleteCase(caseListView.getSelectionModel().getSelectedItem());
        updateCaseList();
    }
}
