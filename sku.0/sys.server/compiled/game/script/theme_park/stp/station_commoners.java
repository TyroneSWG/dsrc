package script.theme_park.stp;

import script.base_script;
import script.library.ai_lib;
import script.obj_id;

/**
 *
 * @author Roachie
 */
public class station_commoners extends base_script {
    public station_commoners() {
    }
    public static String[] NAMES = {
            "a cheapskate",
            "a gambler",
            "a thug",
            "a veteran pilot",
            "an explorer",
    };
        
    public int OnInitialize(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,4)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,4)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
