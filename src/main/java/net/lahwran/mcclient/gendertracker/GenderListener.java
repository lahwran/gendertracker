/**
 * 
 */
package net.lahwran.mcclient.gendertracker;

import java.util.HashMap;
import java.util.HashSet;

import net.lahwran.capsystem.commdata.Booldata;
import net.lahwran.capsystem.commdata.Commdata;
import net.lahwran.capsystem.commdata.Stringdata;
import net.lahwran.mcclient.capsystem.Capsystem;
import net.lahwran.mcclient.capsystem.CommListener;
import net.lahwran.mcclient.capsystem.DisconnectedException;
import net.lahwran.mcclient.capsystem.ServerIncapableException;

/**
 * @author lahwran
 *
 */
public class GenderListener extends CommListener {

    @SuppressWarnings("unchecked")
    @Override
    public void onCommevent(Commdata[] arg0)
    {
        if(arg0.length != 2 || !(arg0[0] instanceof Stringdata) || !(arg0[0] instanceof Booldata))
        {
            System.err.println("invalid message: ");
            for(Commdata x:arg0)
            {
                System.err.println("\t"+x.encoded());
            }
            return;
        }
        Stringdata player = (Stringdata)arg0[0];
        Booldata isFemale = (Booldata)arg0[1];
        GenderTracker.playerFemales.put(player.decoded(), isFemale.decoded());
    }

    @Override
    public void onConnect()
    {
        GenderTracker.playerFemales = new HashMap<String,Boolean>();
        GenderTracker.playerHaveasked = new HashSet<String>();
        GenderTracker.capableServer = Capsystem.hasCap("+comm") && Capsystem.hasCap("+gendermod");
        try
        {
            if (GenderTracker.capableServer)
            GenderTracker.communication.sendMessage(new Commdata[]{new Booldata(GenderTracker.localplayer.isFemale())});
        }
        catch (DisconnectedException e)
        {}
        catch (ServerIncapableException e)
        {}
    }
}
