package ashesi.edu.gh.ICP313;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Reynolds Okyere Boakye
 * This class creates an inteconnected map for the cities
 */

public class Map {
    private HashMap<String, ArrayList<String>> new_map = new HashMap<>();

    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param routes the name of the food
     */
    public Map(ArrayList <String[]> routes){
        ArrayList <String> result;

        for (String[] each: routes){
            //checks whether city already exists else create an empty list
            result = this.new_map.getOrDefault(each[2], new ArrayList<String>());
            if (result.contains(each[4]))
                continue;
            result.add(each[4]);
            this.new_map.put(each[2], result);
        }
    }

    /**
     * returns new_map cities connecting directly to other cities
     */
    public HashMap return_map(){
        return new_map;
    }
}
