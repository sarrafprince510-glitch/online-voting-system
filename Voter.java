
public class Voter extends User {


    private String voterId;      
    private boolean hasVoted;    
    public Voter(String voterId, String name, int age) {
        
        super(name, age);
        this.voterId = voterId;
        this.hasVoted = false; 
    }

    public String getVoterId() {
        return voterId;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void markAsVoted() {
        this.hasVoted = true;
    }

    
    @Override
    public void displayInfo() {
        System.out.println("Voter ID: " + voterId
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Voted: " + (hasVoted ? "Yes" : "No"));
    }
}
