/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Book;
import com.mycompany.pojo.Muon;
import com.mycompany.pojo.Reader_card;
import com.mycompany.service.MuonService;
import com.mycompany.service.ReaderCardService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author HP
 */
public class MuonTest {

    private static Connection conn;
    ReaderCardService rd = new ReaderCardService();
    MuonService ms = new MuonService();

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = jdbcUtils.getConn();
    }

    @AfterAll
    public static void AfterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    public void deleteMuonTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(a, b, b, 3, 0, 0);
        List<Muon> m = new ArrayList<>();
        Muon muon = new Muon(r.getId(), 4);
        Muon muon1 = new Muon(r.getId(), 5);
        m.add(muon1);
        m.add(muon);
        rd.addCard(r, m);
        List<Reader_card> kq = rd.getCard(3);
        Assertions.assertEquals(19, kq.size());
        for (Muon i : ms.getMuonDelete(r.getId())) {
            ms.deleteMuon(i.getId());
        }
        System.out.println(r.getId());
        rd.deleteCard(r.getId());
        List<Reader_card> kq1 = rd.getCard(3);
        Assertions.assertEquals(18, kq1.size());
    }
    
    
    @Test
    public void getByBookIdTest() throws SQLException {
        int bookId = 10;
        List<Muon> muonList = ms.getByBookId(bookId);

        Assertions.assertEquals(10, muonList.size());
        
        for (Muon muon : muonList) {
            Assertions.assertEquals(bookId, muon.getBook_id());
        }
    }

    @Test
    public void getByCardIDTest() throws SQLException {
        String cardID = "10d474bf-8c09-4cab-a3c2-ef88dbea9432";
        
        List<Muon> muonList = ms.getByCardId(cardID);

        Assertions.assertEquals(1, muonList.size());
        
        for (Muon muon : muonList) {
            Assertions.assertEquals(cardID, muon.getCard_id());
        }
    }
    

    @Test
    public void KIemTraSachMuon4Test() throws SQLException {
        List<Book> book = new ArrayList<>();
        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();
        Book b4 = new Book();
        book.add(b1);
        book.add(b2);
        book.add(b3);
        book.add(b4);
        Assertions.assertFalse(ms.kiemTraSoSachMuon(book));
    }

    @Test
    public void KIemTraSachMuon5Test() throws SQLException {
        List<Book> book = new ArrayList<>();
        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();
        Book b4 = new Book();
        Book b5 = new Book();
        book.add(b1);
        book.add(b2);
        book.add(b3);
        book.add(b4);
        book.add(b5);
        Assertions.assertFalse(ms.kiemTraSoSachMuon(book));
    }
    
    @Test
    public void KIemTraSachMuon6Test() throws SQLException {
        List<Book> book = new ArrayList<>();
        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();
        Book b4 = new Book();
        Book b5 = new Book();
        Book b6 = new Book();
        book.add(b1);
        book.add(b2);
        book.add(b3);
        book.add(b4);
        book.add(b5);
        book.add(b6);
        Assertions.assertTrue(ms.kiemTraSoSachMuon(book));
    }
    @Test
    public void getMuonMuonDeleteTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(a, b, b, 3, 0, 0);
        List<Muon> m = new ArrayList<>();
        Muon muon = new Muon(r.getId(), 4);
        Muon muon1 = new Muon(r.getId(), 5);
        m.add(muon1);
        m.add(muon);
        rd.addCard(r, m);
        List<Reader_card> kq = rd.getCard(3);
        Assertions.assertEquals(20, kq.size());
        List<Muon> kq1 = ms.getMuonDelete(r.getId());
        Assertions.assertEquals(2, kq1.size());
        for (Muon i : ms.getMuonDelete(r.getId())) {
            ms.deleteMuon(i.getId());
        }
        rd.deleteCard(r.getId());
    }
}
