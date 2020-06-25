
import java.util.Objects;

/**
 * User Statistics
 *
 * @author Victor Schneuwly
 */
class UserStatistics {
    private int wins, losses, totalAnnounce;
    public UserStatistics(int wins, int losses, int totalAnnounce) {
        this.wins = wins;
        this.losses = losses;
        this.totalAnnounce = totalAnnounce;
    }

    public int getWins() {
        return wins;
    }

    void addWin() {
        ++wins;
    }

    public int getLosses() {
        return losses;
    }

    void addLosses() {
        ++losses;
    }

    public int getTotalGamesPlayed() {
        return wins + losses;
    }

    public double getWinPercentage() {
        return 100d * wins / getTotalGamesPlayed();
    }

    public int getTotalAnnounce() {
        return totalAnnounce;
    }

    public double getAnnouncePerGame() {
        return totalAnnounce * 1d / getTotalGamesPlayed();
    }

    void addAnnounce(int announce) {
        totalAnnounce += announce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatistics userStatistics = (UserStatistics) o;
        return wins == userStatistics.wins &&
                losses == userStatistics.losses &&
                totalAnnounce == userStatistics.totalAnnounce;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, losses, totalAnnounce);
    }

    @Override
    public String toString() {
        return "Statistics { " +
                "P = " + getTotalGamesPlayed() +
                ", V = " + wins +
                ", D = " + losses +
                ", V% = " + getWinPercentage() + '%' +
                ", TA = " + totalAnnounce +
                ", AP = " + getAnnouncePerGame() +
                " }";
    }
}
