/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HP
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String user_role;
    private int reader_id;

     public User(int id,String username, String password, String user_role, int reader_id){
        this.id = id;
        this.username = username;
        this.password = password;
        this.user_role = user_role;
        this.reader_id = reader_id;
    }
    public User(String username, String password, String user_role, int reader_id){
        this.username = username;
        this.password = password;
        this.user_role = user_role;
        this.reader_id = reader_id;
    }
    public User(String username, String password, String user_role){
        this.username = username;
        this.password = password;
        this.user_role = user_role;
    }
    public User(String password,int reader_id){
        this.password = password;
        this.reader_id = reader_id;
    }
    public User(){
        
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user_role
     */
    public String getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    /**
     * @return the reader_id
     */
    public int getReader_id() {
        return reader_id;
    }

    /**
     * @param reader_id the reader_id to set
     */
    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }
}
