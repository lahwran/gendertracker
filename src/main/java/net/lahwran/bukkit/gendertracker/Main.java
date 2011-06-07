
package net.lahwran.bukkit.gendertracker;

import java.util.HashMap;

import net.lahwran.bukkit.capsystem.*;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public final CapListener listener = new CapListener(this);
    public final Commplugin comm = Commsystem.register("Irwp", listener);
    public final HashMap<String,Boolean> playerFemales = new HashMap<String,Boolean>();
    
    public void onEnable() {
        Capsystem.registerCap("+gendermod:v0");
    }
    public void onDisable() {}

}
