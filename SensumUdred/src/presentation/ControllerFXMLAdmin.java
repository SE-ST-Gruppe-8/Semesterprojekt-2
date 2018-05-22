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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.TextArea;
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
    private boolean logUpdatedOnce;

    @FXML
    private Tab adminTab;
    @FXML
    private ListView<IUser> adminUserListView;
    @FXML
    private ToggleGroup createUserToggleGroup;
    @FXML
    private RadioButton createSocialWorkerRadioButton;
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
    private Button updateList;
    @FXML
    private TextField adminIdTextField;
    @FXML
    private Button logoutButtonSW;
    @FXML
    private Button UpdateLogButton;
    @FXML
    private TextArea logTextArea;

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
//        createSocialWorkerRadioButton.setToggleGroup(createUserToggleGroup);
//        createAdminRadioButton.setToggleGroup(createUserToggleGroup);
//        createSocialWorkerRadioButton.setSelected(true);
    }

    /**
     * Handle method for button 'Log ud' When clicked closing current window and
     * shows log in screen
     *
     * @param event
     * @throws IOException
     */
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

    /**
     * Handle method for button 'Opret bruger' in admin tab when logged in as
     * admin . Creates a citizen.
     * ID must be on 10 characters Password must be between 4 and 16 characters
     * Name must be between 3 and 100 characters Email must contain a '@' max 50
     * characters
     *
     * @param event
     */
    @FXML
    private void createUserButtonAction(ActionEvent event) {
        int value; // user type
        if (createAdminRadioButton.isSelected()) {
            value = 0; // admin value
        }
        else if (createSocialWorkerRadioButton.isSelected()) {
            value = 1; // social worker value
        }
        else {
            value = -1;
        }
        if (value != -1) {
            String id = adminIdTextField.getText();
            String password = adminPasswordTextField.getText();
            String repeatedPassword = adminRepeatPasswordTextField.getText();
            String username = adminUsernameTextField.getText();
            String firstName = adminFirstNameTextField.getText();
            String lastName = adminLastNameTextField.getText();
            String email = adminEmailTextField.getText();
            if (ib.hasAcceptableID(id)) {
                if (ib.hasUniqueUserID(id)) {
                    if (ib.hasAcceptablePassword(password, repeatedPassword)) {
                        if (ib.hasAcceptableUsername(username)) {
                            if (ib.hasUniqueUsername(username)) {
                                if (ib.hasAcceptableName(firstName + " " + lastName)) {
                                    if (ib.hasAcceptableMail(email)) {
                                        ib.createUser(firstName + " " + lastName, id, username, password, email, value);
                                        System.out.println("Role: " + ib.getRole());
                                        updateUserList();
                                        adminInfoLabel.setText("Success");
                                    }
                                    else {
                                        adminInfoLabel.setText("Emailen skal indeholde et @, og må maks indeholde "
                                                + ib.getFinalInts()[7] + " tegn");
                                    }
                                }
                                else {
                                    adminInfoLabel.setText("Navnet skal indeholde mellem " + ib.getFinalInts()[5] + " og "
                                            + ib.getFinalInts()[6] + " tegn");
                                }
                            }
                            else {
                                adminInfoLabel.setText("Brugernavnet eksisterer allerede");
                            }
                        }
                        else {
                            adminInfoLabel.setText("Brugernavnet skal indeholde mellem " + ib.getFinalInts()[3] + " og "
                                    + ib.getFinalInts()[4] + " tegn");
                        }
                    }
                    else {
                        adminInfoLabel.setText("Kodeordet skal indeholde mellem " + ib.getFinalInts()[1] + " og "
                                + ib.getFinalInts()[2] + " tegn, og de to kodeord skal matche");
                    }
                }
                else {
                    adminInfoLabel.setText("ID'et eksisterer allerede");
                }
            }
            else {
                adminInfoLabel.setText("ID'et skal indeholde " + ib.getFinalInts()[0] + " tegn");
            }
        }
    }

    /**
     * Handle method for button 'Slet bruger' in admin tab when logged in as
     * admin. Deletes selected
     * citizen
     *
     * @param event
     */
    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
        updateUserList();
    }

    /**
     * Handle method for button 'Opdatér Liste' in Admin tab when logged in as
     * admin. Updates Userlist
     *
     * @param event
     */
    @FXML
    private void updateListAction(ActionEvent event) {
        updateUserList();
    }

    public void updateUserList() {
        if (ib.getUsers() == null) {
            adminInfoLabel.setText("no Users installed");
        }
        else {
            adminUserListView.setItems(ib.getUsers());
        }
    }


    /**
     * Handle method for button 'Opdatér' in log tab when logged in as
     * admin.
     * Updates log
     *
     * @param event
     */
    @FXML
    private void updateLogButtonAction(ActionEvent event) {
        updateLogTextArea();
    }

    /**
     * Method for show updated log in textarea in log tab when logged in as
     * admin.
     */
    private void updateLogTextArea() {
        List<String> logList = ib.getLog();
        for (String s : logList) {
            logTextArea.appendText(s + "\n");
        }
        logUpdatedOnce = true;
    }

    /**
     * Handle method to be executed when selecting log tab when logged in as
     * admin.
     * Updates log when clicked
     * @param event 
     */
    @FXML
    private void logTabChosenEvent(Event event) {
        if (!logUpdatedOnce) {
            updateLogTextArea();
        }
    }

    /**
     * Method for injecting businesslogic into this controller
     *
     * @param businessFacade
     */
    @Override
    public void injectBusiness(IBusiness businessFacade) {
        ib = businessFacade;
    }

    /**
     * Method to launch User Interface (is executed after initialize)
     */
    @Override
    public void openUI() {
        loginInfoLabelAdmin.setText("Logget ind som: " + ib.getActiveUser().getName());
        updateUserList();
        logUpdatedOnce = false;
    }

}
