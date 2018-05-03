/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
import acq.ICase;
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
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Robin
 */
public class ControllerFXMLSocialWorker implements Initializable, IPresentation {

    private IBusiness ib;

    private AlertBox ab;

    @FXML
    private ListView<ICase> caseListView;

    private Tab socialTab;

    private Tab adminTab;

    @FXML
    private Label loginInfoLabelSW;
    
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

    @FXML
    private Button logoutButtonSW;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ib = PresentationFacade.getIData().getIBusiness();
        loginInfoLabelSW.setText("Logged in as: " + ib.getActiveUser().getName());
        System.out.println("Logged in as: " + ib.getActiveUser().getName());
        ib.getCitizen();
        updateCaseList();
    }


    public void updateCaseList() {
        if (ib.getCases() == null) {
//            sw.setText("no Users installed");
        }
        else {
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
      //  ab.display("Create case", ib);//              Søren, fix this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        updateCaseList();

    }

    @FXML
    private void DeleteCaseAction(ActionEvent event) {
        ib.deleteCase(caseListView.getSelectionModel().getSelectedItem());
        updateCaseList();
    }

    @Override
    public void injectBusiness(IBusiness businessFacade) {
               
    }

    @Override
    public void openUI() {
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
        window.setScene(scene2);
        window.show();
    }

}
