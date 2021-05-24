package script.ai.mechanics;

import script.library.utils;
import script.*;

public class brain extends script.base_class {

    public brain() {
    }

    public String[] STATES = {
            "DEFENSIVE",
            "PASSIVE",
            "AGGRESSIVE",
            "NEUTRAL"
    };

    public static int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public static void Think(obj_id self) {
        //do something
    }

    public static void setupBrain(obj_id self, int state) {
        setObjVar(self, "ai.brain.template", state);
    }

    public static void Rethink(obj_id self) {

    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        if (isGod(player)) {
            string_id menu = utils.unpackString("Setup");
            menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
            //mi.addSubMenu(mi, menu_info_types.SERVER_MENU1, menu); wtf is the int
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE && isGod(player)) {
            setupBrain(self, 1);
        }
        return SCRIPT_CONTINUE;
    }
}
