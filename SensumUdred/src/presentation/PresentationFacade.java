/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.*;

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

    private static IBusiness ib;

//    private static PresentationFacade ui;
//    public static PresentationFacade getUi() {
//        return ui;
//    }
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
//        ui = this;
        launch();
    }

//    public static PresentationFacade getIData() {
//        return ui;
//    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLlogin.fxml"));
        Parent root = loader.load();

        IPresentation controller = loader.getController();
        controller.injectBusiness(ib);

        stage.setTitle("Sensum Udred");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Application closing " + ib.getActiveUser() + " logged out");
        
    }

}
