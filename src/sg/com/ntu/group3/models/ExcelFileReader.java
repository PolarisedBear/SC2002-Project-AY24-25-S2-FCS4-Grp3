package sg.com.ntu.group3.models;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/** Excel File Reader to take in and register user details from a csv file
 *
 */
public class ExcelFileReader {
    public static List<String[]> read(String exfilePath) throws IOException {
        List<String> filerows = Files.readAllLines(Path.of(exfilePath));
        List<String[]> data = new ArrayList<>();

        for (String filerow : filerows) {
            if (!filerow.trim().isEmpty()) {
                data.add(filerow.split(","));
            }
        }

        return data;
    }
}