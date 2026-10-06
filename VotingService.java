
import java.util.ArrayList;


 
public class VotingService {

    private ArrayList<Voter> voters;
    private ArrayList<Candidate> candidates;

    public VotingService() {
        voters = new ArrayList<>();
        candidates = new ArrayList<>();
        loadCandidates(); 
    }

    private void loadCandidates() {
        candidates.add(new Candidate(1, "Aditi Rao", "Green Party"));
        candidates.add(new Candidate(2, "Rohan Mehta", "Unity Party"));
        candidates.add(new Candidate(3, "Sara Khan", "Progress Party"));
    }

    
    public String registerVoter(String voterId, String name, int age) {
        if (age < 18) {
            return "REJECTED: Voter must be 18 or older to register.";
        }
        if (findVoterById(voterId) != null) {
            return "REJECTED: Voter ID already registered.";
        }
        voters.add(new Voter(voterId, name, age));
        return "SUCCESS: Voter registered.";
    }

    private Voter findVoterById(String voterId) {
        for (Voter v : voters) {
            if (v.getVoterId().equalsIgnoreCase(voterId)) {
                return v;
            }
        }
        return null; 
    }

    private Candidate findCandidateById(int candidateId) {
        for (Candidate c : candidates) {
            if (c.getCandidateId() == candidateId) {
                return c;
            }
        }
        return null;
    }

    public String castVote(String voterId, int candidateId) {
        Voter voter = findVoterById(voterId);
        if (voter == null) {
            return "REJECTED: No such registered voter. Please register first.";
        }
        if (voter.hasVoted()) {
            return "REJECTED: This voter has already voted. Duplicate voting is not allowed.";
        }
        Candidate candidate = findCandidateById(candidateId);
        if (candidate == null) {
            return "REJECTED: No such candidate.";
        }
        candidate.incrementVote();
        voter.markAsVoted();
        return "SUCCESS: Vote cast for " + candidate.getName() + ".";
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public ArrayList<Voter> getVoters() {
        return voters;
    }

    
    public void showResults() {
        ArrayList<Candidate> sorted = new ArrayList<>(candidates);
        sorted.sort((a, b) -> b.getVoteCount() - a.getVoteCount());

        System.out.println("\n===== VOTING RESULTS =====");
        for (Candidate c : sorted) {
            System.out.println(c);
        }
        if (!sorted.isEmpty()) {
            System.out.println("\nLeading: " + sorted.get(0).getName());
        }
    }
}
