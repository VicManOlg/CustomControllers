package model;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class PlayerDTO {
    private int playerID;
    private String playerName;
    private String playerSurname;
    private String playerBirth;
    private String nacionality;
    private double heigth;
    private double weight;
    private double price;
    private String contract;
    private String contact;
    private String agent;
    private String contactAgent;
    private String contactFamily;
    private String photo;

    public PlayerDTO() {
    }

    public PlayerDTO(int playerID, String nacionality, double heigth, double weight, double price, String contact, String agent, String contactAgent, String contactFamily) {
        this.playerID = playerID;
        this.nacionality = nacionality;
        this.heigth = heigth;
        this.weight = weight;
        this.price = price;
        this.contact = contact;
        this.agent = agent;
        this.contactAgent = contactAgent;
        this.contactFamily = contactFamily;
    }
    
    public PlayerDTO(int playerID, String nacionality, double heigth, double weight, double price, String contact, String agent, String contactAgent, String contactFamily, String photo) {
        this.playerID = playerID;
        this.nacionality = nacionality;
        this.heigth = heigth;
        this.weight = weight;
        this.price = price;
        this.contact = contact;
        this.agent = agent;
        this.contactAgent = contactAgent;
        this.contactFamily = contactFamily;
        this.photo = photo;
    }
    
    public PlayerDTO(int playerID, String playerName, String playerSurname, String playerBirth, String nacionality, double heigth, double weight, double price, String contract, String contact, String agent, String contactAgent, String contactFamily) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirth = playerBirth;
        this.nacionality = nacionality;
        this.heigth = heigth;
        this.weight = weight;
        this.price = price;
        this.contract = contract;
        this.contact = contact;
        this.agent = agent;
        this.contactAgent = contactAgent;
        this.contactFamily = contactFamily;
    }

    public PlayerDTO(int playerID, String playerName, String playerSurname, String playerBirth, String nacionality, double heigth, double weight, double price, String contract, String contact, String agent, String contactAgent, String contactFamily, String photo) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirth = playerBirth;
        this.nacionality = nacionality;
        this.heigth = heigth;
        this.weight = weight;
        this.price = price;
        this.contract = contract;
        this.contact = contact;
        this.agent = agent;
        this.contactAgent = contactAgent;
        this.contactFamily = contactFamily;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public void setPlayerSurname(String playerSurname) {
        this.playerSurname = playerSurname;
    }

    public String getPlayerBirth() {
        return playerBirth;
    }

    public void setPlayerBirth(String playerBirth) {
        this.playerBirth = playerBirth;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getContactAgent() {
        return contactAgent;
    }

    public void setContactAgent(String contactAgent) {
        this.contactAgent = contactAgent;
    }

    public String getContactFamily() {
        return contactFamily;
    }

    public void setContactFamily(String contactFamily) {
        this.contactFamily = contactFamily;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" + "playerID=" + playerID + ", playerName=" + playerName + ", playerSurname=" + playerSurname + ", playerBirth=" + playerBirth + ", nacionality=" + nacionality + ", heigth=" + heigth + ", weight=" + weight + ", price=" + price + ", contract=" + contract + ", contact=" + contact + ", agent=" + agent + ", contactAgent=" + contactAgent + ", contactFamily=" + contactFamily + ", photo=" + photo + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public boolean equals(ArrayList<PlayerDTO> players) {
        for(PlayerDTO pDTO : players){
            if(this.playerID == pDTO.getPlayerID()){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
}
