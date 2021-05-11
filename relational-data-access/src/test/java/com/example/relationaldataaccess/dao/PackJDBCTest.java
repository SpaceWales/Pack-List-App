package com.example.relationaldataaccess.dao;

import com.example.relationaldataaccess.model.Item;
import com.example.relationaldataaccess.model.Pack;
import com.example.relationaldataaccess.model.Packlist;
import com.example.relationaldataaccess.model.User;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class PackJDBCTest {

    private static JdbcTemplate template;
    private static SingleConnectionDataSource dataSource;
    private static PackDAO packDAO;
    private static UserDAO userDAO;

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
        packDAO = new PackJDBC(template);
        userDAO = new UserJDBC(template);
    }

    @After
    public void tearDown() throws Exception {
        dataSource.getConnection().rollback();
    }

    @Test
    public void create_pack_list(){
        //test that pack and items are being created successfully in db
        Packlist localPacklist = createLocalPackList();
        //verify that packlist does not exist in db
        String packlistSql = "select * from packlist where user_id = ?";
        SqlRowSet emptyResults = template.queryForRowSet(packlistSql,localPacklist.getUser().getUser_id());
        //should return empty results
        Assert.assertFalse(emptyResults.next());

        //push packlist to db and verify results
        localPacklist = packDAO.createPack(localPacklist);

        //same query as pre-creation
        SqlRowSet packlistResults = template.queryForRowSet(packlistSql,localPacklist.getUser().getUser_id());

        Assert.assertTrue(packlistResults.next());
    }


    public Packlist createLocalPackList(){

        //user needs to be pushed into db, FK constraint on packlist
        User user = new User();
        user.setUsername("PackCreatingUser");
        userDAO.registerNewUser(user);

        Pack pack = new Pack();
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setItem_name("testItem");
        itemList.add(item);
        pack.setItemList(itemList);

        Packlist packlist = new Packlist();
        packlist.setPack(pack);
        packlist.setUser(user);
        return packlist;
    }
}