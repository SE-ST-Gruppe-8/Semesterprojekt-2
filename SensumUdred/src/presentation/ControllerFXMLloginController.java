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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederik
 */
public class ControllerFXMLloginController implements Initializable {

    private IBusiness ib;

    private AlertBox ab;

    private PresentationFacade pf;

    @FXML
    private TextField loginUsernameTextField;

    @FXML
    private PasswordField loginPasswordTextField;

    @FXML
    private Label loginInfoLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ib = PresentationFacade.getIData().getIBusiness();
        pf = PresentationFacade.getIData();
    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("ControllerFXMLloginController.fxml"));
 loader.setLocation(getClass().getResource("FXMLTest.fxml"));

        GridPane gridPane = loader.load();
        IPresentation controller = loader.getController();
        controller.injectBusiness(ib);

        Scene scene2 = new Scene(gridPane);
        //Get Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

    }

    @FXML
    private void logoutButtonAction(ActionEvent event) {
    }

}
