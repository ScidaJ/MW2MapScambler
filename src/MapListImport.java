import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class MapListImport {
    static Map<Integer, String> mapList;
    static Map<String, String> mapContainer;

    public static Map<Integer, String> importMap(String fileName){
        mapList = new HashMap<>();
        mapContainer = new HashMap<>();
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            String[] maps = stream.toArray(String[]::new);
            int size = (maps.length);
            Random rand = new Random();
            for(String s: maps){
                Integer integer = new Integer(rand.nextInt(size));
                while(mapList.containsKey(integer)){
                    if(integer != 0){
                        integer = integer - 1;
                    } else {
                        integer = size;
                    }
                }
                String[] mapHold = s.split(",");
                String inGame = mapHold[0];
                String plainText = mapHold[1];
                mapContainer.put(plainText, inGame);
                mapList.put(integer, plainText);
            }
            return mapList;
        } catch(IOException e){
            System.err.print(e);
        }
        return null;
    }
}
