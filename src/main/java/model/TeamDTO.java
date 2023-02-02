package model;

/**
 *
 * @author Victor
 */
public class TeamDTO {
    private int teamID;
    private String teamName;
    private int catID;
    private String picture;

    public TeamDTO(int teamID, String teamName, int catID, String picture) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.catID = catID;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

 
    public TeamDTO() {
    }
    
    
    public int getTeamID() {
        return teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getCatID() {
        return catID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    @Override
    public String toString() {
        return "TeamDTO{" + "teamID=" + teamID + ", teamName=" + teamName + ", catID=" + catID + ", picture=" + picture + '}';
    }

    
    
}
