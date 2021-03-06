/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederik
 */
public class ControllerFXMLLogin implements Initializable {

    /**
     * IBusiness is used for accessing business logic
     */
    private IBusiness ib;
    /**
     * String is used to set the string of the FXMLLoader
     */
    private String fxmlString;
    /**
     * Textfield for entering username
     */
    @FXML
    private TextField loginUsernameTextField;
    /**
     * Textfield for entering password
     */
    @FXML
    private PasswordField loginPasswordTextField;
    /**
     * If username or password is invalid a 'Forkert input' is shown
     */
    @FXML
    private Label loginInfoLabel;
    /**
     * Button for login
     */
    @FXML
    private Button loginButton;
    /**
     * Button for exit
     */
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     *
     * @param url The url
     * @param rb The ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ib = PresentationFacade.getBusiness();
    }

    /**
     * Handlemethod for login if you login as SocialWorker, you will be directed
     * to SocialWorker page if you login as Admin, you will be directed to Admin
     * page.
     *
     * Message is shown if you enter wrong input.
     *
     * @param event The event
     * @throws IOException The IOException
     */
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        try {
            boolean isCorrect = ib.validateUser(loginUsernameTextField.getText(), loginPasswordTextField.getText());
            if (isCorrect) {
                if (ib.getRole() == ib.getSocialWorkerRoleInt()) {
                    fxmlString = "FXMLSocialWorker.fxml";

                } else if (ib.getRole() == ib.getAdminRoleInt()) {
                    fxmlString = "FXMLAdmin.fxml";
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlString));

                AnchorPane anchorPane = loader.load();
                Scene scene2 = new Scene(anchorPane);

                //Get Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setMinWidth(500);
                window.setMinHeight(430);
                window.setWidth(900);
                window.setHeight(430);
                window.setScene(scene2);
            } else {
                loginInfoLabel.setText("Forkert input");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            loginInfoLabel.setText("Forkert input");
        }
    }

    /**
     * Closing application when exit button is clicked
     *
     * @param event The event
     */
    @FXML
    private void exitButtonAction(ActionEvent event) {
        Platform.exit();
    }

}
