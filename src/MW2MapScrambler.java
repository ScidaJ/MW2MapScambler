import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

public class MW2MapScrambler {

    public static void main(String[] args) {
        String mapFile = "d://Games//New folder (2)//maps.txt";
        String execFile = "d://Games//New folder (2)//startserver.bat";
        Map<String, String> mapList = MapListImport.importMap(mapFile);
        int size = mapList.size();
        Random rand = new Random(size);
        String exec = "@echo off\n" +
                "start iw4x.exe -dedicated set net_port 28961 +exec server.cfg +party_enable 0 +sv_maxclients 16"
                + "set lan_only 1 +set fs_game \"mods/bots\" +set sv_mapRotation \"";
        String[] k = mapList.keySet().toArray(new String[size]);
        for(int i = 0;i < size;i++){
            String map = k[rand.nextInt(size)];
            if(i < size - 1){
                exec += mapList.get(map) + " ";
            } else {
                exec += mapList.get(map);
            }
            System.out.println(map);
        }
        exec += "\" +map_rotate +set g_gametype dom +set bots_manage_fill 12 +set scr_xpscale 4";
        try {
            PrintWriter out = new PrintWriter(execFile);
            out.println(exec);
            out.close();
        } catch(IOException e){
            System.err.print(e);
        }
    }
}
