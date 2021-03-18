package persistence;

import entities.Registration;
import exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "Vinter2020";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub_test?serverTimezone=CET&useSSL=false";
    private static Database db;
    private static RegistrationMapper registrationMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            db = new Database(USER, PASSWORD, URL);
            registrationMapper = new RegistrationMapper(db);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('1', 'h01', '128');
INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('2', 'gym01', '128');
INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('3', 'ten01', '119');
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
                stmt.execute("INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('1', 'h01', '128');");
                stmt.execute("INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('2', 'gym01', '128');");
                stmt.execute("INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES ('3', 'ten01', '119');");
            }
        } catch (SQLException throwables) {
            fail("Database connection failed");
        }
    }

    @Test
    void addToTeam() throws DatabaseException, SQLException {
        Registration expected1 = new Registration(2,"yo02",100);
        String expected = expected1.getTeamId();
        Registration actual1 = registrationMapper.addToTeam(4,"yo02");
        String actual = actual1.getTeamId();
        assertEquals(expected,actual);
    }


    @Test
    void getAllRegistrations() throws DatabaseException {
        List<Registration> registrations = registrationMapper.getAllRegistrations();
        int expected = 3;
        int actual = registrations.size();
        assertEquals(expected,actual);
    }
}