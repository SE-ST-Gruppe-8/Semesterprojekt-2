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
public interface IUser {

    public String getName();

    public void setName(String name);

    public String getID();

    public String getUsername();

    public void setUsername(String userName);

    public String getPassword();

    public void setPassword(String password);

    public String getEmail();

    public void setEmail(String email);

    public int getRole();

}
