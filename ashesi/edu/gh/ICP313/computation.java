package ashesi.edu.gh.ICP313;
import java.io.*;
import java.util.*;

/**
 * @author Reynolds Okyere Boakye
 * This class runs the program to locate the
 * shortest route from start to destination cities
 */

public class computation {
    public static void main(String [] args) throws IOException {
//        fileReader airlines = new fileReader("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/airlines.csv");
        fileReader routes = new fileReader("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/routes.csv");
        Map problem_map = new Map(routes.return_result()); // creates a map for the connected cities

        System.out.println(uniform_cost_search(problem_map)); // run the uniform search algorithm
    }

    /**
     * @param problem_map
     * returns String of the path if located
     * This class initializes the initial and destination cities
     */
    public static String uniform_cost_search(Map problem_map) throws IOException {

        String start_city_code = null;
        ArrayList<String> end_city_code = new ArrayList<>();
        boolean found_start = false;
        boolean found_end = false;
        fileReader airports = new fileReader("C:/Users/McReynaads/OneDrive - Ashesi University/schulen/2 sem 2/Intermediate Programming/ICP individual project/Reynolds_Boakye_Individual_Project/airports.csv");

        // compare the nodes of the cities using their number of trips
        Comparator<Node> usingTrips = Comparator.comparing(Node::getTrip);

        // uses priority queue to store the nodes to process the node with the shortest trip number
        PriorityQueue<Node> frontier = new PriorityQueue<>(usingTrips);

        // access the airport codes for the specifies starting and ending cities
        for(String[] arr: airports.return_result()){
            if(arr[2].equalsIgnoreCase(ProblemSpace.starting_city())
                    && arr[3].equalsIgnoreCase(ProblemSpace.starting_country())
                    && !arr[4].equalsIgnoreCase("\\N")){
                start_city_code = arr[4];
                found_start = true;

                Node city = new Node(start_city_code);
                frontier.add(city);
            }
            if(arr[2].equalsIgnoreCase(ProblemSpace.end_city())
                    && arr[3].equalsIgnoreCase(ProblemSpace.end_country())
                    && !arr[4].equalsIgnoreCase("\\N")){
                end_city_code.add(arr[4]);
                found_end = true;
            }
        }

        // possibly no airport in the specified city
        if(!found_start || !found_end){
            System.out.println("Starting Airport of Ending Airport Route not found");
            return null;
        }

        System.out.println("Starting Route Search from "+ProblemSpace.starting_city());
        System.out.println("Ending at "+ProblemSpace.end_city());

        Set<String> explored = new HashSet<>();// stores processed cities to prevent repetition

        System.out.println(frontier);// checking if all works
        System.out.println(explored);// checking if all works well

        while(frontier.size() > 0){
            Node City = frontier.poll();// process the city with the smallest trip

            if(end_city_code.contains(City.getState())){// when destination is located
                System.out.println("\nFound the destination");
                System.out.println("----------------------");
                return City.path_route(); // find the previous cities till the start city
            }

            explored.add(City.getState());// processed cities

            // acquire connected cities to current cities
            ArrayList<String> succ_city = ProblemSpace.connectedCities(City.getState(), problem_map);

            if(succ_city==null)
                continue;

            for(String each_city: succ_city){ // load the next city
                Node subcity = new Node(each_city, City,City.getTrip()+1);

                System.out.println("Moving from "+ City.getState() + " to next city "+subcity.getState());

                if(!explored.contains(subcity.getState()) && !frontier.contains(subcity)){
                    frontier.add(subcity); // add new city to the frontier
                } else if(frontier.contains(subcity)){
                    for(Node each: frontier){
                        if (subcity.equals(each)){
                            if(each.getTrip()>subcity.getTrip()){ // exchange previous city with same city if the new one has lower trip
                                frontier.remove(each);
                                frontier.add(subcity);
                            }
                            break;
                        }
                    }
                }
            }
        }


        return null;
    }
}