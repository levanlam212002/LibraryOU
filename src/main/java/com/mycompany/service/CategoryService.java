/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Category;
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
public class CategoryService {
     public List<Category> getCategorys() throws SQLException{
        List<Category> cate = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM category";
            PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Category c = new Category(rs.getInt("id"),rs.getString("name"));
                 cate.add(c);
             }
        }
        return cate;
    }
     public Category getCategoryById(int id) throws SQLException{
       Category cate = null;
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM librarydb.category WHERE id = ?;";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                cate = new Category(rs.getInt("id"),rs.getString("name"));
            }
        }
        return cate;
    }
    public List<Category> getCategoryByName(String name) throws SQLException{
       List<Category> cate = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM librarydb.category";
            if (!"".equals(name)){
                sql +=" WHERE (name like concat('%',?,'%'))";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            if(!"".equals(name)){
                stm.setString(1, name);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Category c = new Category(rs.getInt("id"),rs.getString("name"));
                cate.add(c);
            }
        }
        return cate;
    }
    public boolean addCategory(Category c) throws SQLException{
        if (kiemTraDanhMuc(c.getName()))
            return false;
        else
            try (Connection conn = jdbcUtils.getConn()) {
                String sql ="INSERT INTO `librarydb`.`category` (`name`) VALUES (?);";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1,c.getName());
                stm.executeUpdate();
            }
        return true;
    }
    public void deleteCategory(int id) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql ="DELETE FROM `librarydb`.`category` WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,id);
            stm.executeUpdate();
        }
    }
    public boolean kiemTraDanhMuc(String name) throws SQLException{
        List<Category> cate = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql ="SELECT * FROM librarydb.category WHERE (name like concat(?));";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,name);
           ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Category c = new Category(rs.getInt("id"),rs.getString("name"));
                cate.add(c);
            }
            return cate.size() == 1;
        }
    }
}
