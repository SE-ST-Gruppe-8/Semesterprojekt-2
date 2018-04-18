/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.IBusiness;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author Robin
 */
public class FXMLDocumentController implements Initializable {

    private IBusiness ib;

    @FXML
    private Button button;
    @FXML
    private ListView<?> list1;
    @FXML
    private ListView<?> list2;
    @FXML
    private ListView<?> list3;
    @FXML
    private ListView<?> list4;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
