package twitteranalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import static twitteranalysis.TwitterAnalysis.twitterConditions;

/**
 *
 * @author Fraser
 */
public class SupervisedLearning {

    public static HashMap<Integer, Integer> readConditionsFromTxt(File file) throws IOException, NumberFormatException {
        BufferedReader br = null;
        FileReader fr = null;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String array[] = sCurrentLine.split("\\|");
                hmap.put(Integer.parseInt(array[0].replace(" ", "")), Integer.parseInt(array[4].replace(" ", "")));
            }
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return hmap;
    }

    public static void writeConditionsToTxt(String tableName, int oldMaxId, int maxEntries) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        File file = new File("C:\\Users\\Fraser\\Google Drive\\GitProjects\\TwitterAnalysis\\src\\twitteranalysis\\SupervisedLearningTxt\\" + tableName + ".txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File exists.");
            }
        } catch (IOException ex) {
            System.out.println("Create file exception");
        }

        int count = 1;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (EncodeChromosome twitterCondition : twitterConditions) {
                bw.write(oldMaxId + " | " + twitterCondition.getOgUserName() + " | " + twitterCondition.getOgStatus() + " | " + twitterCondition.getComment() + " | ");
                bw.write("\n");

                if (count > maxEntries - 1) {
                    break;
                }
                oldMaxId++;
                count++;
            }
        } catch (IOException e) {
            System.out.println("Write file exception");
        }
    }
}
