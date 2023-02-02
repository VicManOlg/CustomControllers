package model;

import java.io.IOException;


/**
 *
 * @author Victor
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Repository.login("afer", "123");
        Repository.GetTeamsByCategory(1, "Pimer");
        Repository.GetClub(1);
        Repository.GetScoutReviews(2);
        Repository r = new Repository();
        PlayerDTO player = new PlayerDTO(0, "Ronald Federico", "Araujo Da Silva", "1999-03-07", "Uruguaya", 188.0, 79.0, 60000.0, ".....", "65546056", "6634243", "Barcelona", "96878969"); 
        r.addPlayer(player, 1);
        /*
        Repository.GetPlayersByTeam(1);
        TeamDTO tdto = new TeamDTO();
        tdto.setCatID(1);
        tdto.setPicture("https://images.emojiterra.com/twitter/v13.1/512px/2753.png");
        tdto.setTeamName("Paris Saint Germain");
        tdto.setTeamID(0);
        Repository r = new Repository();
        r.sendPOST(tdto, 1);
*/
    }
}

