package persistence;

import entities.Member;
import exceptions.DatabaseException;
import exceptions.IllegalInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Uncomment the line below, to temporarily disable this test
//@Disabled
class MemberMapperTest {

    private final static String USER = "dev";
    private final static String PASSWORD = "ax2";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub_test?serverTimezone=CET&useSSL=false";

    private static Database db;
    private static MemberMapper memberMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            db = new Database(USER, PASSWORD, URL);
            memberMapper = new MemberMapper(db);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = db.connect()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("delete from registration");
                stmt.execute("delete from team");
                stmt.execute("delete from sport");
                stmt.execute("delete from member");
                stmt.execute("delete from zip");
                // Insert a well known number of members into the zip and member tables
                stmt.execute(   "INSERT INTO `zip` VALUES " +
                                "(3700,'Rønne'),(3730,'Nexø'),(3740,'Svanneke')" +
                                ",(3760,'Gudhjem'),(3770,'Allinge'),(3782,'Klemmensker')");
                stmt.execute("ALTER TABLE `member` DISABLE KEYS");
                stmt.execute("ALTER TABLE `member` AUTO_INCREMENT = 1");//auto_increment bliver nu nulstillet efter hver test
                stmt.execute(   "INSERT INTO `member` VALUES " +
                                "(1,'Hans Sørensen','2, Agernvej 3',3700,'m','2000')," +
                                "(2, 'Jens Kofoed','Agrevej 5',3700,'m','2001')," +
                                "(3, 'Peter Lundin','Ahlegårdsvejen 7',3700,'m','2002')");
                stmt.execute("ALTER TABLE `member` ENABLE KEYS");
            }
        } catch (SQLException throwables) {
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
            assertNotNull(db.connect());
    }

    @Test
    void getAllMembers() throws DatabaseException {
        List<Member> members = memberMapper.getAllMembers();
        assertEquals(3, members.size());
        assertEquals(members.get(0), new Member(1,"Hans Sørensen", "2, Agernvej 3",3700, "Rønne","m",2000));
        assertEquals(members.get(1), new Member(2, "Jens Kofoed","Agrevej 5",3700,"Rønne","m",2001));
        assertEquals(members.get(2), new Member(3, "Peter Lundin","Ahlegårdsvejen 7",3700,"Rønne","m",2002));
    }

    @Test
    void getMemberById() throws DatabaseException {
        assertEquals(new Member(3, "Peter Lundin","Ahlegårdsvejen 7",3700,"Rønne","m",2002), memberMapper.getMemberById(3));
    }

    @Test
    void deleteMember() throws DatabaseException {
        assertEquals(true, memberMapper.deleteMember(2));
        assertEquals(2, memberMapper.getAllMembers().size());
    }

    @Test
    void insertMember() throws DatabaseException, IllegalInputException {
        Member m1 = memberMapper.insertMember(new Member("Jon Snow","Wintherfell 3", 3760, "Gudhjem", "m", 1992));
        assertNotNull(m1);
        assertEquals(4, memberMapper.getAllMembers().size());
        assertEquals(m1.getMemberId(), memberMapper.getMemberById(m1.getMemberId()).getMemberId());
    }

    @Test
    void updateMember() throws DatabaseException {
        boolean result = memberMapper.updateMember(new Member(2, "Jens Kofoed","Agrevej 5",3760,"Gudhjem","m",1999));
        assertTrue(result);
        Member m1 = memberMapper.getMemberById(2);
        assertEquals(1999,m1.getYear());
        assertEquals(3, memberMapper.getAllMembers().size());
    }


}