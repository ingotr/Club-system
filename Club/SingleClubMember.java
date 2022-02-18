package Club;

public class SingleClubMember extends Member {
    private int club;

    public SingleClubMember(char memberType, int memberID, String name, double fees, int pClub) {
        super(memberType, memberID, name, fees);
        this.setClub(pClub);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.club;
    }

    public int getClub() {
        return this.club;
    }

    public void setClub(int pClub) {
        this.club = pClub;
    }
}
