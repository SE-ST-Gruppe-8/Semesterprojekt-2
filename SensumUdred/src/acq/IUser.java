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
     * Sets the user's name.
     *
     * @param name the user's name.
     */
    public void setName(String name);

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
     * Sets the user's username.
     *
     * @param userName a username.
     */
    public void setUsername(String userName);

    /**
     * Returns the user's password.
     *
     * @return the user's password.
     */
    public String getPassword();

    /**
     * Sets the user's password.
     *
     * @param password a password.
     */
    public void setPassword(String password);

    /**
     * Returns the user's email.
     *
     * @return an email.
     */
    public String getEmail();

    /**
     * Sets the user's email.
     *
     * @param email an email.
     */
    public void setEmail(String email);

    /**
     * Returns an integer representing the User's role.
     *
     * @return the user's role.
     */
    public int getRole();

}
