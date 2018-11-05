import java.io.File

fun main(args: Array<String>) {
    val maps = File("d://Games//New folder (2)//maps.txt").readLines().map { it.split(",")[0] }.shuffled()

    File("d://Games//New folder (2)//startserver.bat").writeText(
        "@echo off\n"
            + "start iw4x.exe -dedicated set net_port 28961 +exec server.cfg +party_enable 0 +sv_maxclients 16"
            + "set lan_only 1 +set fs_game \"mods/bots\" +set sv_mapRotation \""
            + maps.joinToString(" ")
            + "\" +map_rotate +set g_gametype dom +set bots_manage_fill 12 +set scr_xpscale 4"
    )
}