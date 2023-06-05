/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvl.SearchTester;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.service.CategoryService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.mycompany.pojo.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author HP
 */
public class CategoryTest {

    private static Connection conn;
    CategoryService cate = new CategoryService();

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
    public void getCategorysTest() throws SQLException {
        List<Category> kq = cate.getCategorys();
        Assertions.assertEquals(8, kq.size());
    }

    @Test
    public void getCategorysByIdTest() throws SQLException {
        Category kq = cate.getCategoryById(4);
        Assertions.assertEquals("Học thuật", kq.getName());
    }

    @Test
    public void getCategorysByNameTest() throws SQLException {
        List<Category> kq = cate.getCategoryByName("H");
        Assertions.assertEquals(5, kq.size());
    }

    @Test
    public void addCategoryTest() throws SQLException {
        Category c = new Category("Truyện ma");
        cate.addCategory(c);
        List<Category> kq = cate.getCategoryByName("Truyện ma");
        Assertions.assertEquals(1, kq.size());
        List<Category> kq1 = cate.getCategoryByName("Truyện ma");
        for (Category i : kq1) {
            cate.deleteCategory(i.getId());
        }
        kq = cate.getCategoryByName("Truyện ma");
        Assertions.assertEquals(0, kq.size());
    }

    @Test
    public void addCategory2Test() throws SQLException {
        Category c = new Category("Từ điển");
        Assertions.assertFalse(cate.addCategory(c));
    }

    @Test
    public void deleteCategory() throws SQLException {
        Category ct = new Category("Phim");
        cate.addCategory(ct);

        int id = 0;
        for (Category i : cate.getCategorys()) {
            id = i.getId();
        }
        cate.deleteCategory(id);
        List<Category> kq = cate.getCategorys();
        Assertions.assertEquals(8, kq.size());
    }

    @Test
    public void kiemTraDanhMucTest() throws SQLException {
        String name = "Báo";
        boolean result = cate.kiemTraDanhMuc(name);
        Assertions.assertTrue(result);
    }

}
