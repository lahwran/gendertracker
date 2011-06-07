/**
 * 
 */
package net.lahwran.mcclient.gendertracker;

import java.util.HashMap;
import java.util.HashSet;

import net.lahwran.capsystem.commdata.Commdata;
import net.lahwran.capsystem.commdata.Stringdata;
import net.lahwran.mcclient.capsystem.Capsystem;
import net.lahwran.mcclient.capsystem.Commplugin;
import net.lahwran.mcclient.capsystem.Commsystem;
import net.lahwran.mcclient.capsystem.DisconnectedException;
import net.lahwran.mcclient.capsystem.ServerIncapableException;


/**
 * @author lahwran
 *
 */
public class GenderTracker {

    static boolean capableServer = false;
    
    static HashMap<String,Boolean> playerFemales = null;

    static HashSet<String> playerHaveasked = null;

    static Commplugin communication;

    static LocalGender localplayer;
    
    
    /**
     * check if a player is female
     * @param playerName player to check
     * @return is the player female
     */
    static boolean isPlayerFemale(String playerName)
    {
        if (!capableServer)
            return false;
        else if (playerFemales.get(playerName) != null)
            return playerFemales.get(playerName);
        else if (playerHaveasked.contains(playerName))
            return false;
        else
        {
            playerHaveasked.add(playerName);
            try
            {
                communication.sendMessage(new Commdata[]{new Stringdata(playerName)});
            }
            catch (DisconnectedException e)
            {}
            catch (ServerIncapableException e)
            {}
            return false;
        }
    }

    /**
     * should we cache the gender of a player yet?
     * @param playerName player to check
     * @return whether to cache - cache if positive
     */
    public static boolean shouldCache(String playerName)
    {
        return playerFemales.get(playerName) != null;
    }

    public static void initialize()
    {
        Capsystem.registerCap("+gendermod:v0");
        communication = Commsystem.register("Irwp", new GenderListener());
    }

    public static interface LocalGender
    {
        public boolean isFemale();
    }

    public static void setLocalGender(LocalGender localplayer)
    {
        if (GenderTracker.localplayer == null)
            GenderTracker.localplayer = localplayer;
        else
            throw new RuntimeException("Tried to set local gender retriever twice!");
    }
}
