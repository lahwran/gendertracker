import net.lahwran.mcclient.gendertracker.GenderTracker;

/**
 * @author lahwran
 *
 */
public class mod_GenderTracker extends BaseMod {

    static {
        GenderTracker.initialize();
    }

    /* (non-Javadoc)
     * @see BaseMod#Version()
     */
    @Override
    public String Version()
    {
        // TODO Auto-generated method stub
        return "1";
    }
    
    
}
