package model;

import java.io.IOException;


/**
 *
 * @author Victor
 */
public class Test {
    public static void main(String[] args) throws IOException {
   
        //r.addPlayer(player, 1);
        Repository.GetGames(1);
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

