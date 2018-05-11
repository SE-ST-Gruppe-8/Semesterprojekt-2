/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederik
 */
public class ControllerFXMLlogin implements Initializable, IPresentation {

    private IBusiness ib;

    private String fxmlString;

    @FXML
    private TextField loginUsernameTextField;

    @FXML
    private PasswordField loginPasswordTextField;

    @FXML
    private Label loginInfoLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ib = PresentationFacade.getIData().getIBusiness();
    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        boolean iscorrect = ib.validateUser(loginUsernameTextField.getText(), loginPasswordTextField.getText());

        if (iscorrect) {
            loginInfoLabel.setText("Succesfully logged in as: " + ib.getActiveUser().getName());
            if (ib.getRole() == 1) {
//                fxmlString = "FXMLAdmin.fxml";
                fxmlString = "FXMLSocialWorker.fxml";

            } else if (ib.getRole() == 0) {
//                fxmlString = "FXMLSocialWorker.fxml";
                fxmlString = "FXMLAdmin.fxml";
            }
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlString));

        AnchorPane anchorPane = loader.load();
        IPresentation controller = loader.getController();
        controller.injectBusiness(ib);
        controller.openUI();


            Scene scene2 = new Scene(anchorPane);
            //Get Stage information

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setMinWidth(500);
            window.setMinHeight(430);
            window.setWidth(900);
            window.setHeight(430);
            window.setScene(scene2);
            window.show();
        } else {
            loginInfoLabel.setText("Wrong input");
        }
    }

    @Override
    public void injectBusiness(IBusiness businessFacade) {

        ib = businessFacade;
    }

    @Override
    public void openUI() {
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
