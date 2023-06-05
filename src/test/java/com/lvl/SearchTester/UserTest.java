/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.User;
import com.mycompany.service.UserService;
import java.sql.Connection;
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
public class UserTest {

    private static Connection conn;
    UserService u = new UserService();

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
    public void getUsersTest() throws SQLException {
        List<User> kq = u.getUsers();
        Assertions.assertEquals(5, kq.size());
    }

    @Test
    public void getUserTest() throws SQLException {
        List<User> kq = u.getUser(4);
        kq.forEach(i -> {
            Assertions.assertEquals("hoang", i.getUsername());
        });
    }

    @Test
    public void addUserTest() throws SQLException {
        User user = new User("thuyen", "123", "Độc giả", 11);
        u.addUser(user);
        List<User> kq = u.getUsers();
        Assertions.assertEquals(6, kq.size());
        int id = 0;
        for (User i : u.getUsers()) {
            id = i.getReader_id();
        }
        u.deleteUser(id);
        List<User> kq1 = u.getUsers();
        Assertions.assertEquals(5, kq1.size());
    }

    @Test
    public void addAdminTest() throws SQLException {
        User user = new User("admin1", "123", "Thủ thư");
        u.addAdmin(user);
        List<User> kq = u.getUsers();
        Assertions.assertEquals(6, kq.size());
        int id = 0;
        for (User i : u.getUsers()) {
            id = i.getId();
        }
        u.deleteAdmin(id);
    }

    @Test
    public void deleteUserTest() throws SQLException {
        User user = new User("thuyen", "123", "Độc giả", 11);
        u.addUser(user);
        int id = 0;
        for (User i : u.getUsers()) {
            id = i.getReader_id();
        }
        u.deleteUser(id);
        List<User> kq = u.getUsers();
        Assertions.assertEquals(5, kq.size());
    }

    @Test
    public void kiemTraUserTest() throws SQLException {
        Assertions.assertEquals(1, u.KiemTraUser(u.getUsers(), "admin"));
    }

    @Test
    public void kiemTraUser2Test() throws SQLException {
        Assertions.assertEquals(0, u.KiemTraUser(u.getUsers(), "vi"));
    }

    @Test
    public void kiemTraDaCoTaiKhoanTest() throws SQLException {
        Assertions.assertFalse(u.KiemTraDGCoTaiKhoanChua(u.getUsers(), 3));
    }

    @Test
    public void kiemTraDaCoTaiKhoan2Test() throws SQLException {
        Assertions.assertTrue(u.KiemTraDGCoTaiKhoanChua(u.getUsers(), 9));
    }

    @Test
    public void kiemTraMatKhauTest() throws SQLException {
        for (User i : u.getUser(3)) {
            Assertions.assertTrue(u.kiemTraMatKhau(i, "123"));
        }
    }

    @Test
    public void kiemTraMatKhau2Test() throws SQLException {
        for (User i : u.getUser(3)) {
            Assertions.assertFalse(u.kiemTraMatKhau(i, "1234"));
        }
    }

    @Test
    public void kiemTraAdminTest() throws SQLException {
        for (User i : u.getUser(1)) {
            Assertions.assertTrue(u.kiemTraQuyenAdmin(i));
        }
    }

    @Test
    public void kiemTraAdmin2Test() throws SQLException {
        for (User i : u.getUser(3)) {
            Assertions.assertFalse(u.kiemTraQuyenAdmin(i));
        }
    }
}
