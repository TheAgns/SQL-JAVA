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

    private final static String USER = "root";
    private final static String PASSWORD = "Vinter2020";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub_test?serverTimezone=CET&useSSL=false";

    private static Database db;
    private static MemberMapper memberMapper;

    /*
    Inden testene bliver udført, bliver denne BeforeAll altid udført først. Og den bliver kun udført 1 gang.
     */
    @BeforeAll
    public static void setUpClass() {
        try {
            db = new Database(USER, PASSWORD, URL);
            memberMapper = new MemberMapper(db);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    Bliver kørt hver gang før hver metode.
     */
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

    /*
    Sørger for Connection bliver etableret. Og hvis ikke vil Connection være null.
     */
    @Test
    void testConnection() throws SQLException, ClassNotFoundException {
            assertNotNull(db.connect());
            //forkert brugernavn/password/url
           /* db = new Database("muystjaidfj","heheh","heheh URL!!");
            assertNotNull(db.connect()); */
    }

    /*
    Inden hver test bliver udført bliver der addet 3 members til member tabellen. Disse members er tilsvarende
    de members vi tester for i denne test.
     */
    @Test
    void getAllMembers() throws DatabaseException {
        List<Member> members = memberMapper.getAllMembers();
        assertEquals(3, members.size());
        assertEquals(members.get(0), new Member(1,"Hans Sørensen", "2, Agernvej 3",3700, "Rønne","m",2000));
        assertEquals(members.get(1), new Member(2, "Jens Kofoed","Agrevej 5",3700,"Rønne","m",2001));
        assertEquals(members.get(2), new Member(3, "Peter Lundin","Ahlegårdsvejen 7",3700,"Rønne","m",2002));
    }

/*
Ser om getMemberById faktisk tager fat i det rigtige id
 */
    @Test
    void getMemberById() throws DatabaseException {
        assertEquals(new Member(3, "Peter Lundin","Ahlegårdsvejen 7",3700,"Rønne","m",2002), memberMapper.getMemberById(3));
    }

    //Tester om den håndtere et memberId der ikke eksistere rigtigt.
    @Test()
    void getMemberByIdIfItDosentExit() throws DatabaseException {
      /*  try {
            memberMapper.getMemberById(5);
            memberMapper.getMemberById(7);
            fail("fail på getMemberByIdIfItDosentExit");
        } catch (DatabaseException e){

        }*/
        assertThrows(DatabaseException.class, () -> memberMapper.getMemberById(7));
    }

    @Test
    void deleteMember() throws DatabaseException {
        assertEquals(true, memberMapper.deleteMember(2));
        assertEquals(2, memberMapper.getAllMembers().size());
    }

    @Test
    void deleteMemberThatDontExist() throws DatabaseException {
        assertThrows(DatabaseException.class,() -> memberMapper.deleteMember(123));
    }

    @Test
    void insertMember() throws DatabaseException, IllegalInputException {
        Member m1 = memberMapper.insertMember(new Member("Jon Snow","Wintherfell 3", 3760, "Gudhjem", "m", 1992));
        assertNotNull(m1);
        assertEquals(4, memberMapper.getAllMembers().size());
        assertEquals(m1.getMemberId(), memberMapper.getMemberById(m1.getMemberId()).getMemberId());
    }

    @Test
    void insertMemberWithWrongData() throws DatabaseException, IllegalInputException {
        //Member m1 = memberMapper.insertMember(new Member("Jon Snow","Wintherfell 3", 3760, "Gudhjem", "c", 1992));
        assertThrows(IllegalInputException.class,() -> memberMapper.insertMember(new Member("Jon Snow","Wintherfell 3", 3760, "Gudhjem", "c", 1992)));
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