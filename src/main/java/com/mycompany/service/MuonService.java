/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Book;
import com.mycompany.pojo.Muon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class MuonService {

    public void deleteMuon(int id) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM `librarydb`.`muon` WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        }
    }

    public List<Muon> getByBookId(int id) throws SQLException {
        List<Muon> muon = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `muon` WHERE (`Book_id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Muon b = new Muon(rs.getInt("id"), rs.getString("Card_id"), rs.getInt("Book_id"));
                muon.add(b);
            }
        }
        return muon;
    }
    
    public List<Muon> getByCardId(String id) throws SQLException {
        List<Muon> muon = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `muon` WHERE (`Card_id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Muon b = new Muon(rs.getInt("id"), rs.getString("Card_id"), rs.getInt("Book_id"));
                muon.add(b);
            }
        }
        return muon;
    }

    public boolean kiemTraSoSachMuon(List<Book> b) {
        return b.size() > 5;
    }

    public List<Muon> getMuonDelete(String id) throws SQLException {
        List<Muon> muon = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `muon` WHERE (`Card_id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Muon b = new Muon(rs.getInt("id"), rs.getString("Card_id"), rs.getInt("Book_id"));
                muon.add(b);
            }
        }
        return muon;
    }
}
