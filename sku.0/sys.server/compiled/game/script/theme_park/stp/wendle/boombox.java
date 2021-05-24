package script.theme_park.stp.wendle;

import script.*;
/**
 *
 * @author Roachie
 */
public class boombox extends script.base_script {
    public boombox() {
    }
    public static String SNDPRE = "sound/mus_";
    public int OnAttach(obj_id self) throws InterruptedException
    {
       createTriggerVolume("boombox_cantina", 15.0f, false);
       setName(self, "a radio (inactive)");
       return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
       createTriggerVolume("boombox_cantina", 15.0f, false);
       setName(self, "a radio (inactive)");
       return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (!isPlayer(breacher) || (!volumeName.equals("boombox_cantina"))) {
            return SCRIPT_CONTINUE;
        }
        String SNDFILEPIRATE = "pirate_cantina.snd";
        String SNDFILECANTINA = "cantina_band_remix.snd";
        setName(self, getName(self) + " (active)");
        play2dNonLoopingSound(self, SNDPRE + SNDFILEPIRATE);
        return SCRIPT_CONTINUE;
    }
}