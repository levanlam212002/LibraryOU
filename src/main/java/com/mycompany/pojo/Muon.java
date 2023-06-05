/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author HP
 */
public class Muon {
    private int id;
    private String card_id;
    private int book_id;
    
    public Muon(int id, String card_id, int book_id){
        this.id = id;
        this.card_id = card_id;
        this.book_id = book_id;
    }
     public Muon(String card_id, int book_id){
        this.card_id = card_id;
        this.book_id = book_id;
    }
    public Muon(){
        
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
     * @return the card_id
     */
    public String getCard_id() {
        return card_id;
    }

    /**
     * @param card_id the card_id to set
     */
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    /**
     * @return the book_id
     */
    public int getBook_id() {
        return book_id;
    }

    /**
     * @param book_id the book_id to set
     */
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
