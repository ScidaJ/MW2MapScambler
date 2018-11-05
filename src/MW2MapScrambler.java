import java.io.IOException;
import java.io.PrintWriter;


public class MW2MapScrambler {
    public static void main(String[] args) throws IOException {
        String execFile = "d://Games//New folder (2)//startserver.bat"; // TODO: Read from env

        List<String> maps = new ArrayList<>(MapImporter.perform().keySet());
        Collections.shuffle(maps);

        PrintWriter out = new PrintWriter(execFile);
        out.println(
            "@echo off\n"
                + "start iw4x.exe -dedicated set net_port 28961 +exec server.cfg +party_enable 0 +sv_maxclients 16"
                + "set lan_only 1 +set fs_game \"mods/bots\" +set sv_mapRotation \""
                + maps.stream().reduce("", (acc, e) -> acc + e + " ").trim()
                + "\" +map_rotate +set g_gametype dom +set bots_manage_fill 12 +set scr_xpscale 4"
        );
        out.close();
    }
}
