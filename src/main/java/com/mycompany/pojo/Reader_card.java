/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class Reader_card {
    private String id;
    private Date borrowed_date;
    private Date pay_date;
    private Date appointment_date;
    private int reader_id;
    private int get_books;
    private int pay_book;
    private Date booking_date;
    private int booking;
    private String getBook;
    private String payBook;

    {
        id = UUID.randomUUID().toString();
    }
    
    public Reader_card(String id, Date borrowed_date, Date pay_date, Date appointment_date, int reader_id,int get_books, int pay_book ){
        this.id = id;
        this.borrowed_date = borrowed_date;
        this.pay_date =pay_date;
        this.appointment_date = appointment_date;
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
    }
    public Reader_card(String id, Date borrowed_date, Date pay_date, Date appointment_date, int reader_id,int get_books, int pay_book, String getBook, String payBook ){
        this.id = id;
        this.borrowed_date = borrowed_date;
        this.pay_date =pay_date;
        this.appointment_date = appointment_date;
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
        this.getBook = getBook;
        this.payBook = payBook;
    }
     public Reader_card(String id, Date borrowed_date, Date pay_date, Date appointment_date, int reader_id,int get_books, int pay_book ,Date booking_date){
        this.id = id;
        this.borrowed_date = borrowed_date;
        this.pay_date =pay_date;
        this.appointment_date = appointment_date;
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
        this.booking_date = booking_date;
    }
     public Reader_card(int reader_id,int get_books, int pay_book,Date booking_date, int booking){
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
        this.booking_date = booking_date;
        this.booking = booking;
    }
    public Reader_card(int reader_id,int get_books, int pay_book ){
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
    }
    public Reader_card(Date borrowed_date, Date appointment_date, int reader_id,int get_books, int pay_book ){
        this.borrowed_date = borrowed_date;
        this.appointment_date = appointment_date;
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
    }
     public Reader_card(Date borrowed_date, Date pay_date, Date appointment_date, int reader_id,int get_books, int pay_book ){
        this.borrowed_date = borrowed_date;
        this.pay_date =pay_date;
        this.appointment_date = appointment_date;
        this.reader_id= reader_id;
        this.get_books = get_books;
        this.pay_book = pay_book;
    }
    public Reader_card(){
        
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the borrowed_date
     */
    public Date getBorrowed_date() {
        return borrowed_date;
    }

    /**
     * @param borrowed_date the borrowed_date to set
     */
    public void setBorrowed_date(Date borrowed_date) {
        this.borrowed_date = borrowed_date;
    }

    /**
     * @return the pay_date
     */
    public Date getPay_date() {
        return pay_date;
    }

    /**
     * @param pay_date the pay_date to set
     */
    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    /**
     * @return the appointment_date
     */
    public Date getAppointment_date() {
        return appointment_date;
    }

    /**
     * @param appointment_date the appointment_date to set
     */
    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    /**
     * @return the status
     */
   
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


    /**
     * @return the pay_book
     */
    public int getPay_book() {
        return pay_book;
    }

    /**
     * @param pay_book the pay_book to set
     */
    public void setPay_book(int pay_book) {
        this.pay_book = pay_book;
    }

    /**
     * @return the get_books
     */
    public int getGet_books() {
        return get_books;
    }

    /**
     * @param get_books the get_books to set
     */
    public void setGet_books(int get_books) {
        this.get_books = get_books;
    }

    /**
     * @return the booking_Date
     */

    /**
     * @return the booking
     */
    public int getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(int booking) {
        this.booking = booking;
    }

    /**
     * @return the booking_date
     */
    public Date getBooking_date() {
        return booking_date;
    }

    /**
     * @param booking_Date the booking_date to set
     */
    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    /**
     * @return the getBook
     */
    public String getGetBook() {
        return getBook;
    }

    /**
     * @param getBook the getBook to set
     */
    public void setGetBook(String getBook) {
        this.getBook = getBook;
    }

    /**
     * @return the payBook
     */
    public String getPayBook() {
        return payBook;
    }

    /**
     * @param payBook the payBook to set
     */
    public void setPayBook(String payBook) {
        this.payBook = payBook;
    }

    /**
     * @return the booking_Date
     */
  
}
