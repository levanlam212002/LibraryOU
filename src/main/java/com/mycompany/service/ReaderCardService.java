/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Muon;
import com.mycompany.pojo.Reader_card;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author HP
 */
public class ReaderCardService {

    public List<Reader_card> getCard(int kw) throws SQLException {
        List<Reader_card> card = new ArrayList<>();

        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM librarydb.reader_card";
            if (kw != 0) {
                sql += " WHERE reader_id = ?;";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != 0) {
                stm.setInt(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"), ShowgetBook(rs.getInt("get_books")), ShowpayBook(rs.getInt("pay_book")));
                card.add(b);
            }
        }
        return card;
    }

    public List<Reader_card> getByReaderID(int kw) throws SQLException {
        List<Reader_card> card = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `reader_card` WHERE (`reader_id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"));
                card.add(b);
            }
        }
        return card;
    }

    public boolean addCard(Reader_card card, List<Muon> m) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO `librarydb`.`reader_card` (`id`, `borrowed_date`, `pay_date`, `appointment_date`, `reader_id`, `get_books`, `pay_book`) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, card.getId());
            stm.setDate(2, card.getBorrowed_date());
            stm.setDate(3, card.getPay_date());
            stm.setDate(4, card.getAppointment_date());
            stm.setInt(5, card.getReader_id());
            stm.setInt(6, card.getGet_books());
            stm.setInt(7, card.getPay_book());
            stm.executeUpdate();

            sql = "INSERT INTO `librarydb`.`muon` (`Book_id`, `Card_id`) VALUES (?,?);";
            PreparedStatement stm1 = conn.prepareStatement(sql);
            for (Muon i : m) {
                stm1.setInt(1, i.getBook_id());
                stm1.setString(2, card.getId());
                stm1.executeUpdate();
            }
            conn.commit();
            return true;
        }
    }

    public void deleteCard(String id) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM `librarydb`.`reader_card` WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        }
    }

    public boolean kiemTraChuaTraHetSach(Reader_card c) {
        return c.getPay_book() == 0;
    }

    public boolean kiemTraNgayMuon(Date a, Date b) {
        long date = a.getTime() - b.getTime();
        long datediff = TimeUnit.MILLISECONDS.toDays(date);
        return datediff > 30;
    }

    public void PayBook(Date a, String b) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`reader_card` SET `pay_date` = ?, `pay_book` = ? WHERE (`id` like concat(?));";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, a);
            stm.setInt(2, 1);
            stm.setString(3, b);
            stm.executeUpdate();
        }
    }

    public void GetBook(Date a, Date b, String c) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`reader_card` SET `borrowed_date` = ?, `appointment_date` = ?, `get_books` = ? WHERE (`id` like concat(?));";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDate(1, a);
            stm.setDate(2, b);
            stm.setInt(3, 1);
            stm.setString(4, c);
            stm.executeUpdate();
        }
    }

    public long kiemTraTraTre(Date a, Date b) {
        long date = a.getTime() - b.getTime();
        long datediff = TimeUnit.MILLISECONDS.toDays(date);
        return datediff;
    }

    public boolean DatOnline(Reader_card card, List<Muon> m) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO `librarydb`.`reader_card` (`id`, `reader_id`, `get_books`, `pay_book`, `booking_date`, `booking`) VALUES (?,?,?,?,?,?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, card.getId());
            stm.setInt(2, card.getReader_id());
            stm.setInt(3, card.getGet_books());
            stm.setInt(4, card.getPay_book());
            stm.setDate(5, card.getBooking_date());
            stm.setInt(6, card.getBooking());
            stm.executeUpdate();

            sql = "INSERT INTO `librarydb`.`muon` (`Book_id`, `Card_id`) VALUES (?,?);";
            PreparedStatement stm1 = conn.prepareStatement(sql);
            for (Muon i : m) {
                stm1.setInt(1, i.getBook_id());
                stm1.setString(2, card.getId());
                stm1.executeUpdate();
            }
            conn.commit();
            return true;
        }
    }

    public boolean kiemTraHanLaySachDaDat(Date a, Date b) {
        long date = a.getTime() - b.getTime();
        long datediff = TimeUnit.MILLISECONDS.toHours(date);
        System.out.println(datediff);
        return datediff > 48;
    }

    public List<Reader_card> getCardBooking() throws SQLException {
        List<Reader_card> card = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `librarydb`.`reader_card` WHERE (`booking` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, 1);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"), rs.getDate("booking_date"));
                card.add(b);
            }
        }
        return card;
    }

    public List<Reader_card> getCardByBorrowed(int year, int quy) throws SQLException {
        List<Reader_card> card = new ArrayList<>();

        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM librarydb.reader_card WHERE (get_books = ?) AND (YEAR(borrowed_date) = ?)";
            switch (quy) {
                case 0:
                case 1:
                case 2:
                case 3:
                    sql += " AND ((Month(borrowed_date) = ?) OR (Month(borrowed_date) = ?) OR (Month(borrowed_date) = ?))";
                    break;
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            switch (quy) {
                case 0:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 1);
                    stm.setInt(4, 2);
                    stm.setInt(5, 3);
                    break;
                case 1:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 4);
                    stm.setInt(4, 5);
                    stm.setInt(5, 6);
                    break;
                case 2:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 7);
                    stm.setInt(4, 8);
                    stm.setInt(5, 9);
                    break;
                case 3:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 10);
                    stm.setInt(4, 11);
                    stm.setInt(5, 12);
                    break;
                default:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"));
                card.add(b);
            }
        }
        return card;
    }

    public List<Reader_card> getCardByPay(int year, int quy) throws SQLException {
        List<Reader_card> card = new ArrayList<>();

        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM librarydb.reader_card WHERE (pay_book = ?) AND (YEAR(borrowed_date) = ?)";
            switch (quy) {
                case 0:
                case 1:
                case 2:
                case 3:
                    sql += " AND ((Month(borrowed_date) = ?) OR (Month(borrowed_date) = ?) OR (Month(borrowed_date) = ?))";
                    break;
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            switch (quy) {
                case 0:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 1);
                    stm.setInt(4, 2);
                    stm.setInt(5, 3);
                    break;
                case 1:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 4);
                    stm.setInt(4, 5);
                    stm.setInt(5, 6);
                    break;
                case 2:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 7);
                    stm.setInt(4, 8);
                    stm.setInt(5, 9);
                    break;
                case 3:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
                    stm.setInt(3, 10);
                    stm.setInt(4, 11);
                    stm.setInt(5, 12);
                    break;
                default:
                    stm.setInt(1, 1);
                    stm.setInt(2, year);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"));
                card.add(b);
            }
        }
        return card;
    }

    public List<Reader_card> getCardByPayLate() throws SQLException {
        List<Reader_card> card = new ArrayList<>();

        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM librarydb.reader_card WHERE DATEDIFF(`pay_date`,`borrowed_date`) > ?;";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, 30);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Reader_card b = new Reader_card(rs.getString("id"), rs.getDate("borrowed_date"), rs.getDate("pay_date"), rs.getDate("appointment_date"), rs.getInt("reader_id"), rs.getInt("get_books"), rs.getInt("pay_book"));
                card.add(b);
            }
        }
        return card;
    }

    public String ShowgetBook(int a) {
        return a == 1 ? "Đã nhận" : "Chưa nhận";
    }

    public String ShowpayBook(int a) {
        return a == 1 ? "Đã trả" : "Chưa trả";
    }
}
