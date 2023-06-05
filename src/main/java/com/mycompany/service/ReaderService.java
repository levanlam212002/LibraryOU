/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Readers;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ReaderService {

    public List<Readers> getReaders(int kw) throws SQLException {
        List<Readers> readers = new ArrayList<>();

        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM `librarydb`.`readers`";
            if (kw != 0) {
                sql += " WHERE id = ?;";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != 0) {
                stm.setInt(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Readers r = new Readers(rs.getInt("id"), rs.getString("name"),
                        rs.getString("sex"), rs.getDate("birthday"), rs.getDate("start_date"), rs.getDate("end_date"),
                         rs.getString("part"), rs.getString("email"), rs.getString("address"), rs.getString("phone"), rs.getInt("object_id"));
                readers.add(r);
            }
        }
        return readers;
    }

    public void addReader(Readers b) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "INSERT INTO `librarydb`.`readers` (`name`, `sex`, `birthday`, `start_date`, `end_date`, `part`, `email`, `address`, `phone`, `object_id`) VALUES (?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, b.getName());
            stm.setString(2, b.getSex());
            stm.setDate(3, b.getBirthday());
            stm.setDate(4, b.getStart_date());
            stm.setDate(5, b.getEnd_date());
            stm.setString(6, b.getPart());
            stm.setString(7, b.getEmail());
            stm.setString(8, b.getAddress());
            stm.setString(9, b.getPhone());
            stm.setInt(10, b.getObject_id());
            stm.executeUpdate();
        }
    }

    public boolean deleteReader(int id) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM `librarydb`.`readers` WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void updateReader(Readers b) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`readers` SET `name` = ?, `sex` = ?, `birthday` = ?, `start_date` = ?, `end_date` = ?, `part` = ?, `email` = ?, `address` = ?, `phone` = ?, `object_id` = ? WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, b.getName());
            stm.setString(2, b.getSex());
            stm.setDate(3, b.getBirthday());
            stm.setDate(4, b.getStart_date());
            stm.setDate(5, b.getEnd_date());
            stm.setString(6, b.getPart());
            stm.setString(7, b.getEmail());
            stm.setString(8, b.getAddress());
            stm.setString(9, b.getPhone());
            stm.setInt(10, b.getObject_id());
            stm.setInt(11, b.getId());
            stm.executeUpdate();
        }
    }

    public boolean kiemTraHanThe(Date a) {
        return a.before(Date.valueOf(java.time.LocalDate.now()));
    }

    public boolean kiemTraSDT(String sdt) {
        boolean flag;
        try {
            Integer.parseInt(sdt);
            if (sdt.charAt(0) == '0') {
                flag = sdt.length() == 10;
            } else {
                flag = false;
            }
        } catch (NumberFormatException ex) {
            flag = false;
        }
        return flag;
    }

    public boolean kiemTraEmail(String email) {
        return email.endsWith("@ou.edu.vn");
    }

    public boolean KiemTraDocGiaTrungSDT(List<Readers> r, String sdt, String name, String sex) {
        for (Readers i : r) {
            if (sdt.equals(i.getPhone())) {
                if (i.getEnd_date().before(Date.valueOf(java.time.LocalDate.now()))) {
                    if (i.getName().equals(name) && i.getSex().equals(sex)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean KiemTraDocGiaTrungEmail(List<Readers> r, String mail, String name, String sex) {
        for (Readers i : r) {
            if (mail.equals(i.getEmail())) {
                if (i.getEnd_date().before(Date.valueOf(java.time.LocalDate.now()))) {
                    if (i.getName().equals(name) && i.getSex().equals(sex)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
