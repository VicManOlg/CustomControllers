package model;


public class ScoutDTO {
    
    private int scoutID;
    private String scoutName;
    private String scoutSurname;
    private String scoutMail;
    private String scoutUser;
    private String scoutPass;
    private String rol;
    private int clubId;

    public ScoutDTO(int scoutID, String scoutName, String scoutSurname, String scoutMail, String scoutUser, String scoutPass, String rol) {
        this.scoutID = scoutID;
        this.scoutName = scoutName;
        this.scoutSurname = scoutSurname;
        this.scoutMail = scoutMail;
        this.scoutUser = scoutUser;
        this.scoutPass = scoutPass;
        this.rol = rol;
    }

    public ScoutDTO() {
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getScoutID() {
        return scoutID;
    }

    public String getScoutName() {
        return scoutName;
    }

    public String getScoutSurname() {
        return scoutSurname;
    }

    public String getScoutMail() {
        return scoutMail;
    }

    public String getScoutUser() {
        return scoutUser;
    }

    public String getScoutPass() {
        return scoutPass;
    }

    public String getRol() {
        return rol;
    }

    public void setScoutID(int scoutID) {
        this.scoutID = scoutID;
    }

    public void setScoutName(String scoutName) {
        this.scoutName = scoutName;
    }

    public void setScoutSurname(String scoutSurname) {
        this.scoutSurname = scoutSurname;
    }

    public void setScoutMail(String scoutMail) {
        this.scoutMail = scoutMail;
    }

    public void setScoutUser(String scoutUser) {
        this.scoutUser = scoutUser;
    }

    public void setScoutPass(String scoutPass) {
        this.scoutPass = scoutPass;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "ScoutDTO{" + "scoutID=" + scoutID + ", scoutName=" + scoutName + ", scoutSurname=" + scoutSurname + ", scoutMail=" + scoutMail + ", scoutUser=" + scoutUser + ", scoutPass=" + scoutPass + ", rol=" + rol + ", clubId=" + clubId + '}';
    }

    
    
}
