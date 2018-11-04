import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapListImport {

    public static Map<String, String> importMap(String fileName){
        Map<String, String> mapList = new HashMap<>();
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            String[] maps = stream.toArray(String[]::new);
            for(String s: maps){
                String[] mapHold = s.split(",");
                Maps map = new Maps(mapHold[0], mapHold[1]);
                mapList.put(map.getPlainText(), map.getInGame());
            }
            return mapList;
        } catch(IOException e){
            System.err.print(e);
        }
        return null;
    }
}
