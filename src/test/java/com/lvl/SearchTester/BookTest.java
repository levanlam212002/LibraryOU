/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Book;
import com.mycompany.service.BookService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author HP
 */
public class BookTest {
    private static Connection conn;
    BookService b = new BookService();
   @BeforeAll
   public static void BeforeAll() throws SQLException{
       conn = jdbcUtils.getConn();
   }
   
   @AfterAll
   public static void AfterAll() throws SQLException{
       if(conn != null)
           conn.close();
   }
   @Test
   public void testSearchByName() throws SQLException
   {
       List<Book> kq = b.getBooks("cơ sở lập trình");
       Assertions.assertEquals(1, kq.size());
      
   }
   
   @Test
   public void testSearchByAuthor() throws SQLException
   {
        List<Book> kq = b.getBooksByAuthor("Lê Văn Lâm");
       Assertions.assertEquals(1, kq.size());
      
   }
   
   @Test
   public void testSearchByPublicationDate() throws SQLException
   {
        List<Book> kq = b.getBooksByDate("2022");
       Assertions.assertEquals(3, kq.size());
      
   }
   
   @Test
   public void testSearchByCategory() throws SQLException
   {
        List<Book> kq = b.getBooksByCategory("Tiểu thuyết");
       Assertions.assertEquals(2, kq.size());
      
   }
   
   @Test
   public void testAddBook() throws SQLException
   {
       Book book = new Book("Công nghệ phần mềm","Dương Hữu Thành","Các lý thuyết liên quan đến phần mềm",Date.valueOf("2021-02-21"),"NXB OU",Date.valueOf("2022-05-21"),"Hàng 12 cột 8",1,0);
       b.addBook(book);
       List<Book> kq = b.getBooks(null);
       Assertions.assertEquals(9, kq.size());
       List<Book> kq1 = b.getBooks("Công nghệ phần mềm");
       Assertions.assertEquals(2, kq1.size());
   }
   
   @Test
   public void testDeleteBook() throws SQLException{
       int id = 0;
       for (Book i : b.getBooks(null)){
            id = i.getId();
       }
       b.deleteBook(id);
       List<Book> kq = b.getBooks(null);
       Assertions.assertEquals(8, kq.size());
   }
   
   @Test
   public void testUpdateBook() throws SQLException{
       Book book = new Book(4,"Cô gái năm ấy","Nguyễn Hoài","Mối tình thanh xuân tươi đẹp",Date.valueOf("2021-03-21"),"NXB Học Hỏi",Date.valueOf("2023-03-10"),"Hàng 11 cột 7",2,0);
       b.updateBook(book);
       List<Book> kq = b.getBooksByAuthor("Nguyễn Hoài");
       Assertions.assertEquals(1, kq.size());
   }
   
   @Test
   public void testBorrowedBook() throws SQLException{
       b.BorrowedBook(13);
       List<Book> kq = b.getBooks("Sự tích con khỉ");
       kq.forEach(i -> {
           Assertions.assertEquals(1, i.getStatus());
        });
       b.PayBook(13);
   }
   
   @Test
   public void testPayBook() throws SQLException{
       b.BorrowedBook(13);
       b.PayBook(13);
       List<Book> kq = b.getBooks("Sự tích con khỉ");
       kq.forEach(i -> {
           Assertions.assertEquals(0, i.getStatus());
        });
   }
   
   @Test
   public void testBookNotborrowed() throws SQLException{
       List<Book> kq = b.getBooksNotBorrowed();
       Assertions.assertEquals(8, kq.size());
   }
   
   @Test
   public void KiemTraNgayQuaKhuTest(){
       Date a = Date.valueOf("2022-04-22");
       boolean result = b.KiemTraNgayQuaKhu(a);
       Assertions.assertTrue(result);
   }
   
   @Test
   public void KiemTraNgayTuongLaiTest(){
       Date a = Date.valueOf("2024-04-22");
       boolean result = b.KiemTraNgayTuongLai(a);
       Assertions.assertTrue(result);
   }
}
