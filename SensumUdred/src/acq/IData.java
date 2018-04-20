/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acq;

import java.util.ArrayList;
import presentation.PresentationFacade;

/**
 *
 * @author Bruger
 */
public interface IData {
    
    void saveUsers(ArrayList<IUser> data);
    ArrayList<IUser> readUsers();

    
}
