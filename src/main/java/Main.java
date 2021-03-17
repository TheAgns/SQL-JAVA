import entities.Member;
import persistence.Database;
import persistence.MemberMapper;

import java.util.List;

public class Main {

    private final static String USER = "root";
    private final static String PASSWORD = "Vinter2020";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub?serverTimezone=CET&useSSL=false";

    public static void main(String[] args) {

        Database db = new Database(USER, PASSWORD, URL);
        MemberMapper memberMapper = new MemberMapper(db);
        List<Member> members = memberMapper.getAllMembers();

        showMembers(members);
        showMemberById(memberMapper, 13);
        int newMemberId = insertMember(memberMapper);
        deleteMember(newMemberId, memberMapper);
        showMembers(members);
        updateMember(13, memberMapper);
        showParticipants(memberMapper.partiEachTeam());
        showParticipantsEachSport(memberMapper.partiEachSport());
        showGenderAmount(memberMapper.amountByGender());
        showIncomeAmount(memberMapper.totalIncome());
        showIncomeEachTeam(memberMapper.totalIncomeForEachTeam());
        showAvgIncomeForAllTeams(memberMapper.avgIncomeForEachTeam());
    }

    private static void deleteMember(int memberId, MemberMapper memberMapper) {
        if (memberMapper.deleteMember(memberId)) {
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) {
        Member m1 = new Member("Ole Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if (memberMapper.updateMember(m1)) {
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId) {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

    private static void showParticipants(List<String> participants) {
        System.out.println("\n" + "***** Antal medlemmer på hvert hold *****");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
    private static void showParticipantsEachSport(List<String> participants) {
        System.out.println("\n" + "***** Antal medlemmer for hver sport *****");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
    private static void showGenderAmount(List<String> participants) {
        System.out.println("\n" + "***** Antal af forskellige køn *****");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
    private static void showIncomeAmount(int income) {
        System.out.println("\n" + "***** Total income *****");
        System.out.println(income);
        }
    private static void showIncomeEachTeam(List<String> participants) {
        System.out.println("\n" + "***** Income for hvert hold *****");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
    private static void showAvgIncomeForAllTeams(List<String> participants) {
        System.out.println("\n" + "***** AVG Income for hvert hold *****");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
    }

