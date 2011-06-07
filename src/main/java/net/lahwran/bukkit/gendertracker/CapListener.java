package net.lahwran.bukkit.gendertracker;

import net.lahwran.bukkit.capsystem.Commevent;
import net.lahwran.capsystem.commdata.*;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

public class CapListener extends CustomEventListener
{

    public final Main plugin;

    public CapListener(final Main main)
    {
        this.plugin = main;
    }
    
    @SuppressWarnings("unchecked")
    public void onCustomEvent(Event e)
    {
        if(e instanceof Commevent)
        {
            Commevent evt = ((Commevent)e);
            if(evt.getArgCount() != 1)
            {
                Commdata data = evt.getData(0);
                if (data instanceof Stringdata)
                {
                    Booldata isFemale = new Booldata(plugin.playerFemales.get(((Stringdata)data).decoded()));
                    Commdata[] message = new Commdata[]{data, isFemale};
                    plugin.comm.sendMessage(evt.getPlayer(), message);
                }
                else if (data instanceof Booldata)
                {
                    plugin.playerFemales.put(evt.getPlayer().getName(), ((Booldata)data).decoded());
                }
            }
        }
    }
}
