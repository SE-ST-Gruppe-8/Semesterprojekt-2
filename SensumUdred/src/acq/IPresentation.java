/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;


/**
 *
 * @author Bruger
 */
public interface IPresentation {

    /**
     * Method for injecting businesslogic into presentation
     * 
     * @param businessFacade 
     */
    
    public void injectBusiness(IBusiness businessFacade);

    /**
     * Method to launch User Interface
     */

    public void openUI();
    
    
}
