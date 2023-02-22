package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author Victor
 */
public class Repository {
    public static ScoutDTO  login(String name, String pass) {
        ScoutDTO scout = new ScoutDTO();
        try {
            URL url = new URL("https://iotscouting.com/api/login/" + name + "/" + pass);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                //JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                JSONObject countryData = (JSONObject) parse.parse(String.valueOf(informationString));               
                scout.setScoutID(Integer.parseInt(countryData.get("ScoutId").toString()));
                scout.setClubId(Integer.parseInt(countryData.get("ClubId").toString()));
                scout.setScoutName(countryData.get("ScoutName").toString());
                scout.setScoutSurname(countryData.get("ScoutSurname").toString());
                scout.setScoutMail(countryData.get("ScoutMail").toString());
                scout.setScoutUser(countryData.get("ScoutUser").toString());
                scout.setScoutPass(countryData.get("ScoutPass").toString());
                scout.setRol(countryData.get("Rol").toString());
                System.out.println(scout);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return scout;
    }
    public static ArrayList<TeamDTO> GetTeamsByCategory(int id, String cat) {  
        ArrayList<TeamDTO> teams = new ArrayList<TeamDTO>();
        try {
            cat = cat.replaceAll(" ", "%20");
            URL url = new URL("https://iotscouting.com/api/teams/category/" + id+"/"+cat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    TeamDTO team = new TeamDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    team.setTeamID(Integer.parseInt(countryData.get("teamId").toString()));
                    team.setTeamName(countryData.get("teamName").toString());
                    team.setPicture(countryData.get("picture").toString());
                    teams.add(team);
                    System.out.println(team);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return teams;
    }
    
    public static ArrayList<PlayerDTO> GetPlayersByTeam(int id) {  
        ArrayList<PlayerDTO> players = new ArrayList<PlayerDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/players/team/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1;");
            conn.setRequestProperty("Accept", "application/json;charset=ISO-8859-1;");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    PlayerDTO player = new PlayerDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    player.setPlayerID(Integer.parseInt(countryData.get("PlayerId").toString()));
                    player.setPlayerName(countryData.get("PlayerName").toString());
                    player.setPlayerSurname(countryData.get("PlayerSurname").toString());
                    player.setPlayerBirth(countryData.get("BirthDate").toString());
                    player.setNacionality(countryData.get("Nacionality").toString());
                    player.setHeigth(Double.parseDouble(countryData.get("Heigth").toString()));
                    player.setWeight(Double.parseDouble(countryData.get("Weight").toString()));
                    player.setPrice(Double.parseDouble(countryData.get("Price").toString()));
                    player.setContract(countryData.get("Contract").toString());
                    player.setAgent(countryData.get("Agent").toString());
                    player.setContactAgent(countryData.get("ContactAgent").toString());
                    player.setContact(countryData.get("Contact").toString());
                    player.setContactFamily(countryData.get("ContactFamily").toString());
                    players.add(player);
                    System.out.println(player);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return players;
    }
    public static ArrayList<TeamDTO> GetTeamsByCategoryName(int id, String name, String cat) {  
        ArrayList<TeamDTO> teams = new ArrayList<TeamDTO>();
        try {
            cat = cat.replaceAll(" ", "%20");
            URL url = new URL("https://iotscouting.com/api/teams/category/" + id+"/"+name+"/"+cat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    TeamDTO team = new TeamDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    team.setTeamID(Integer.parseInt(countryData.get("teamId").toString()));
                    team.setTeamName(countryData.get("teamName").toString());
                    team.setPicture(countryData.get("picture").toString());
                    teams.add(team);
                    System.out.println(team);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return teams;
    }
    public static ArrayList<CategoryDTO> GetCategories(int id) {  
        ArrayList<CategoryDTO> cats = new ArrayList<CategoryDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/categorys/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    CategoryDTO cat = new CategoryDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    cat.setCatId(Integer.parseInt(countryData.get("categoryId").toString()));
                    cat.setCatName(countryData.get("categoryName").toString());
                    System.out.println(cat);
                    cats.add(cat);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cats;
    }
    private final OkHttpClient httpClient = new OkHttpClient();
    public boolean sendPOST(TeamDTO teamdto, int clubID) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"TeamID\":"+0+",")
                .append("\"TeamName\":\""+teamdto.getTeamName()+"\",")
                .append("\"CategoryID\":\""+teamdto.getCatID()+"\",")
                .append("\"ClubID\":\""+clubID+"\",")
                .append("\"Picture\":\""+teamdto.getPicture()+"\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/post/team")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
    
    public boolean addPlayer(PlayerDTO player, int clubID) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"PlayerID\":"+0+",")
                .append("\"PlayerName\":\""+player.getPlayerName()+"\",")
                .append("\"PlayerSurname\":\""+player.getPlayerSurname()+"\",")
                .append("\"BirthDate\":\""+player.getPlayerBirth()+"\",")
                .append("\"ClubID\":\""+clubID+"\",")
                .append("\"Nationality\":\""+player.getNacionality()+"\",")
                .append("\"Height\":\""+player.getHeigth()+"\",")
                .append("\"Price\":\""+player.getPrice()+"\",")
                .append("\"PContract\":\""+player.getContract()+"\",")
                .append("\"PWeight\":\""+player.getWeight()+"\",")
                .append("\"Contact\":\""+player.getContact()+"\",")
                .append("\"ContactAgent\":\""+player.getContactAgent()+"\",")
                .append("\"Agent\":\""+player.getAgent()+"\",")
                .append("\"ContactFamily\":\""+player.getContactFamily()+"\",")
                .append("\"Photo\":\""+player.getPhoto()+"\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/post/player")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
    
    public static ClubDTO  GetClub(int id) {
        ClubDTO club = new ClubDTO();
        try {
            URL url = new URL("https://iotscouting.com/api/club/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                //JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                JSONObject countryData = (JSONObject) parse.parse(String.valueOf(informationString));               
                club.setClubId(Integer.parseInt(countryData.get("clubID").toString()));
                club.setClubPhoto(countryData.get("clubPhoto").toString());
                club.setClubName(countryData.get("clubName").toString());
                System.out.println(club);
            }
        } catch (IOException | RuntimeException | ParseException e) {
            return null;
        }
        return club;
    }
    
    public static  ArrayList<GameDTO>  GetGamesByTeam(int id) {
        ArrayList<GameDTO> games = new ArrayList<GameDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/games/team/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    GameDTO game = new GameDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    game.setGameId(Integer.parseInt(countryData.get("Id").toString()));
                    game.setGameDate(countryData.get("date").toString());
                    game.setVisitor(new TeamDTO());
                    System.out.println(game);
                    games.add(game);
                }   
            }
        } catch (IOException | RuntimeException | ParseException e) {
            return null;
        }
        return games;
    }
    public static PlayerDTO  GetPlayer(int id) {
        PlayerDTO player = new PlayerDTO();
        try {
            URL url = new URL("https://iotscouting.com/api/player/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                //JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                JSONObject countryData = (JSONObject) parse.parse(String.valueOf(informationString)); 
                player.setPlayerID(Integer.parseInt(countryData.get("PlayerId").toString()));
                player.setPlayerName(countryData.get("PlayerName").toString());
                player.setPlayerSurname(countryData.get("PlayerSurname").toString());
                player.setPlayerBirth(countryData.get("BirthDate").toString());
                player.setNacionality(countryData.get("Nacionality").toString());
                player.setHeigth(Double.parseDouble(countryData.get("Heigth").toString()));
                player.setWeight(Double.parseDouble(countryData.get("Weight").toString()));
                player.setPrice(Double.parseDouble(countryData.get("Price").toString()));
                player.setContract(countryData.get("Contract").toString());
                player.setAgent(countryData.get("Agent").toString());
                player.setContactAgent(countryData.get("ContactAgent").toString());
                player.setContact(countryData.get("Contact").toString());
                player.setContactFamily(countryData.get("ContactFamily").toString());
                player.setPhoto(countryData.get("Photo").toString());
                //club.setClubPhoto(countryData.get("clubPhoto").toString());
                System.out.println(player);
            }
        } catch (IOException | RuntimeException | ParseException e) {
            return null;
        }
        return player;
    }
    
