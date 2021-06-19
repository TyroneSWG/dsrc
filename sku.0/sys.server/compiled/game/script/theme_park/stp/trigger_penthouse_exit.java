package script.theme_park.stp;

import script.*;
import script.library.factions;

public class trigger_penthouse_exit extends base_class {

    public trigger_penthouse_exit() {
    }
    public static String TRIGGER_VOLUME_NAME = "penthouse_exit";
    public int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialized(obj_id self) throws InterruptedException {
        createTriggerVolume(TRIGGER_VOLUME_NAME, 3.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException {
        if (isMob(breacher)) {
            return SCRIPT_CONTINUE;
        }
        location exitPoint = new location();
        exitPoint.area = "taanab";
        exitPoint.x = 5424;
        exitPoint.y = 75;
        exitPoint.z = -4327;
        warpPlayer(breacher, exitPoint, "noHandler", false);
        return SCRIPT_OVERRIDE;
    }
    public int OnTriggerVolumeExited(obj_id self, String triggerVolumeName, obj_id breacher) throws InterruptedException {
        return SCRIPT_OVERRIDE;
    }
}
