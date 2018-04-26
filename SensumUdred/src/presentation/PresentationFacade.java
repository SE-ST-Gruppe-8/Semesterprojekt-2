/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.*;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bruger
 */
public class PresentationFacade extends Application implements IPresentation {

    private IBusiness ib;

    private static PresentationFacade ui;

    public static PresentationFacade getUi() {
        return ui;
    }

    public IBusiness getIBusiness() {
        return ib;
    }

    public PresentationFacade() {

    }

    /**
     * Override; inject business interface to presentation.
     *
     * @param businessFacade IBusiness, business access point.
     */
    @Override
    public void injectBusiness(IBusiness businessFacade) {
        this.ib = businessFacade;
    }

    /**
     * Override; launch GUI.
     */
    @Override
    public void openUI() {
        ui = this;
        launch();
    }

    public static PresentationFacade getIData() {
        return ui;
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("FXML_login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLlogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sensum Udred");
        
    }

  

}
