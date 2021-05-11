package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.User;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

import static org.junit.Assert.*;

public class UserJDBCTest {

    private static SingleConnectionDataSource dataSource;
    private static UserDAO dao;
    private static JdbcTemplate template;

    private static final String USERNAME = "TESTBOB1";

    @BeforeClass
    public static void beforeClass() throws Exception {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/packlist");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        dataSource.setAutoCommit(false);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dataSource.destroy();
    }

    @Before
    public void setUp() throws Exception {
        template = new JdbcTemplate(dataSource);
        dao = new UserJDBC(template);
    }

    @After
    public void tearDown() throws Exception {
        dataSource.getConnection().rollback();
    }

    @Test
    public void register_new_user(){
        //test database connection and that new users are being registered

        //get initial size of userlist
        String dataRequest = "select count(*) from users";
        SqlRowSet countRequest = template.queryForRowSet(dataRequest);
        int beforeCount = 0;
        if(countRequest.next()) {
            beforeCount = countRequest.getInt("count");
        }
        //insert new user and check difference
        User user = new User();
        user.setUsername(USERNAME);
        dao.registerNewUser(user);

        countRequest = template.queryForRowSet(dataRequest);
        int afterCount = 0;
        if(countRequest.next()){
            afterCount = countRequest.getInt("count");
        }
        Assert.assertTrue(beforeCount + 1 == afterCount);
    }


    @Test
    public void returns_all_users(){
        //get user_list size
        String dataRequest = "select count(*) from users";
        SqlRowSet countRequest = template.queryForRowSet(dataRequest);
        int count = 0;
        if(countRequest.next()) {
             count = countRequest.getInt("count");
        }
        //get List of users, compare list size = count
        List<User> userList = dao.returnAllUsers();
        assertEquals(count,userList.size());
    }

}