    public static  ArrayList<TeamDTO>  GetPlayerTeams(int id) {
        ArrayList<TeamDTO> teams = new ArrayList<TeamDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/player/teams/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    TeamDTO team = new TeamDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    team.setTeamID(Integer.parseInt(countryData.get("teamId").toString()));
                    team.setTeamName(countryData.get("teamName").toString());
                    team.setPicture(countryData.get("picture").toString());
                    System.out.println(team);
                    teams.add(team);
                }   
            }
        } catch (IOException | RuntimeException | ParseException e) {
            return null;
        }
        return teams;
    }
    
    public static  ArrayList<PositionDTO>  GetPlayerPositions(int id) {
        ArrayList<PositionDTO> positions = new ArrayList<PositionDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/player/position/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    PositionDTO position = new PositionDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    position.setPosID(Integer.parseInt(countryData.get("id").toString()));
                    position.setPosName(countryData.get("name").toString());
                    position.setLeters(countryData.get("leters").toString());
                    System.out.println(position);
                    positions.add(position);
                }   
            }
        } catch (IOException | RuntimeException | ParseException e) {
            return null;
        }
        return positions;
    }
    public static  ArrayList<ReviewDTO>  GetScoutReviews(int id) {
        ArrayList<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/scout/reviews/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    ReviewDTO review = new ReviewDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    review.setReviewId(Integer.parseInt(countryData.get("reviewId").toString()));
                    review.setComments(countryData.get("comments").toString());
                    review.setDate(countryData.get("date").toString());
                    //review.setScout((ScoutDTO) countryData.get("scout")); //da problemas por diferencia de nombre entre api y app
                    PlayerDTO player = new PlayerDTO();
                    JSONObject recs = (JSONObject) countryData.get("player");
                    player.setPlayerID(Integer.parseInt(recs.get("PlayerId").toString()));
                    
                    player.setPlayerName(recs.get("PlayerName").toString());
                    
                    player.setPlayerSurname(recs.get("PlayerSurname").toString());
                    player.setPlayerBirth(recs.get("BirthDate").toString());
                    player.setNacionality(recs.get("Nacionality").toString());
                    player.setHeigth(Double.parseDouble(recs.get("Heigth").toString()));
                    player.setWeight(Double.parseDouble(recs.get("Weight").toString()));
                    player.setPrice(Double.parseDouble(recs.get("Price").toString()));
                    player.setContract(recs.get("Contract").toString());
                    player.setAgent(recs.get("Agent").toString());
                    player.setContactAgent(recs.get("ContactAgent").toString());
                    player.setContact(recs.get("Contact").toString());
                    player.setContactFamily(recs.get("ContactFamily").toString());
                    review.setPlayerName(player.getPlayerName() + " " + player.getPlayerSurname() );
                    review.setPlayer(player); // puede dar problemas por diferencia de nombre entre api y app
                    review.setPositionId(Integer.parseInt(countryData.get("positionId").toString()));
                    review.setLevel(countryData.get("level").toString());
                    review.setTechnical(Integer.parseInt(countryData.get("technical").toString()));
                    review.setTactical(Integer.parseInt(countryData.get("tactical").toString()));
                    review.setPhisic(Integer.parseInt(countryData.get("phisic").toString()));
                    review.setMental(Integer.parseInt(countryData.get("mental").toString()));
                    review.setIntelligence(Integer.parseInt(countryData.get("intelligence").toString()));
                    System.out.println(review);
                    reviews.add(review);
                }   
            }
        } catch (IOException | RuntimeException | ParseException e) {
            System.out.println(e);
            return null;
        }
        return reviews;
    }
    public static ArrayList<PlayerDTO> GetPlayers(int id) {  
        ArrayList<PlayerDTO> players = new ArrayList<PlayerDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/players/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1;");
            conn.setRequestProperty("Accept", "application/json;charset=ISO-8859-1;");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    PlayerDTO player = new PlayerDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    player.setPlayerID(Integer.parseInt(countryData.get("PlayerId").toString()));
                    player.setPlayerName(countryData.get("PlayerName").toString());
                    player.setPlayerSurname(countryData.get("PlayerSurname").toString());
                    player.setPlayerBirth(countryData.get("BirthDate").toString());
                    player.setNacionality(countryData.get("Nacionality").toString());
                    player.setHeigth(Double.parseDouble(countryData.get("Heigth").toString()));
                    player.setWeight(Double.parseDouble(countryData.get("Weight").toString()));
                    player.setPrice(Double.parseDouble(countryData.get("Price").toString()));
                    player.setContract(countryData.get("Contract").toString());
                    player.setAgent(countryData.get("Agent").toString());
                    player.setContactAgent(countryData.get("ContactAgent").toString());
                    player.setContact(countryData.get("Contact").toString());
                    player.setContactFamily(countryData.get("ContactFamily").toString());
                    players.add(player);
                    System.out.println(player);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return players;
    }
    public boolean addTeamSquad(PlayerDTO player, int teamId) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"PlayerID\":"+player.getPlayerID()+",")
                .append("\"TeamID\":\""+teamId+"\",")
                .append("\"DateStart\":\""+player.getPlayerSurname()+"\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/post/squad")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
    public boolean updateTeam(TeamDTO teamdto, int clubID) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"TeamID\":"+teamdto.getTeamID()+",")
                .append("\"TeamName\":\""+teamdto.getTeamName()+"\",")
                .append("\"CategoryID\":\""+teamdto.getCatID()+"\",")
                .append("\"ClubID\":\""+clubID+"\",")
                .append("\"Picture\":\""+teamdto.getPicture()+"\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/put/team")
                .addHeader("User-Agent", "OkHttp Bot")
                .put(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
    public static boolean RemovePlayerFromTeam(int idTeam, int idPlayer) {  
        try {
            URL url = new URL("https://iotscouting.com/api/delete/teamsquad/" + idTeam + "/" + idPlayer);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1;");
            conn.setRequestProperty("Accept", "application/json;charset=ISO-8859-1;");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();            
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
            return true;
        }
    public static ArrayList<PositionDTO> GetPositions(int idClub) {  
        ArrayList<PositionDTO> positions = new ArrayList<PositionDTO>();
        try {
            URL url = new URL("https://iotscouting.com/api/positions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1;");
            conn.setRequestProperty("Accept", "application/json;charset=ISO-8859-1;");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), "utf-8");
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                for(int i = 0; i < dataObject.size(); i++ ){
                    PositionDTO position = new PositionDTO();
                    JSONObject countryData = (JSONObject) dataObject.get(i);
                    position.setPosID(Integer.parseInt(countryData.get("id").toString()));  
                    position.setPosName(countryData.get("name").toString());
                    position.setLeters(countryData.get("leters").toString());
                    positions.add(position);
                    System.out.println(position);
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return positions;
    }
    public boolean addPlayerPosition(int idPlayer, int idPos, int firstPos) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{").append("\"PlayerID\":")
                .append(idPlayer)
                .append(",")
                .append("\"PositionID\":\"")
                .append(idPos)
                .append("\",")
                .append("\"FirstPosition\":\"")
                .append(firstPos)
                .append("\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/post/playerposition")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
    public boolean addCategory(CategoryDTO cat, int clubId) throws IOException {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"CategoryID\":"+0+",")
                .append("\"CategoryName\":\""+cat.getCatName()+"\",")
                .append("\"idClub\":\""+clubId+"\",")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://iotscouting.com/api/post/category")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
            return true;
        }
    }
  
}
