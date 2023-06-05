/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Readers;
import com.mycompany.service.ReaderService;
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
public class ReaderTest {

    private static Connection conn;
    ReaderService r = new ReaderService();

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
    public void getReadersTest() throws SQLException {
        List<Readers> kq = r.getReaders(0);
        Assertions.assertEquals(5, kq.size());
    }

    @Test
    public void getReaders2Test() throws SQLException {
        List<Readers> kq = r.getReaders(8);
        Assertions.assertEquals(1, kq.size());
    }

    @Test
    public void addReaderTest() throws SQLException {
        Readers reader = new Readers("Nguyễn Thanh Thuyền", "Nam", Date.valueOf("2002-02-21"), Date.valueOf("2021-05-21"), Date.valueOf("2022-05-21"), "Luật", "email@ou.edu.vn", "23 Hoàng Hoa Thám", "0946245527", 1);
        r.addReader(reader);
        List<Readers> kq = r.getReaders(0);
        Assertions.assertEquals(6, kq.size());
        int id = 0;
        for (Readers i : r.getReaders(0)) {
            id = i.getId();
        }
        r.deleteReader(id);
        List<Readers> kq1 = r.getReaders(0);
        Assertions.assertEquals(5, kq1.size());
    }

    @Test
    public void testDeleteReader() throws SQLException {
        Readers reader = new Readers("Nguyễn Thanh Thuyền", "Nam", Date.valueOf("2002-02-21"), Date.valueOf("2021-05-21"), Date.valueOf("2022-05-21"), "Luật", "email@ou.edu.vn", "23 Hoàng Hoa Thám", "0946245527", 1);
        r.addReader(reader);
        int id = 0;
        for (Readers i : r.getReaders(0)) {
            id = i.getId();
        }
        r.deleteReader(id);
        List<Readers> kq = r.getReaders(0);
        Assertions.assertEquals(5, kq.size());
    }

    @Test
    public void testUpdateReader() throws SQLException {
        int id = 11;
        List<Readers> kq = r.getReaders(id);
        for (Readers i : kq) {
            Assertions.assertEquals("Thái Tấn Phát", i.getName());
        }
        Readers reader = new Readers(id, "Thái Tấn Phúc", "Nam", Date.valueOf("2002-03-21"), Date.valueOf("2023-04-07"), Date.valueOf("2024-04-07"), "Tài chính ngân hàng", "tanphat@ou.edu.vn", "123 Thống Nhất", "0923462831", 1);
        r.updateReader(reader);
        kq = r.getReaders(id);
        for (Readers i : kq) {
            Assertions.assertEquals("Thái Tấn Phúc", i.getName());
        }
        Readers reader1 = new Readers(id, "Thái Tấn Phát", "Nam", Date.valueOf("2002-03-21"), Date.valueOf("2023-04-07"), Date.valueOf("2024-04-07"), "Tài chính ngân hàng", "tanphat@ou.edu.vn", "123 Thống Nhất", "0923462831", 1);
        r.updateReader(reader1);
    }

    @Test
    public void kiemTraHanTheTest() throws SQLException {
        for (Readers i : r.getReaders(1)) {
            Assertions.assertTrue(r.kiemTraHanThe(i.getEnd_date()));
        }
    }

    @Test
    public void kiemTraHanThe2Test() throws SQLException {
        for (Readers i : r.getReaders(3)) {
            Assertions.assertFalse(r.kiemTraHanThe(i.getEnd_date()));
        }
    }

    @Test
    public void kiemTraSDTTest() throws SQLException {
        Assertions.assertFalse(r.kiemTraSDT("09552821921"));
    }

    @Test
    public void kiemTraSDT2Test() throws SQLException {
        Assertions.assertFalse(r.kiemTraSDT("0955j21921"));
    }

    @Test
    public void kiemTraSDT3Test() throws SQLException {
        Assertions.assertFalse(r.kiemTraSDT("9955219219"));
    }

    @Test
    public void kiemTraSDT4Test() throws SQLException {
        Assertions.assertTrue(r.kiemTraSDT("0966287704"));
    }

    @Test
    public void kiemTraEmailTest() throws SQLException {
        Assertions.assertFalse(r.kiemTraEmail("lam@gmail.com"));
    }

    @Test
    public void kiemTraEmail2Test() throws SQLException {
        Assertions.assertTrue(r.kiemTraEmail("lam@ou.edu.vn"));
    }

    @Test
    public void KiemTraDocGiaTrungSDTTest() throws SQLException {
        List<Readers> list = r.getReaders(0);
        Assertions.assertFalse(r.KiemTraDocGiaTrungSDT(list, "0923462831", "Thái Tấn Phát", "Nam"));
    }
    
    @Test
    public void KiemTraDocGiaTrungEmailTest() throws SQLException{
        List<Readers> list = r.getReaders(0);
        Assertions.assertFalse(r.KiemTraDocGiaTrungEmail(list, "tanphat@ou.edu.vn", "Thái Tấn Phát", "Nam"));
    }
}
