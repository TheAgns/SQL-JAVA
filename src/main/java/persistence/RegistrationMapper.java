package persistence;

import entities.Member;
import entities.Registration;
import exceptions.DatabaseException;
import exceptions.IllegalInputException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper {
    //INSERT INTO `sportsclub`.`registration` (`member_id`, `team_id`, `price`) VALUES ('50', 'fo01', '122');
    private Database database;
    private List<Registration> registrations = new ArrayList<>();


    public RegistrationMapper(Database database) {
        this.database = database;
    }

    public Registration addToTeam(int memberId, String teamId) throws SQLException, DatabaseException {
        boolean result = false;
        int newId = 0;
        int defualtPrice = 100;
        Registration registration;

        String sql = "INSERT INTO `sportsclub_test`.`registration` (`member_id`, `team_id`, `price`) VALUES (?, ?, ?);";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,memberId);
                if(teamId.equals("yo01") || teamId.equals("yo02") || teamId.equals("fo01") || teamId.equals("h01")
                        || teamId.equals("tai01") || teamId.equals("tai02") || teamId.equals("sv01")
                        || teamId.equals("sv02") || teamId.equals("ten01") || teamId.equals("ten02")
                        || teamId.equals("bt01") || teamId.equals("gym01")) {
                    ps.setString(2, teamId);
                } else {
                    System.out.println("fejl");
                }
                ps.setInt(3,defualtPrice);//TODO: tjek pris og sæt den rigtige pris ind
                registration = new Registration(memberId,teamId,defualtPrice);
                registrations.add(registration);
                ps.executeUpdate();


            }

        } catch (SQLException throwables) {
            throw new DatabaseException("Could not establish connection to database");
        }


        return registration;
    }
//`member_id`, `team_id`, `price`
    public List<Registration> getAllRegistrations() throws DatabaseException {


        String sql = "SELECT * FROM sportsclub_test.registration;";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int memberId = rs.getInt("member_id");
                    String teamId = rs.getString("team_id");
                    int price = rs.getInt("price");
                    registrations.add(new Registration(memberId,teamId,price));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Could not get all registrations from database");
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException("Could not establish connection to database");
        }
        return registrations;
    }
}
