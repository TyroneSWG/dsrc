package script.theme_park.stp;

import script.*;
import script.library.instance;
import script.library.space_station;
import script.library.sui;

/**
 *
 * @author Roachie
 */
public class hub_transport extends script.base_script {
    public hub_transport() {
    }
    public static String SS = "object/building/hub/space_station.iff";
    public static String RED = "\\#0DCC19";
    public static String WHITE = "\\#FFFFFF";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Transport Terminal");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (data != null)
        {
            data.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            String title = "Confirm Departure";
            String prompt = "Would you like to board this shuttle for " + RED + "2,500" + WHITE + "credits";
            sui.msgbox(self, player, prompt, sui.OK_CANCEL, title, "handleConfirm");
        }
        return SCRIPT_CONTINUE;
    }

    public int handleConfirm(obj_id self, dictionary params) throws InterruptedException {
        if (params == null || params.isEmpty()) {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int btn = sui.getIntButtonPressed(params);
        if (btn == sui.BP_OK) {
            instance.requestInstanceMovement(player, "space_hub");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
