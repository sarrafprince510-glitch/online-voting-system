public class TestRunner {
    static int pass = 0, total = 0;

    static void check(String id, String desc, boolean ok) {
        total++;
        if (ok) pass++;
        System.out.println(id + " | " + desc + " | " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] a) {
        VotingService s = new VotingService();
        check("TC01", "Register valid voter (age 20)", s.registerVoter("V001", "Arun", 20).startsWith("SUCCESS"));
        check("TC02", "Reject under-age voter (age 17)", s.registerVoter("V002", "Kiran", 17).startsWith("REJECTED"));
        check("TC03", "Accept boundary age 18", s.registerVoter("V003", "Meena", 18).startsWith("SUCCESS"));
        check("TC04", "Reject duplicate voter ID", s.registerVoter("V001", "Other", 25).startsWith("REJECTED"));
        check("TC05", "Reject duplicate ID (case-insensitive)", s.registerVoter("v001", "Other", 25).startsWith("REJECTED"));
        check("TC06", "Valid vote is accepted", s.castVote("V001", 1).startsWith("SUCCESS"));
        check("TC07", "Vote count incremented", s.getCandidates().get(0).getVoteCount() == 1);
        check("TC08", "Voter marked as voted", s.getVoters().get(0).hasVoted());
        check("TC09", "Reject duplicate vote", s.castVote("V001", 2).startsWith("REJECTED"));
        check("TC10", "Reject unregistered voter", s.castVote("V999", 1).startsWith("REJECTED"));
        check("TC11", "Reject invalid candidate ID", s.castVote("V003", 99).startsWith("REJECTED"));
        check("TC12", "Failed vote does not mark voter", !s.getVoters().get(1).hasVoted());
        check("TC13", "Vote with lowercase voter ID", s.castVote("v003", 3).startsWith("SUCCESS"));
        check("TC14", "Three candidates loaded", s.getCandidates().size() == 3);
        check("TC15", "Total votes equals votes cast", s.getCandidates().stream().mapToInt(Candidate::getVoteCount).sum() == 2);
        check("TC16", "Negative age rejected", s.registerVoter("V004", "Neg", -5).startsWith("REJECTED"));
        check("TC17", "Candidate 0 rejected", s.castVote("V003", 0).startsWith("REJECTED"));
        check("TC18", "Polymorphic displayInfo() runs via User ref", run());
        System.out.println("RESULT: " + pass + "/" + total + " passed = " + (100.0 * pass / total) + "%");
    }

    static boolean run() {
        try {
            User u = new Voter("V100", "Test", 30);
            u.displayInfo();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
