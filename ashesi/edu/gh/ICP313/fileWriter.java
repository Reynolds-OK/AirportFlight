package ashesi.edu.gh.ICP313;

import java.io.*;

/**
 * @author Reynolds Okyere Boakye
 * This class creates a new output file and writes into it
 */

public class fileWriter {
    /**
     * Instance Variable/Field
     */
    private String file_location;

    /**
     * Constructor (Overloaded):
     * Build and initialise objects of this class
     * @param location the current city
     */
    public fileWriter(String location){
        file_location = location+"_output.txt";
    }

    /**
     * @param file
     * Main writing block for the output file
     * @return boolean true if successful, false otherwise
     */
    public boolean Export(String file) throws IOException {
        FileWriter file_name = null;
        BufferedWriter output_now = null;

        try {
            File new_file = new File(file_location);
            boolean done = new_file.createNewFile(); //create a new output file

            file_name = new FileWriter(file_location);
            output_now = new BufferedWriter(file_name);

            if(done) { //write to output file if file creation was successful
                output_now.write(file);
                output_now.flush();
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            assert output_now != null; //incase output_now is not null
            output_now.close();
            file_name.close();
        }
    }
}
