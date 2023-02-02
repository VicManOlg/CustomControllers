package model;

/**
 *
 * @author Victor
 */
public class GameDTO {
    private int gameId;
    private String gameDate;
    private TeamDTO visitor;
    private TeamDTO team;

    @Override
    public String toString() {
        return "GameDTO{" + "gameId=" + gameId + ", gameDate=" + gameDate + ", visitor=" + visitor + ", team=" + team + '}';
    }

    public GameDTO() {
    }

    public GameDTO(int gameId, String gameDate, TeamDTO visitor, TeamDTO team) {
        this.gameId = gameId;
        this.gameDate = gameDate;
        this.visitor = visitor;
        this.team = team;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public TeamDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(TeamDTO visitor) {
        this.visitor = visitor;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
    
}
