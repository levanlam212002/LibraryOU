/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Readers {
    private int id;
    private String name;
    private String sex;
    private Date birthday;
    private Date start_date;
    private Date end_date;
    private String part;
    private String email;
    private String address;
    private String phone;
    private int object_id;

    public Readers(int id, String name, String sex, Date birthday, Date start_date,Date end_date,String part, String email, String address, String phone,int object_id){
        this.id=id;
        this.name = name;
        this.sex = sex;
        this.birthday= birthday;
        this.start_date = start_date;
        this.end_date = end_date;
        this.part = part;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.object_id = object_id;
    }
     public Readers( String name, String sex, Date birthday, Date start_date,Date end_date,String part, String email, String address, String phone,int object_id){

        this.name = name;
        this.sex = sex;
        this.birthday= birthday;
        this.start_date = start_date;
        this.end_date = end_date;
        this.part = part;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.object_id = object_id;
    }
    public Readers(){
        
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the start_date
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the part
     */
    public String getPart() {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart(String part) {
        this.part = part;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the object_id
     */
    public int getObject_id() {
        return object_id;
    }

    /**
     * @param object_id the object_id to set
     */
    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
