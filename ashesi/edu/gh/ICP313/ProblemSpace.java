package ashesi.edu.gh.ICP313;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Reynolds Okyere Boakye
 * This class initializes the initial and destination cities
 */

public class ProblemSpace {
    /**
     * static instance Variable/Field
     */
    static fileReader problem;

    static {
        try {
            problem = new fileReader("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/accra-winnipeg.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<String[]> space = problem.return_result();
    private static String initCity = space.get(0)[0]; //initial city
    private static String initCountry = space.get(0)[1]; //initial country
    private static String destCity = space.get(1)[0]; //destination city
    private static String destCountry = space.get(1)[1]; //destination country

    /**
     * display the information of the route
     */
    public static void Display(){
        System.out.println("Starting city: "+ initCity+ ". Country: "+initCountry);
        System.out.println("Destination city: "+destCity+ ". Country: "+destCountry);
    }

    /**
     * display the starting city
     */
    public static String starting_city(){
        return initCity;
    }

    /**
     * display the starting country
     */
    public static String starting_country(){
        return initCountry;
    }

    /**
     * display the destination city
     */
    public static String end_city(){
        return destCity;
    }

    /**
     * display the destination country
     */
    public static String end_country(){
        return destCountry;
    }

    /**
     * @param state, newMap
     * @return found
     */
    public static ArrayList<String> connectedCities (String state, Map newMap) throws IOException {
        HashMap travellingMap = newMap.return_map();

        ArrayList<String> found = (ArrayList<String>) travellingMap.get(state);

        if(found == null){
            System.out.println("Has no Starting Airport code");// some airports in the cities have no code
        }
        return found;
   }
}
