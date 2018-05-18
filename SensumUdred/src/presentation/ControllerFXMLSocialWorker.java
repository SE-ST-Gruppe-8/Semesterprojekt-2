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
import acq.IInquiry;
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

    private PresentationFacade pf;
    private IBusiness ib;
    private AlertBox ab;

    @FXML
    private ListView<ICase> caseListView;
    @FXML
    private Label loginInfoLabelSW;
    @FXML
    private Tab casesTab;
    @FXML
    private Tab inquiriesTab;
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
    @FXML
    private Button editInquiryButton;
    @FXML
    private ListView<IInquiry> inquiriesListView;
    @FXML
    private Button deleteInquiryButton;
    @FXML
    private Button updateInquiryListView;
    @FXML
    private Button createInquiryButton;
    @FXML
    private ListView<IInquiry> inquiryListView1;
    @FXML
    private Label inquiryLabel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ab = new AlertBox();
//        ib = PresentationFacade.getIData().getIBusiness();
//        loginInfoLabelSW.setText("Logged in as: " + ib.getActiveUser().getName());
//        System.out.println("Logged in as: " + ib.getActiveUser().getName());
//        ib.getCitizen();
//        updateCaseList();
//        updateCitizenList();
//        updateInquiryList();
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
        ib.clearLists();
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
     * Handle method for button 'Opret sag'.Opens an alertbox, where case can be
     * created for selected inquiry
     *
     * @param event
     */
    @FXML
    private void createCaseAction(ActionEvent event) {

        updateCaseList();
        if (inquiryListView1.getSelectionModel().getSelectedItem() != null) {
            if (inquiryListView1.getSelectionModel().getSelectedItem().getCitizen().getCase() == null) {
                ab.displayCaseCreation("Opret sag", ib, (ICitizen) inquiryListView1.getSelectionModel().getSelectedItem().getCitizen());
            }
            else {
                caseLabel.setText("Denne henvendelse har allerede en sag");
            }
        }
        else {
            caseLabel.setText("Vælg en borger fra listen over borgerer");
        }
    }

    /**
     * Handle method for button 'Slet sag'. Deletes selected case
     *
     * @param event
     */
    @FXML
    private void DeleteCaseAction(ActionEvent event) {
        if (caseListView.getSelectionModel().getSelectedItem() != null) {
            ib.deleteCase(caseListView.getSelectionModel().getSelectedItem());
        }
        else {
            caseLabel.setText("Vælg en sag fra listen over sager");
        }
    }

    /**
     * Handle method for button 'Opdatér liste' Calls method:
     * updateCitizenList()
     *
     * @param event
     */
    @FXML
    private void updateListAction(ActionEvent event) {
        updateCitizenList();
    }

    /**
     * Updates listview of cases in GUI
     */
    public void updateCaseList() {
        if (ib.getCases() != null) {
            caseListView.setItems(ib.getCases());
        }
    }

    /**
     * Handle method for button 'Opret borger' Creates citizen Opens an
     * alertbox, where a citizen can be created
     *
     * @param event
     */
    @FXML
    private void createCitizenAction(ActionEvent event) {
        ab.displayCitizenCreation("Opret borger", ib);
    }

    /**
     * Handle method for button 'Slet borger'. Deletes selected citizen
     *
     * @param event
     */
    @FXML
    private void deleteCitizenAction(ActionEvent event) {
        if (citizenListView.getSelectionModel().getSelectedItem() != null) {
            ib.deleteCitizen(citizenListView.getSelectionModel().getSelectedItem());
            updateCitizenList();
            updateCaseList();
            updateInquiryList();
        }
        else {
            inquiryLabel.setText("Vælg en borger fra listen over borgerer");
        }
    }

    /**
     * Handle method for button 'Redigér borger'. Opens an alertbox, where
     * selected citizen can be edited
     *
     * @param event
     */
    @FXML
    private void editCitizenAction(ActionEvent event) {
        if (citizenListView.getSelectionModel().getSelectedItem() != null) {
            ab.displayCitizenEdit("Rediger Borger", ib, citizenListView.getSelectionModel().getSelectedItem());
        }
        else {
            inquiryLabel.setText("Vælg en borger fra listen over borgerer");
        }
    }

    /**
     * @deprecated @param event
     */
    @FXML
    private void updateCaseListAction(ActionEvent event) {

    }

    /**
     * Method for updating list of citizens. Updates list of citizens
     */
    public void updateCitizenList() {
        ib.processStuff();
        citizenListView.setItems(ib.getCitizen());
        updateInquiryList();
        updateCaseList();
    }

    /**
     * Handle method for button 'Opret henvendelse'. Opens an alertbox, where
     * inquiry can be created for selected citizen
     *
     * @param event
     */
    @FXML
    private void createInquiryAction(ActionEvent event) {
        if (citizenListView.getSelectionModel().getSelectedItem() != null) {
            if (citizenListView.getSelectionModel().getSelectedItem().getInquiry() == null) {
                ab.displayInquiryCreation("Opret henvendelse", ib, citizenListView.getSelectionModel().getSelectedItem());
            }
            else {
                inquiryLabel.setText("Denne borger har allerede en henvendelse");
            }
        }
        else {
            inquiryLabel.setText("Vælg en borger fra listen over borgere");
        }
    }

    /**
     * Handle method for button 'Slet henvendelse'. Deletes selected inquiry
     *
     * @param event
     */
    @FXML
    private void deleteInquiryAction(ActionEvent event) {
        if (inquiriesListView.getSelectionModel().getSelectedItem() != null) {
            ib.deleteInquiry(inquiriesListView.getSelectionModel().getSelectedItem());
        }
        else {
            inquiryLabel.setText("Vælg en henvendelse fra listen over henvendelser");
        }
    }

    /**
     * Handle method for button 'Redigér henvendelse'. Opens an alertbox, where
     * selected inquiry can be edited
     *
     * @param event
     */
    @FXML
    private void editInquiryAction(ActionEvent event) {
        if (inquiriesListView.getSelectionModel().getSelectedItem() != null) {
            ab.displayInquiryEdit("Rediger henvendelse", ib, inquiriesListView.getSelectionModel().getSelectedItem());
        }
        else {
            inquiryLabel.setText("Vælg en henvendelse fra listen over henvendelser");
        }
    }

    /**
     * Handle method for button 'Opdatér liste'. Calls: updateInquiryList()
     *
     * @param event
     */
    @FXML
    private void updateInquiryListAction(ActionEvent event) {
        updateInquiryList();
    }

    /**
     * Method for updating list of inquiries. Updates list of inquiries
     */
    public void updateInquiryList() {
        inquiriesListView.setItems(ib.getInquiries());
        inquiryListView1.setItems(ib.getInquiries());
    }

    /**
     * Handle method for button 'Redigér sag'.Opens an alertbox, where selected
     * case can be edited
     *
     * @param event
     */
    @FXML
    private void editCaseAction(ActionEvent event) {
        if (caseListView.getSelectionModel().getSelectedItem() != null) {
            ab.displayCaseEdit("Rediger borger", ib, caseListView.getSelectionModel().getSelectedItem());
        }
        else {
            caseLabel.setText("Vælg en sag fra listen over sager");
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
        loginInfoLabelSW.setText("Logget ind som: " + ib.getActiveUser().getName());
        ib.processStuff();
        updateCitizenList();
        updateCaseList();
        updateInquiryList();
    }

}
