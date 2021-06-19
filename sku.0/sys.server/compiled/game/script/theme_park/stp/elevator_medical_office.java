package script.theme_park.stp;

import script.*;
import script.library.utils;

public class elevator_medical_office extends base_class {

    public elevator_medical_office() {
    }
    public static String BUILDING = "object/building/player/player_house_generic_small_windowed.iff";
    public int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialized(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, utils.unpackString("Call Elevator"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            //@TODO make a better method for these types of transport
            obj_id[] targetLocs = getAllObjectsWithTemplate(getLocation(self), 120.0f, BUILDING);
            obj_id cell = getCellId(targetLocs[0], "room1");
            warpPlayer(player, getCurrentSceneName(), 0.0f, 0.0f, 0.0f, cell, 0.0f, 0.0f, 0.0f);
        }
        return SCRIPT_CONTINUE;
    }
}
