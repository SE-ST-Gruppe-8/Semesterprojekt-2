/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
import acq.IUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
    private AlertBox ab; 
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
    private ListView<IUser> adminUserListView;
    @FXML
    private Button UpdateList;
    @FXML
    private Tab loginTab;
    @FXML
    private Tab socialTab;
    @FXML
    private Tab adminTab;
    @FXML
    private Label loginInfoLabel;

    private PresentationFacade pf;
    @FXML
    private ListView<?> adminUserListView1;
    @FXML
    private Button deleteUserButton1;
    @FXML
    private Button UpdateList1;
    @FXML
    private Button swCasesCreateCaseButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ib = PresentationFacade.getIData().getIBusiness();
        socialTab.setDisable(true);
        adminTab.setDisable(true);
        UpdateList();
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        // TODO
        boolean iscorrect = ib.validateUser(loginUsernameTextField.getText(), loginPasswordTextField.getText());

        if (iscorrect) {
            loginInfoLabel.setText("Succesfully logged in as: " + loginUsernameTextField.getText());
            if (ib.getRole() == 1)
                socialTab.setDisable(false);
            else if (ib.getRole() == 0)
                adminTab.setDisable(false);
        } else {
            loginInfoLabel.setText("Wrong input");
        }
        
    }

    @FXML
    private void logoutButtonAction(ActionEvent event) {
        // TODO
        ib.logOutActiveUser();
        adminTab.setDisable(true);
        socialTab.setDisable(true);
        loginInfoLabel.setText("You have logged out.");

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
        UpdateList();
    }

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        // TODO
        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
        UpdateList();
    }

    @FXML
    private void UpdateListAction(ActionEvent event) {
        UpdateList();
    }

    public void UpdateList() {
        if (ib.getUsers() == null) {
            adminInfoLabel.setText("no Users installed");
        } else {
            adminUserListView.setItems(ib.getUsers());
        }
    }

    @FXML
    private void CreateCase(ActionEvent event) {
    }
}
