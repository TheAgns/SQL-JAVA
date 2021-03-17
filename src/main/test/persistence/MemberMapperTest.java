package persistence;

import entities.Member;
import org.junit.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {
MemberMapper memberMapper;
     String USER;
     String PASSWORD;
     String URL;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        URL = "jdbc:mysql://localhost:3306/sportsclub?serverTimezone=CET&useSSL=false";
        PASSWORD = "Vinter2020";
        USER = "root";
        memberMapper = new MemberMapper(new Database(USER,PASSWORD,URL));
    }

    @org.junit.jupiter.api.Test
    void getAllMembers() {
        List<Member> allMembers = memberMapper.getAllMembers();
        String expected = "Jens Kofoed";
        String acutal = allMembers.get(1).getName();
        assertEquals(expected,acutal);
    }

    @org.junit.jupiter.api.Test
    void getMemberById() {
        String getGenderById = memberMapper.getMemberById(12).getGender();
        String expected = "m";
        String acutal = getGenderById;
        assertEquals(expected,acutal);
    }

    @org.junit.jupiter.api.Test
    void deleteMember() {
        //Need a fake database to test this.
    }

    @org.junit.jupiter.api.Test
    void insertMember() {
        //need af fake database to this.
    }

    @org.junit.jupiter.api.Test
    void updateMember() {
        //need af fake database to this.
    }

    @org.junit.jupiter.api.Test
    void partiEachTeam() {
//  String antal = rs.getString("antal");
//  String teamId = rs.getString("team_id");
//  finalString = teamId + " has " + antal + " participants";
        List<String> testList = memberMapper.partiEachTeam();

        String expected = "yo02" + " has " + "5" + " participants"; //første plads i det sammensatte board.
        String acutal = testList.get(0);
        assertEquals(expected,acutal);

    }

    @org.junit.jupiter.api.Test
    void partiEachSport() {
    }

    @org.junit.jupiter.api.Test
    void amountByGender() {
    }

    @org.junit.jupiter.api.Test
    void totalIncome() {
    }

    @org.junit.jupiter.api.Test
    void totalIncomeForEachTeam() {
    }

    @org.junit.jupiter.api.Test
    void avgIncomeForEachTeam() {
    }
}