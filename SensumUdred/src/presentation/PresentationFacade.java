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

    /**
     * IBusiness is used for accessing business logic
     */
    private static IBusiness ib;

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
        launch();
    }

    /**
     *
     * @param stage The stage
     * @throws Exception The Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Parent root = loader.load();

        stage.setTitle("Sensum Udred");
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        stage.setWidth(400);
        stage.setHeight(300);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        if (ib.getActiveUser() != null) {
            System.out.println("Application closing: " + ib.getActiveUser() + " logged out");
            ib.logOutActiveUser();

        }
    }

    public static IBusiness getBusiness() {
        return ib;
    }

}
