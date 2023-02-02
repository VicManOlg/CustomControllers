
package model;

/**
 *
 * @author Victor
 */
public class ClubDTO {
    private int clubId;
    private String clubName;
    private String clubPhoto;

    public ClubDTO(int clubId, String clubName, String clubPhoto) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubPhoto = clubPhoto;
    }

    public ClubDTO() {
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubPhoto() {
        return clubPhoto;
    }

    public void setClubPhoto(String clubPhoto) {
        this.clubPhoto = clubPhoto;
    }

    @Override
    public String toString() {
        return "ClubDTO{" + "clubId=" + clubId + ", clubName=" + clubName + ", clubPhoto=" + clubPhoto + '}';
    }
    
}
