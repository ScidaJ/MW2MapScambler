import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

public class MapListImport {
  static Map<Integer, String> mapList;
  static Map<String, String> mapContainer;
  static Logger logger = Logger.getLogger(MapListImport.class.getName());
  static Random rand;
  static FileHandler handler;


  private MapListImport() {
  }

  public static void importMap(String fileName) {
    try {
      handler = new FileHandler("Import.log");
      logger.addHandler(handler);
      rand = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      logger.log(Level.SEVERE, e.toString(), e);
    }
    handler.setFormatter(new SimpleFormatter());
    mapList = new HashMap<>();
    mapContainer = new HashMap<>();
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      String[] maps = stream.toArray(String[]::new);
      int size = (maps.length - 1);
      for (String s : maps) {
        int integer = rand.nextInt(size);
        while (mapList.containsKey(integer)) {
          if (integer != 0) {
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
    } catch (IOException e) {
      logger.log(Level.SEVERE, e.toString(), e);
    }
  }
}
