/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Object;
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
public class ObjectService {
    public List<Object> getObjects() throws SQLException{
        List<Object> obj = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM object";
            PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Object o = new Object(rs.getInt("id"),rs.getString("name"));
                 obj.add(o);
             }
        }
        return obj;
    }
     public boolean addObject(Object o) throws SQLException{
        if(kiemTraDoiTuong(o.getName()))
            return false;
        else
            try (Connection conn = jdbcUtils.getConn()) {
                String sql ="INSERT INTO `librarydb`.`object` (`name`) VALUES (?);";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1,o.getName());
                stm.executeUpdate();
                return true;
            }
     }
     public void deleteObject(int id) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql ="DELETE FROM `librarydb`.`object` WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,id);
            stm.executeUpdate();
        }
    }
    public boolean kiemTraDoiTuong(String name) throws SQLException{
        List<Object> o = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql ="SELECT * FROM librarydb.object WHERE (name like concat(?));";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,name);
           ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Object c = new Object(rs.getInt("id"),rs.getString("name"));
                o.add(c);
            }
            return o.size() == 1;
        }
    }
}
