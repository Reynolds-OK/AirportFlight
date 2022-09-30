package ashesi.edu.gh.ICP313;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Reynolds Okyere Boakye
 * This class provide blueprint for the
 * current city, previous city and trip number
 */

public class Node{
    /**
     * Instance Variables/Fields
     */
    private String state;
    private Node parent;
    private int trip;


    public Node(String state) {
        this(state, null, 0);
    }

    public Node(String state, Node parent) {
        this(state, parent, 0);
    }

    /**
     * Constructor (Overloaded):
     * Build and initialise objects of this class
     * @param state the current city
     * @param parent the previous city
     * @param trip current trip number
     */
    public Node(String state, Node parent, int trip) {
        this.state = state;
        this.parent = parent;
        this.trip = trip;
    }

    /**
     * returns current state
     * @return this.state
     */
    public String getState(){
        return this.state;
    }

    /**
     * returns current state
     * @return this.state
     */
    public Node getParent(){
        return this.parent;
    }

    /**
     * returns current state
     * @return this.state
     */
    public int getTrip(){
        return this.trip;
    }

    @Override
    public int hashCode(){
        return (int) state.hashCode();
    }

    @Override
    public String toString(){
        return "Airport Code: "+state + ". From: " + parent + ". Trip: "+trip;
    }

    @Override
    public boolean equals(Object another){
        if (this == another) return true;
        if (another == null) return false;
        if (this.getClass() != another.getClass()) return false;
        Node user = (Node) another;
        return state.equals(user.state);
    }

    /**
     * returns the route from the start to end city
     * Also creates a new file with the output
     * @return final_path.toString()
     */
    public String path_route() throws IOException {
        StringBuilder final_path = new StringBuilder();
        String temp;
        Node current_node = this;
        int total_flights = current_node.getTrip();
        int count = total_flights;
        int additional_stops = 0;
        fileReader routes = new fileReader("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/routes.csv");
        ArrayList <String[]>  find_routes = routes.return_result();
        String airline = null;
        String stops = null;

        while (!(current_node.parent==null)){
            for (String[] each: find_routes){
                if(each[4].equals(current_node.getState()) && each[2].equals(current_node.getParent().getState())){
                    airline = each[0];
                    stops = each[7];
                    break;
                }
            }

            //write the required sentence for a route from one city to another with an airline
            temp = count+". "+airline+" from "+current_node.getParent().getState()+" to " + current_node.getState()+" "+ stops+" stops.\n";
            final_path.insert(0, temp);

            if(!stops.equals(null))
                additional_stops += Integer.parseInt(stops);

            current_node = current_node.getParent();
            count -= 1;
            stops = null;
            airline = null;
        }
        final_path.append("\nTotal flights: ").append(total_flights).append("\n");
        final_path.append("Total additional stops: ").append(additional_stops);

        fileWriter export_file = new fileWriter("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/accra-winnipeg");

        // returns true if output file creation was successful
        boolean result = export_file.Export(final_path.toString());

        if(result){
            System.out.println("Successfully exported route");
        } else{
            System.out.println("Failed to export route");
        }

        return final_path.toString();
    }
}
