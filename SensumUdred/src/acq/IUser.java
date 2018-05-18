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

    /**
     * Returns the user's name.
     *
     * @return the user's name.
     */
    public String getName();

    /**
     * Returns the user's ID.
     *
     * @return the user's ID.
     */
    public String getID();

    /**
     * Returns the user's username.
     *
     * @return the user's username.
     */
    public String getUsername();

    /**
     * Returns the user's password.
     *
     * @return the user's password.
     */
    public String getPassword();

    /**
     * Returns the user's email.
     *
     * @return an email.
     */
    public String getEmail();

    /**
     * Returns an integer representing the user's role.
     *
     * @return the user's role.
     */
    public int getRole();

}
