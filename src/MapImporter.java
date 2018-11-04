import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class MapImporter {
    private static String mapFile = "d://Games//New folder (2)//maps.txt"; // TODO: Read from env

    public static Map<String, String> perform() throws IOException {
        return Files.lines(Paths.get(mapFile))
            .map(s -> s.split(","))
            .collect(Collectors.toMap(item -> item[0], item -> item[1]));
    }
}
