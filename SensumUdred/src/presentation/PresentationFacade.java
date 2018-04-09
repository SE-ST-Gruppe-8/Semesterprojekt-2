/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import acq.*;
import static javafx.application.Application.launch;

/**
 *
 * @author Bruger
 */
public class PresentationFacade implements IPresentation {

    private IBusiness ib;
    private static PresentationFacade ui;

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
}
