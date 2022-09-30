package ashesi.edu.gh.ICP313;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Reynolds Okyere Boakye
 * This class reads any file location and saves in an ArrayList
 */

public class fileReader {
    ArrayList <String[]> new_list = new ArrayList<>();
    private String file_location;

    /**
     * Constructor:
     * Build and initialise objects of this class
     * @param file_location the location+name of the input file
     */
    public fileReader(String file_location) throws IOException {
        this.file_location = file_location;
        BufferedReader readd = null;
        FileReader data_file = null;

        try {
            data_file = new FileReader(file_location);
            readd = new BufferedReader(data_file);

            String each_line;

            while ((each_line = readd.readLine()) != null) {
                new_list.add(each_line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")); // using regex to correct , problems when reading file
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert readd != null;
            readd.close();
            data_file.close();
        }
    }

    /*
     * @prints the arraylist of the file
     */
    public void show_result(){
        System.out.println(new_list);
    }

    /*
     * @returns the arraylist of the file
     */
    public ArrayList<String[]> return_result(){
        return new_list;
    }
}