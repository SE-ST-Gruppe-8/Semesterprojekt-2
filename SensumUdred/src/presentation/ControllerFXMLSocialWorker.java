/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
import acq.ICase;
import acq.ICitizen;
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
    private Tab casesTab;

    @FXML
    private Tab inquiriesTab;

    @FXML
    private Tab referenceTab;

    @FXML
    private Button logoutButtonSW;
    @FXML
    private Button createCaseButton;
    @FXML
    private Button editCaseButton;
    @FXML
    private Button createCitizenButton;
    @FXML
    private Button editCitizenButton;
    @FXML
    private Button deleteCitizenButton;
    @FXML
    private Button updateCitizenListButton;
    @FXML
    private Button deleteCaseButton;
    @FXML
    private Button updateCaseListButton;
    @FXML
    private ListView<ICitizen> citizenListView;
    @FXML
    private Label caseLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ab = new AlertBox();
        ib = PresentationFacade.getIData().getIBusiness();
        loginInfoLabelSW.setText("Logged in as: " + ib.getActiveUser().getName());
        System.out.println("Logged in as: " + ib.getActiveUser().getName());
        ib.getCitizen();
        updateCaseList();
        updateCitizenList();
    }


    public void updateCaseList() {
        if (ib.getCases() == null) {
            caseLabel.setText("No Useres Installed");
        }
        else {
            caseListView.setItems(ib.getCases());
        }
    }


    @FXML
    private void createCaseAction(ActionEvent event) {
        
        updateCaseList();
        if (citizenListView.getSelectionModel().getSelectedItem() != null) {
            ab.displayCaseCreation("Create case", ib, (ICitizen) citizenListView.getSelectionModel().getSelectedItem());
        } else {
            caseLabel.setText("you must select a Citizen from the list to create a case");
        }
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

   @FXML
    private void createCitizenAction(ActionEvent event) {
        ab.displayCitizenCreation("Create citizen", ib);
        updateCitizenList();
    }

   @FXML
    private void deleteCitizenAction(ActionEvent event) {
        ib.deleteCitizen(citizenListView.getSelectionModel().getSelectedItem());
        updateCitizenList();
    }

    @FXML
    private void updateCitizenListAction(ActionEvent event) {
        updateCitizenList();
    }

    @FXML
    private void updateCaseListAction(ActionEvent event) {
        updateCaseList();
    }

    
    public void updateCitizenList() {
        if (ib.getCitizen() == null) {
            //indsæt ting på label
        } else {
            citizenListView.setItems(ib.getCitizen());
        }
    }
}
