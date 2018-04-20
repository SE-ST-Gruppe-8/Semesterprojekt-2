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

    @FXML
    private Tab loginUsernameTextField;
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
    
    private PresentationFacade pf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ib = PresentationFacade.getIData().getIBusiness();
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        // TODO
        ib.test();
    }

    @FXML
    private void logoutButtonAction(ActionEvent event) {
        // TODO
    }

    @FXML
    private void createUserButtonAction(ActionEvent event) {
        // TODO
    }

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        // TODO
    }

    @FXML
    private void UpdateListAction(ActionEvent event) {
        System.out.println(ib);
        if (ib.getUsers() == null) {
            adminInfoLabel.setText("no Users installed");
        } else {
            adminUserListView.setItems(ib.getUsers());
        }
    }

}
