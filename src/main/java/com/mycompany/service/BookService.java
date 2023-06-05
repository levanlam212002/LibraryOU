/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Book;
import com.mycompany.pojo.Category;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class BookService {
    public List<Book> getBooks(String kw) throws SQLException{
        List<Book> books = new ArrayList<>();
        CategoryService c = new CategoryService();
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM book";
            
            if (kw != null)
                sql += " WHERE (name like concat('%',?,'%'));";
            PreparedStatement stm = conn.prepareStatement(sql);
             if (kw != null)
             {
                 stm.setString(1,kw);
             }
             
             ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Book b = new Book(rs.getInt("id"),rs.getString("name"),
                         rs.getString("author"),rs.getString("describe"),rs.getDate("publication_date"),rs.getString("publication_place"),rs.getDate("added_date"),rs.getString("location"),c.getCategoryById(rs.getInt("category_id")).getName(),rs.getInt("category_id"),rs.getInt("status"));
                 books.add(b);
             }
        }
        return books;
    }
    public List<Book> getBooksByAuthor(String kw) throws SQLException{
        List<Book> books = new ArrayList<>();
        CategoryService c = new CategoryService();
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM book";
            
            if (kw != null)
                sql += " WHERE (author like concat('%',?,'%'));";
            PreparedStatement stm = conn.prepareStatement(sql);
             if (kw != null)
             {
                 stm.setString(1,kw);
             }
             
             ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Book b = new Book(rs.getInt("id"),rs.getString("name"),
                         rs.getString("author"),rs.getString("describe"),rs.getDate("publication_date"),rs.getString("publication_place"),rs.getDate("added_date"),rs.getString("location"),c.getCategoryById(rs.getInt("category_id")).getName(),rs.getInt("category_id"),rs.getInt("status"));
                 books.add(b);
             }
        }
        return books;
    }
    public List<Book> getBooksByCategory(String kw) throws SQLException{
        List<Book> books = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            CategoryService c = new CategoryService();
            List<Category> cate = c.getCategoryByName(kw);
            for(Category i : cate){
                String sql ="SELECT * FROM book WHERE (category_id = ?);";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1,i.getId());
                ResultSet rs = stm.executeQuery();
                 while (rs.next()){
                     Book b = new Book(rs.getInt("id"),rs.getString("name"),
                             rs.getString("author"),rs.getString("describe"),rs.getDate("publication_date"),rs.getString("publication_place"),rs.getDate("added_date"),rs.getString("location"),i.getName(),rs.getInt("category_id"),rs.getInt("status"));
                     books.add(b);
                    }
            }
        }
        return books;
    }
    public List<Book> getBooksByDate(String kw) throws SQLException{
        List<Book> books = new ArrayList<>();
        CategoryService c = new CategoryService();
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM book";
            
            if (kw != null)
                sql += " WHERE (CONVERT(YEAR(publication_date),char) like concat('%',?,'%'));";
            PreparedStatement stm = conn.prepareStatement(sql);
             if (kw != null)
             {
                 stm.setString(1,kw);
             }
             
             ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Book b = new Book(rs.getInt("id"),rs.getString("name"),
                         rs.getString("author"),rs.getString("describe"),rs.getDate("publication_date"),rs.getString("publication_place"),rs.getDate("added_date"),rs.getString("location"),c.getCategoryById(rs.getInt("category_id")).getName(),rs.getInt("category_id"),rs.getInt("status"));
                 books.add(b);
             }
        }
        return books;
    }
    public void addBook(Book b) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql ="INSERT INTO `librarydb`.`book` (`name`, `author`, `describe`, `publication_date`, `publication_place`, `added_date`, `location`, `category_id`,`status`) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,b.getName());
            stm.setString(2,b.getAuthor());
            stm.setString(3,b.getDescribe());
            stm.setDate(4,b.getPublication_date());
            stm.setString(5,b.getPublication_place());
            stm.setDate(6,b.getAdded_date());
            stm.setString(7,b.getLocation());
            stm.setInt(8,b.getCategory_id());
            stm.setInt(9,b.getStatus());
            stm.executeUpdate();
        }
    }
    public void deleteBook(int id) throws SQLException{
         try (Connection conn = jdbcUtils.getConn()) {
             String sql = "DELETE FROM `librarydb`.`book` WHERE (`id` = ?);";
             PreparedStatement stm = conn.prepareStatement(sql);
             stm.setInt(1,id);
             stm.executeUpdate();
         }
    }
    public void updateBook(Book b) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`book` SET `name` = ?,`author` = ?,`describe` = ?,`publication_date` = ?,`publication_place` = ?,`added_date` = ?,`location` = ?,`category_id` = ? WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,b.getName());
            stm.setString(2,b.getAuthor());
            stm.setString(3,b.getDescribe());
            stm.setDate(4,b.getPublication_date());
            stm.setString(5,b.getPublication_place());
            stm.setDate(6,b.getAdded_date());
            stm.setString(7,b.getLocation());
            stm.setInt(8,b.getCategory_id());
            stm.setInt(9,b.getId());
            stm.executeUpdate();
        }
    }
    public void BorrowedBook(int id) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`book` SET `status` = ? WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,1);
            stm.setInt(2,id);
            stm.executeUpdate();
        }
    }
    public void PayBook(int id) throws SQLException{
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "UPDATE `librarydb`.`book` SET `status` = ? WHERE (`id` = ?);";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,0);
            stm.setInt(2,id);
            stm.executeUpdate();
        }
    }
    public List<Book> getBooksNotBorrowed() throws SQLException{
        List<Book> books = new ArrayList<>();
     
        try(Connection conn = jdbcUtils.getConn()){
            String sql ="SELECT * FROM book WHERE status = ?";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,0);
            ResultSet rs = stm.executeQuery();
             while (rs.next()){
                 Book b = new Book(rs.getInt("id"),rs.getString("name"),
                         rs.getString("author"),rs.getString("describe"),rs.getDate("publication_date"),rs.getString("publication_place"),rs.getDate("added_date"),rs.getString("location"),rs.getInt("category_id"),rs.getInt("status"));
                 books.add(b);
             }
        }
        return books;
    }
    public boolean KiemTraNgayQuaKhu(Date a){
        return a.before(Date.valueOf(java.time.LocalDate.now())) && !a.equals(Date.valueOf(java.time.LocalDate.now()));
    }
    public boolean KiemTraNgayTuongLai(Date a){
        return a.after(Date.valueOf(java.time.LocalDate.now())) && !a.equals(Date.valueOf(java.time.LocalDate.now()));
    }
}
