/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
import acq.IUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederik
 */
public class ControllerFXMLAdmin implements Initializable, IPresentation {

    private IBusiness ib;

    @FXML
    private Tab adminTab;

    @FXML
    private ListView<IUser> adminUserListView;

    @FXML
    private RadioButton createSocialWorkerRadioButton;

    @FXML
    private ToggleGroup createUserToggleGroup;

    @FXML
    private RadioButton createAdminRadioButton;

    @FXML
    private TextField adminUsernameTextField;

    @FXML
    private TextField adminFirstNameTextField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private TextField adminLastNameTextField;

    @FXML
    private TextField adminRepeatPasswordTextField;

    @FXML
    private TextField adminEmailTextField;

    @FXML
    private Button createUserButton;

    @FXML
    private Label adminInfoLabel;

    @FXML
    private Label loginInfoLabelAdmin;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button UpdateList;

    @FXML
    private TextField adminIdTextField;
    @FXML
    private Button logoutButtonSW;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ib = PresentationFacade.getIData().getIBusiness();
//        loginInfoLabelAdmin.setText("Logged in as: " + ib.getActiveUser().getName());
//        updateUserList();
    }

    @Override
    public void injectBusiness(IBusiness businessFacade) {
        ib = businessFacade;
    }

    @Override
    public void openUI() {
        loginInfoLabelAdmin.setText("Logged in as: " + ib.getActiveUser().getName());
        updateUserList();
    }

    @FXML
    private void createUserButtonAction(ActionEvent event) {
        int value;
        if (createAdminRadioButton.isSelected()) {
            value = 0;
        } else {
            value = 1;
        }
        String id = adminIdTextField.getText();
        // if (ib.hasUniqueUserID(id)) {
        if (true) { // brug ovenstående linje når hasUniqueUserID(id) er implementeret i DataFacade!!!
            ib.createUser(adminFirstNameTextField.getText() + " " + adminLastNameTextField.getText(), id,
                    adminUsernameTextField.getText(), adminPasswordTextField.getText(),
                    adminEmailTextField.getText(), value);
            System.out.println("Role: " + ib.getRole());
            updateUserList();
        }
    }

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        // TODO
        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
        updateUserList();
    }

    private void updateListAction(ActionEvent event) {
        updateUserList();
    }

    public void updateUserList() {
        if (ib.getUsers() == null) {
            adminInfoLabel.setText("no Users installed");
        } else {
            adminUserListView.setItems(ib.getUsers());
        }
    }

    @FXML
    private void logoutButtonAction(ActionEvent event) throws IOException {
        ib.logOutActiveUser();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLlogin.fxml"));

        GridPane gridPane = loader.load();
        IPresentation controller = loader.getController();
        controller.injectBusiness(ib);

        Scene scene2 = new Scene(gridPane);
        //Get Stage information

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setMinWidth(300);
        window.setMinHeight(200);
        window.setWidth(400);
        window.setHeight(300);
        window.setScene(scene2);
        window.show();

    }

    @FXML
    private void UpdateListAction(ActionEvent event) {
    }

}
