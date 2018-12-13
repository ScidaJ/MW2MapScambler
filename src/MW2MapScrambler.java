import java.io.IOException;
import java.io.PrintWriter;


public class MW2MapScrambler {

    public static void main(String[] args) {
        String mapFile = "d://Games//New folder (2)//maps.txt";
        String execFile = "d://Games//New folder (2)//startserver.bat";
        MapListImport.importMap(mapFile);
        String exec = "@echo off\n" +
                "start iw4x.exe -dedicated set net_port 28961 +exec server.cfg +party_enable 0 +sv_maxclients 16"
                + " +set lan_only 1 +set fs_game \"mods/bots\" +set sv_mapRotation \"";
        for(int i = 0;i < MapListImport.mapList.size();i++){
            if(i < MapListImport.mapList.size() - 1){
                exec += "map " + MapListImport.mapContainer.get(MapListImport.mapList.get(i)) + " ";
            } else {
                exec += "map " + MapListImport.mapContainer.get(MapListImport.mapList.get(i));
            }
            System.out.println(MapListImport.mapList.get(Integer.valueOf(i)));
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
