package generator;

import java.util.Random;

/**
 *
 * @author Esben
 */
public class Generator {
    //Tak til https://www.generatedata.com/ for inholdet i hardcoded fornavne og efternavne
    String[] fNames = {"Reed","Juliet","India","Carla","Elton","Steel","Aladdin","Sean","Sarah","Ashton","Bertha","Bevis","Lawrence","Yetta","Samson","Griffith","Meghan","Karen","Brady","Serena","Ferdinand","Mariam","Stephanie","Noel","Trevor","Christen","Nathan","Chester","Adria","Jemima","Linus","Elmo","Jeanette","Kay","Lee","Daniel","Theodore","Ariel","Kelsey","Hall","Kristen","Castor","Alma","MacKensie","Garth","Ahmed","Dalton","Illana","Dustin","Jin","Brady","Virginia","Wang","Cathleen","Sara","Jacob","Mufutau","Danielle","Emma","Emerald","Baker","Chelsea","Russell","Ray","Teegan","Kevyn","Carla","Troy","Evan","Alice","Lareina","Olivia","Clare","Fuller","Uma","Darrel","Georgia","Cassady","Kameko","Jordan","Amanda","Xenos","Elliott","Amber","Brandon","Beck","Chester","Kirestin","Rafael","Keane","Myles","Chase","Dorothy","Althea","Jenette","Melissa","Guy","Noble","Kelly","Amery"};
    String[] lNames = {"Benton","Leblanc","Mcclain","Williams","Swanson","Foster","Glenn","Moreno","Paul","Lynch","Kirby","Valentine","Bush","Fulton","Snow","Lyons","Stafford","Steele","Harding","Atkinson","Hanson","Reyes","Slater","Anderson","Stark","Bridges","Mercer","Shepard","Johnston","Swanson","Goff","King","Greer","Horne","Dillon","Goff","Rowe","Mendoza","Savage","Leonard","Francis","Carson","Dudley","Nixon","Cantu","Langley","Wilcox","Fischer","Riley","Lynn","Vaughan","Knapp","Humphrey","Nixon","Cardenas","Conrad","Glenn","Carrillo","Mccarthy","Ramsey","Bond","James","Mccormick","Carroll","Burt","Pugh","Mcdonald","Massey","Ross","Little","Ashley","Holmes","Pope","Vaughan","Guthrie","Bean","Meyers","Hines","Hinton","Klein","Marshall","Ross","Farley","Finch","Henderson","Walls","Beard","Hoffman","Hill","Fuentes","Mclaughlin","Stone","Herrera","Booker","Baldwin","Navarro","Franks","Byers","Baldwin","Rice"};
    Random r = new Random();
    
    int minAge;
    int maxAge;

    public Generator(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }
    
    public String getRandom(int count, int startingID){
        String json = "[";
        for(int i = 0; i < count; i++){
            json += createResult(startingID);
            startingID++;
        }
        json = json.substring(0, json.length()-1);
        json += "]";
        return json;
    }
    
    private String createResult(int ID){
        int age = r.nextInt(maxAge-minAge) + minAge;
        return "{\"fname\": \""+fNames[r.nextInt(fNames.length)]+"\", \"lname\": \""+lNames[r.nextInt(lNames.length)]+"\", \"id\": \""+ID+"\", \"age\": \""+ age +"\"},";
    }
}

/**
 * Since the assignment did not mention moving entities but only json I made the 
 * presumption that we were to write the json on my own, instead of making 
 * an entity and use something like gson to turn the entities into json strings
 * 
 */