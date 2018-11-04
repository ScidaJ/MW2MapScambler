import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MapListImport {

    public static ArrayList<Maps> importMap(String fileName){
        ArrayList<Maps> mapList = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            String[] maps = stream.toArray(String[]::new);
            for(String s: maps){
                String[] mapHold = s.split(",");
                Maps map = new Maps(mapHold[0], mapHold[1]);
                mapList.add(map);
            }
            return mapList;
        } catch(IOException e){
            System.err.print(e);
        }
        return null;
    }
}
