/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import acq.IPresentation;
import acq.ICase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

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

//    private Label loginInfoLabel;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ib = PresentationFacade.getIData().getIBusiness();
        casesTab.setDisable(true);
        inquiriesTab.setDisable(true);
        referenceTab.setDisable(true);
//        adminTab.setDisable(true);
//        updateUserList();
        updateCaseList();
    }

//    private void createUserButtonAction(ActionEvent event) {
//        // TODO
//        int value;
//        if (createAdminRadioButton.isSelected()) {
//            value = 0;
//        }
//        else {
//            value = 1;
//        }
//        ib.createUser(adminFirstNameTextField.getText() + " " + adminLastNameTextField.getText(), "test ID",
//                adminUsernameTextField.getText(), adminPasswordTextField.getText(),
//                adminEmailTextField.getText(), value);
//        updateUserList();
//    }

//    private void deleteUserButtonAction(ActionEvent event) {
//        // TODO
//        ib.deleteUser(adminUserListView.getSelectionModel().getSelectedItem());
//        updateUserList();
//    }

//    private void UpdateListAction(ActionEvent event) {
//        updateUserList();
//    }

//    public void updateUserList() {
//        if (ib.getUsers() == null) {
//            adminInfoLabel.setText("no Users installed");
//        }
//        else {
//            adminUserListView.setItems(ib.getUsers());
//        }
//    }

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
        ab.display("Create case", ib);
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

}
