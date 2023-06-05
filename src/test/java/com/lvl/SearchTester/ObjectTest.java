/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.service.ObjectService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.mycompany.pojo.Object;

/**
 *
 * @author HP
 */
public class ObjectTest {

    private static Connection conn;
    ObjectService o = new ObjectService();

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
    public void getObjectTest() throws SQLException {
        List<Object> kq = o.getObjects();
        Assertions.assertEquals(3, kq.size());
    }

    @Test
    public void addObjectTest() throws SQLException {
        Object c = new Object("Trợ giảng");
        List<Object> kq = o.getObjects();
        int id = 0;
        for(Object i : kq){
            id = i.getId();
        }
         o.deleteObject(id);
        Assertions.assertEquals(3, kq.size());
    }

    @Test
    public void addObject2Test() throws SQLException {
        Object c = new Object("Sinh viên");
        Assertions.assertFalse(o.addObject(c));
    }
    
    
    @Test
    public void kiemTraDanhMucTest() throws SQLException {
        String name = "Sinh viên";
        boolean result = o.kiemTraDoiTuong(name);
        Assertions.assertTrue(result);
    }

}
