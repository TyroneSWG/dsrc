/**
 * // @TODO do i only need localtext or just text
 * <3 aconite
 **/
package script.hub.items;

import script.*;
import script.library.sui;
import script.library.instance;
import script.library.utils;

public class datapad extends base_class {

    public datapad() {
    }

    public static String GOLD = "\\#FFD700";
    public static String WHITE = "\\#FFFFFF";
    public static String STATIC_MESSAGE = "[string_id me pls] TEXT TEXT TEXT TEXT TEXT TEXT TEXT ";

    public static int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public static int OnInitialize(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Test"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE) {
            int page = createSUIPage("/Script.hubWindow", self, self);
            setSUIProperty(page, "btnOk", "LocalText", "Travel");
            setSUIProperty(page, "btnCancel", "LocalText", "Decline");
            setSUIProperty(page, "text", "LocalText", GOLD + "THE ADVENTURE CONTINUES..." + WHITE);
            setSUIProperty(page, "main.text", "LocalText", WHITE + STATIC_MESSAGE);
            setSUIProperty(page, "bg.caption.text", "LocalText", "Outer Rim Travel Authority");
            subscribeToSUIEvent(page, sui_event_type.SET_onButton, "btnOk", "OnButtonClick");
            //subscribeToSUIPropertyForEvent(page, sui_event_type.SET_onButton, "btnOk", "pageText.text", "Text");
            setSUIAssociatedObject(page, self);
            boolean showResult = showSUIPage(page);
            if (!showResult) {
                WARNING("Cannot display UI page '/Script.hubWindow");
            }
            flushSUIPage(page);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnButtonClick(obj_id self, dictionary params) throws InterruptedException {
        obj_id player = sui.getPlayerId(params);
        obj_id planetId = getPlanetByName("dungeon_hub");
        obj_id marker = utils.getObjIdObjVar(planetId, "hub_marker");
        if (!isIdValid(player) && (!isIdValid(planetId) && (!isValidId(marker)))) {
            systemMsg(player, "one or more id's are not valid.");
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(planetId, "hub_entrance")) {
            systemMsg(player, "marker not found.");
            return SCRIPT_CONTINUE;
        }
        setLocation(player, marker);
        return SCRIPT_CONTINUE;
    }
}
