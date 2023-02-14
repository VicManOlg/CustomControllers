package model;

/**
 *
 * @author Victor
 */
public class PositionDTO {
    private int posID;
    private String posName;
    private String leters;

    public PositionDTO(int posID, String posName, String leters) {
        this.posID = posID;
        this.posName = posName;
        this.leters = leters;
    }

    @Override
    public String toString() {
        return  posName;
    }

    public PositionDTO() {
    }

    public int getPosID() {
        return posID;
    }

    public void setPosID(int posID) {
        this.posID = posID;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getLeters() {
        return leters;
    }

    public void setLeters(String leters) {
        this.leters = leters;
    }
    
}
