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
public class Book {
    private int id;
    private String name;
    private String author;
    private String describe;
    private Date publication_date;
    private String publication_place;
    private Date added_date;
    private String location;
    private int category_id;
    private int status;
    private String cate;

    
    public Book(int id, String name, String author, String describe, Date publication_date, String publication_place, Date added_date, String location, int category_id,int status){
        this.id = id;
        this.name = name;
        this.author = author;
        this.describe = describe;
        this.publication_date = publication_date;
        this.publication_place = publication_place;
        this.added_date = added_date;
        this.location = location;
        this.category_id = category_id;
        this.status = status;
    }
     public Book(int id, String name, String author, String describe, Date publication_date, String publication_place, Date added_date, String location, String category,int category_id,int status){
        this.id = id;
        this.name = name;
        this.author = author;
        this.describe = describe;
        this.publication_date = publication_date;
        this.publication_place = publication_place;
        this.added_date = added_date;
        this.location = location;
        this.cate = category;
        this.category_id = category_id;
        this.status = status;
    }
    
     public Book(String name, String author, String describe, Date publication_date, String publication_place, Date added_date, String location, int category_id,int status){
        this.name = name;
        this.author = author;
        this.describe = describe;
        this.publication_date = publication_date;
        this.publication_place = publication_place;
        this.added_date = added_date;
        this.location = location;
        this.category_id = category_id;
        this.status = status;
        
    }
    
    public Book(){
        
    }
    @Override
    public String toString() {
        return this.name;
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
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe the describe to set
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return the publication_date
     */
    public Date getPublication_date() {
        return publication_date;
    }

    /**
     * @param publication_date the publication_date to set
     */
    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    /**
     * @return the publication_place
     */
    public String getPublication_place() {
        return publication_place;
    }

    /**
     * @param publication_place the publication_place to set
     */
    public void setPublication_place(String publication_place) {
        this.publication_place = publication_place;
    }

    /**
     * @return the added_date
     */
    public Date getAdded_date() {
        return added_date;
    }

    /**
     * @param added_date the added_date to set
     */
    public void setAdded_date(Date added_date) {
        this.added_date = added_date;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the category_id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the cate
     */
    public String getCate() {
        return cate;
    }

    /**
     * @param cate the cate to set
     */
    public void setCate(String cate) {
        this.cate = cate;
    }
            
}
