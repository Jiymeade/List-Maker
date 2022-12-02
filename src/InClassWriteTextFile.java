import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class InClassWriteTextFile {
    public static void main(String[] args) {

        ArrayList<String> records = new ArrayList<>();
        records.add("This is a sample data to write into our text file");
        records.add("This data will be added to Line 2 of our text file");
        records.add("This data is for Line 3 of the text file");
        records.add("Sample sata for Line 4");
        records.add("Sample data for Line 5");
        String[] fields = new String[0];

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath()+ "\\src\\1.text" );

        try {

            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String rec: records){
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data has been successfully written to sampledata.txt");

        }catch(IOException ex){
            ex.printStackTrace();

        }


    }
}
