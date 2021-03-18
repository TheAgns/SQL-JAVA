import entities.Member;
import entities.Registration;
import exceptions.DatabaseException;
import exceptions.IllegalInputException;
import persistence.Database;
import persistence.MemberMapper;
import persistence.RegistrationMapper;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private final static String USER = "root";
    private final static String PASSWORD = "Vinter2020";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub?serverTimezone=CET&useSSL=false";
    private static MemberMapper memberMapper;
    private static RegistrationMapper registrationMapper;
    private static Database db;

    public static void main(String[] args) {

        try {
            Database db = new Database(USER, PASSWORD, URL);
            memberMapper = new MemberMapper(db);
            registrationMapper = new RegistrationMapper(db);

            try {
                List<Member> members = memberMapper.getAllMembers();
                showMembers(members);
                showMemberById(memberMapper, 13);
                int newMemberId = insertMember(memberMapper);
                // deleteMember(newMemberId, memberMapper);
                showMembers(members);
                updateMember(13, memberMapper);
                List<Registration> registrations = registrationMapper.getAllRegistrations();
                showRegistrations(registrations);
                Registration registrationToAdd = insertMemberToTeam(registrationMapper);
                showRegistrations(registrations);

            } catch (DatabaseException | IllegalInputException ex) {
                System.out.println("Problemer med databasen: " + ex.getMessage());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("An error has occured: " + ex.getMessage());
            }
        }

        private static void deleteMember(int memberId, MemberMapper memberMapper)  throws DatabaseException {
        if (memberMapper.deleteMember(memberId)){
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) throws DatabaseException, IllegalInputException {
        Member m1 = new Member("Ole Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static Registration insertMemberToTeam(RegistrationMapper registrationMapper) throws DatabaseException, SQLException {
        Registration r1 = new Registration(2,"yo01",100);
        Registration r2 = registrationMapper.addToTeam(r1.getMemberId(),r1.getTeamId());
        return r2;
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) throws DatabaseException {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if(memberMapper.updateMember(m1)){
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId)  throws DatabaseException {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

    private static void showRegistrations(List<Registration> registrations){
        System.out.println("***** Vis alle registrations *****");
        for (Registration registration: registrations){
            System.out.println(registration.toString());
        }
    }


}
