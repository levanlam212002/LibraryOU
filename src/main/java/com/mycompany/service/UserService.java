/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.User;
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
public class UserService {
    public List<User> getUsers() throws SQLException{
        List<User> users = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM `librarydb`.`user`";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 User r = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("user_role"),rs.getInt("reader_id"));
                 users.add(r);
             }
        }
        return users;
    }
    public List<User> getUser(int kw) throws SQLException{
        List<User> users = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM `librarydb`.`user`";
            if (kw != 0)
                sql+= " WHERE (id = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != 0)
                 stm.setInt(1,kw);
            ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 User r = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("user_role"),rs.getInt("reader_id"));
                 users.add(r);
             }
        }
        return users;
    }
    public void addUser(User u) throws SQLException{
         try (Connection conn = jdbcUtils.getConn()) {
            String sql ="INSERT INTO `librarydb`.`user` (`username`, `password`, `user_role`, `reader_id`) VALUES (?,?,?,?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,u.getUsername());
            stm.setString(2,u.getPassword());
            stm.setString(3,u.getUser_role());
            stm.setInt(4,u.getReader_id());
            stm.executeUpdate();
        }
    }
    public void addAdmin(User u) throws SQLException{
         try (Connection conn = jdbcUtils.getConn()) {
            String sql ="INSERT INTO `librarydb`.`user` (`username`, `password`, `user_role`) VALUES (?,?,?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,u.getUsername());
            stm.setString(2,u.getPassword());
            stm.setString(3,u.getUser_role());
            stm.executeUpdate();
        }
    }
    public void updateUser(User  u) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`user` SET `password` = ? WHERE (`reader_id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,u.getPassword());
            stm.setInt(2,u.getReader_id());
            stm.executeUpdate();
        }
    }
    public void deleteAdmin(int id) throws SQLException{
         try (Connection conn = jdbcUtils.getConn()) {
             String sql = "DELETE FROM `librarydb`.`user` WHERE (`id` = ?);";
             PreparedStatement stm = conn.prepareStatement(sql);
             stm.setInt(1,id);
             stm.executeUpdate();
         }
    }
    public void deleteUser(int id) throws SQLException{
         try (Connection conn = jdbcUtils.getConn()) {
             String sql = "DELETE FROM `librarydb`.`user` WHERE (`reader_id` = ?);";
             PreparedStatement stm = conn.prepareStatement(sql);
             stm.setInt(1,id);
             stm.executeUpdate();
         }
    }
    public int KiemTraUser(List<User> u, String user){
        for(User i : u)
            if(i.getUsername().equals(user)) 
                return i.getId();
        return 0;
    }
    public int GetReaderId(List<User> u, int id){
        for(User i : u)
            if(i.getId() == id) 
                return i.getReader_id();
        return 0;
    }
    public boolean KiemTraDGCoTaiKhoanChua(List<User> u, int id){
        return u.stream().noneMatch(i -> (i.getReader_id() == id));
    }
    public boolean kiemTraMatKhau(User u,String pass){
        return u.getPassword().equals(pass);
    }
    public boolean kiemTraQuyenAdmin(User u){
        return u.getUser_role().equals("Thủ thư");
    }
}
