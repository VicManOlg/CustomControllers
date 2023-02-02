package model;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class ReviewDTO {
        private int reviewId; 
        private String comments; 
        private String date; 
        private ScoutDTO scout; 
        private PlayerDTO player; 
        private int positionId; 
        private String level; 
        private int technical; 
        private int tactical; 
        private int phisic; 
        private int mental; 
        private int intelligence; 
        private String playerName;

    public ReviewDTO() {
    }

    public ReviewDTO(int reviewId, String comments, String date, ScoutDTO scout, PlayerDTO player, int positionId, String level, int technical, int tactical, int phisic, int mental, int intelligence, String playerName) {
        this.reviewId = reviewId;
        this.comments = comments;
        this.date = date;
        this.scout = scout;
        this.player = player;
        this.positionId = positionId;
        this.level = level;
        this.technical = technical;
        this.tactical = tactical;
        this.phisic = phisic;
        this.mental = mental;
        this.intelligence = intelligence;
        this.playerName = playerName;
    }

    public ReviewDTO(int reviewId, String comments, String date, ScoutDTO scout, PlayerDTO player, int positionId, String level, int technical, int tactical, int phisic, int mental, int intelligence) {
        this.reviewId = reviewId;
        this.comments = comments;
        this.date = date;
        this.scout = scout;
        this.player = player;
        this.positionId = positionId;
        this.level = level;
        this.technical = technical;
        this.tactical = tactical;
        this.phisic = phisic;
        this.mental = mental;
        this.intelligence = intelligence;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ScoutDTO getScout() {
        return scout;
    }

    public void setScout(ScoutDTO scout) {
        this.scout = scout;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getTechnical() {
        return technical;
    }

    public void setTechnical(int technical) {
        this.technical = technical;
    }

    public int getTactical() {
        return tactical;
    }

    public void setTactical(int tactical) {
        this.tactical = tactical;
    }

    public int getPhisic() {
        return phisic;
    }

    public void setPhisic(int phisic) {
        this.phisic = phisic;
    }

    public int getMental() {
        return mental;
    }

    public void setMental(int mental) {
        this.mental = mental;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" + "reviewId=" + reviewId + ", comments=" + comments + ", date=" + date + ", scout=" + scout + ", player=" + player + ", positionId=" + positionId + ", level=" + level + ", technical=" + technical + ", tactical=" + tactical + ", phisic=" + phisic + ", mental=" + mental + ", intelligence=" + intelligence + ", playerName=" + playerName + '}';
    }

    
        
        
}
