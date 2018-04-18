/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import acq.*;

/**
 *
 * @author Bruger
 */
public class BusinessFacade implements IBusiness {

    private IData data;
    private SecurityHandler security;

    public BusinessFacade() {
        security = new SecurityHandler();
    }

    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }
    
    public void createCase(){
       // data.writeData((SocialWorker)security.getActiveUser().createCase(), "LogFile.txt");
    }

}
