


public class Candidate {

    private int candidateId;
    private String name;
    private String party;
    private int voteCount;

    public Candidate(int candidateId, String name, String party) {
        this.candidateId = candidateId;
        this.name = name;
        this.party = party;
        this.voteCount = 0; 
    }

    public int getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVote() {
        this.voteCount++;
    }

    
    @Override
    public String toString() {
        return candidateId + ". " + name + " (" + party + ") - Votes: " + voteCount;
    }
}
