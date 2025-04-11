package sg.com.ntu.group3.models;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
