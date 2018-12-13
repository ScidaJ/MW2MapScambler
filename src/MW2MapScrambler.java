import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class MW2MapScrambler {

    static Logger logger = Logger.getLogger(MW2MapScrambler.class.getName());
    static FileHandler handler;

    public static void main(String[] args) {
        try {
            handler = new FileHandler("Scrambler.log");
            logger.addHandler(handler);
        } catch(IOException e){
            logger.log(Level.SEVERE, e.toString(), e);
        }
        handler.setFormatter(new SimpleFormatter());
        String mapFile = args[0];
        String execFile = args[1];
        MapListImport.importMap(mapFile);
        StringBuilder bld = new StringBuilder();
        bld.append("@echo off\n");
        bld.append("start iw4x.exe -dedicated set net_port 28961 +exec server.cfg +party_enable 0 +sv_maxclients 16");
        bld.append(" +set lan_only 1 +set fs_game \"mods/bots\" +set sv_mapRotation \"");
        for(int i = 0;i < MapListImport.mapList.size();i++){
            if(i < MapListImport.mapList.size() - 1){
                bld.append("map ");
                bld.append(MapListImport.mapContainer.get(MapListImport.mapList.get(i)));
                bld.append(" ");
            } else {
                bld.append("map ");
                bld.append(MapListImport.mapContainer.get(MapListImport.mapList.get(i)));
            }
            logger.log(Level.INFO, MapListImport.mapList.get(i));
        }
        bld.append("\" +map_rotate +set g_gametype dom +set bots_manage_fill 12 +set scr_xpscale 4");
        try {
            PrintWriter out = new PrintWriter(execFile);
            out.println(bld.toString());
            out.close();
        } catch(IOException e){
            logger.log(Level.SEVERE,e.toString(), e);
        }
    }
}
