/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
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
public class ReaderCardTest {

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
    public void getReaderCardTest() throws SQLException {
        List<Reader_card> kq = rd.getCard(1);
        Assertions.assertEquals(7, kq.size());
    }

    @Test
    public void getByReaderIDTest() throws SQLException{
        List<Reader_card> kq = rd.getByReaderID(3);
        Assertions.assertEquals(20, kq.size());
    }
    
    @Test
    public void AddReaderCardTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(a,b,b,3, 0, 0);
        List<Muon> m = new ArrayList<>();
        Muon muon = new Muon(r.getId(), 4);
        Muon muon1 = new Muon(r.getId(), 5);
        m.add(muon1);
        m.add(muon);
        rd.addCard(r, m);
        List<Reader_card> kq = rd.getCard(3);
        Assertions.assertEquals(23, kq.size());
        for (Muon i : ms.getMuonDelete(r.getId())) {
            ms.deleteMuon(i.getId());
        }
        System.out.println(r.getId());
        rd.deleteCard(r.getId());
        List<Reader_card> kq1 = rd.getCard(3);
        Assertions.assertEquals(22, kq1.size());
    }

    @Test
    public void GetBookTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(3, 0, 0);
        rd.GetBook(a, b, r.getId());
        for (Reader_card i : rd.getCard(3)) {
            if (i.getId().equals(r.getId())) {
                Assertions.assertEquals(1, i.getGet_books());
            }
        }
        rd.deleteCard(r.getId());
        List<Reader_card> kq1 = rd.getCard(3);
        Assertions.assertEquals(22, kq1.size());
    }

    @Test
    public void PayBookTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(a,b,3, 1, 0);
        rd.PayBook(a, r.getId());
        for (Reader_card i : rd.getCard(3)) {
            if (i.getId().equals(r.getId())) {
                Assertions.assertEquals(1, i.getPay_book());
            }
        }
        rd.deleteCard(r.getId());
        List<Reader_card> kq1 = rd.getCard(3);
        Assertions.assertEquals(22, kq1.size());
    }

    @Test
    public void DeleteCardTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Reader_card r = new Reader_card(a,b,b,3, 0, 0);
        List<Muon> m = new ArrayList<>();
        Muon muon = new Muon(r.getId(), 4);
        Muon muon1 = new Muon(r.getId(), 5);
        m.add(muon1);
        m.add(muon);
        rd.addCard(r, m);
        for (Muon i : ms.getMuonDelete(r.getId())) {
            ms.deleteMuon(i.getId());
        }
        rd.deleteCard(r.getId());
        List<Reader_card> kq = rd.getCard(3);
        Assertions.assertEquals(22, kq.size());
    }

    @Test
    public void KiemTraDaTraHetSachChuaTest() throws SQLException {
        List<Reader_card> rc = rd.getCard(8);
        rc.forEach(i -> {
            Assertions.assertFalse(rd.kiemTraChuaTraHetSach(i));
        });
    }

    @Test
    public void KiemTraNgayMuonNhoHon30Test() throws SQLException {
        Date a = Date.valueOf("2023-05-16");
        Date b = Date.valueOf("2023-04-17");
        Assertions.assertFalse(rd.kiemTraNgayMuon(a, b));
    }

    @Test
    public void KiemTraNgayMuonBang30Test() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Assertions.assertFalse(rd.kiemTraNgayMuon(a, b));
    }

    @Test
    public void KiemTraNgayMuonLonHon30Test() throws SQLException {
        Date a = Date.valueOf("2023-05-18");
        Date b = Date.valueOf("2023-04-17");
        Assertions.assertTrue(rd.kiemTraNgayMuon(a, b));
    }
     @Test
    public void KiemTraTraDung30NgayTest() throws SQLException {
        Date a = Date.valueOf("2023-05-17");
        Date b = Date.valueOf("2023-04-17");
        Assertions.assertEquals(30,rd.kiemTraTraTre(a, b));
    }
    @Test
    public void KiemTraTraTreTest() throws SQLException {
        Date a = Date.valueOf("2023-05-18");
        Date b = Date.valueOf("2023-04-17");
        Assertions.assertEquals(31,rd.kiemTraTraTre(a, b));
    }
    @Test
    public void BookingBookOnlineTest() throws SQLException {
        Date a = Date.valueOf("2023-04-18");
        Reader_card r = new Reader_card(8,0,0,a,1);
        List<Muon> m = new ArrayList<>();
        Muon muon = new Muon(r.getId(), 4);
        Muon muon1 = new Muon(r.getId(), 5);
        m.add(muon1);
        m.add(muon);
        rd.DatOnline(r, m);
        List<Reader_card> kq = rd.getCardBooking();
        Assertions.assertEquals(8, kq.size());
        for (Muon i : ms.getMuonDelete(r.getId())) {
            ms.deleteMuon(i.getId());
        }
        rd.deleteCard(r.getId());
        List<Reader_card> kq1 = rd.getCardBooking();
        Assertions.assertEquals(7, kq1.size());
    }
    @Test
    public void KiemTraHanLaySachDaDatDuoi48Test() throws SQLException {
        Date a = Date.valueOf("2023-05-18");
        Date b = Date.valueOf("2023-05-17");
        Assertions.assertFalse(rd.kiemTraHanLaySachDaDat(a, b));
    }
    @Test
    public void KiemTraHanLaySachDaDatBang48Test() throws SQLException {
        Date a = Date.valueOf("2023-05-19");
        Date b = Date.valueOf("2023-05-17");
        Assertions.assertFalse(rd.kiemTraHanLaySachDaDat(a, b));
    }
    @Test
    public void KiemTraHanLaySachDaDatHon48Test() throws SQLException {
        Date a = Date.valueOf("2023-05-20");
        Date b = Date.valueOf("2023-05-17");
        Assertions.assertTrue(rd.kiemTraHanLaySachDaDat(a, b));
    }
    @Test
    public void getCardByBookingTest() throws SQLException {
        List<Reader_card> kq = rd.getCardBooking();
        Assertions.assertEquals(7, kq.size());
    }
    @Test
    public void getCardByBorrowedTest() throws SQLException {
        List<Reader_card> kq = rd.getCardByBorrowed(2023, 0);
        Assertions.assertEquals(1, kq.size());
    }
    @Test
    public void getCardByBorrowed1Test() throws SQLException {
        List<Reader_card> kq = rd.getCardByBorrowed(2023, 4);
        Assertions.assertEquals(24, kq.size());
    }
    @Test
    public void getCardByPayTest() throws SQLException {
        List<Reader_card> kq = rd.getCardByPay(2023, 1);
        Assertions.assertEquals(23, kq.size());
    }
    @Test
    public void getCardByPay1Test() throws SQLException {
        List<Reader_card> kq = rd.getCardByPay(2023, 4);
        Assertions.assertEquals(24, kq.size());
    }
    @Test
    public void getCardByPayLateTest() throws SQLException {
        List<Reader_card> kq = rd.getCardByPayLate();
        Assertions.assertEquals(3, kq.size());
    }
    
}
