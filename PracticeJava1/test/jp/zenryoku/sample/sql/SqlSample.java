package jp.zenryoku.sample.sql;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

public class SqlSample {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:";

    private static final String DB_NAME = "MyDbTest";
    private static final String SHOW_TABLE = "select st.tablename  from sys.systables st LEFT OUTER join sys.sysschemas ss on (st.schemaid = ss.schemaid)";

    private static Connection con;

    @BeforeAll
    public static void init() {
        Properties props = new Properties(); // connection properties
        // providing a user name and password is optional in the embedded
        // and derbyclient frameworks
        props.put("user", "user1");
        props.put("password", "user1");

        try {
            con = DriverManager.getConnection(protocol + DB_NAME + ";create=true", props);
//            System.out.println("Connected to and created database " + DB_NAME);
            con.setAutoCommit(false);
            ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
            PreparedStatement psInsert;
            PreparedStatement psUpdate;
            Statement s;
            ResultSet rs = null;

            s = con.createStatement();
            statements.add(s);
            s.execute("create table location(num int, addr varchar(40))");

            psInsert = con.prepareStatement(
                    "insert into location values (?, ?)");
            statements.add(psInsert);

            psInsert.setInt(1, 1956);
            psInsert.setString(2, "Webster St.");
            psInsert.executeUpdate();
//            System.out.println("Inserted 1956 Webster");

            psInsert.setInt(1, 1910);
            psInsert.setString(2, "Union St.");
            psInsert.executeUpdate();

            psUpdate = con.prepareStatement(
                    "update location set num=?, addr=? where num=?");
            statements.add(psUpdate);

            psUpdate.setInt(1, 180);
            psUpdate.setString(2, "Grand Ave.");
            psUpdate.setInt(3, 1956);
            psUpdate.executeUpdate();
//            System.out.println("Updated 1956 Webster to 180 Grand");

            psUpdate.setInt(1, 300);
            psUpdate.setString(2, "Lakeshore Ave.");
            psUpdate.setInt(3, 180);
            psUpdate.executeUpdate();

            rs = s.executeQuery(
                    "SELECT num, addr FROM location ORDER BY num");

//            while (rs.next()) {
//                System.out.println(rs.getInt(1));
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @AfterAll
//    public static void tearDown() {
//        try {
//            DriverManager.getConnection("jdbc:derby:" + DB_NAME + ";shutdown=true");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private ResultSet exeSelect(String sql) {
        ResultSet result = null;
        try {
            Statement stm = con.createStatement();
            result = stm.executeQuery(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        return result;
    }

    private void printSet(ResultSet res) {
        try {
            while (res.next()) {
                System.out.print("Num: " + res.getInt(1) + " / ");
                System.out.println("Addr: " + res.getString(2));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private void printSet(ResultSet res, boolean isStr) {
        try {
            while (res.next()) {
                System.out.println(res.getString(1));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private void showTables() {
        ResultSet res = exeSelect(SHOW_TABLE);
        printSet(res, true);
    }
    @Test
    public void testSelect() throws Exception {
        Statement stm = con.createStatement();
        stm.execute("select * from location");
        ResultSet rs = stm.getResultSet();

        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
    }

    @Test
    public void testSelect2() {
        ResultSet res = exeSelect("select * from location");
        printSet(res);
    }

    @Test
    public void testSelectCount() {
        String sql = "select count(*) from location";
        ResultSet res = exeSelect(sql);
        printSet(res, true);
    }


}
