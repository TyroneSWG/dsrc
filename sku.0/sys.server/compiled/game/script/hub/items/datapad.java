/**
 // @NOTE do i only need localtext or just text
 **/
package script.hub.items;

import script.*;
import script.library.sui;
import script.library.instance;

public class datapad extends base_class {

    public datapad() {
    }
    public static String STATIC_MESSAGE = "";
    public static int OnAttach(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }

    public static int OnInitialize(obj_id self) throws InterruptedException {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Test"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE) {
            int page = createSUIPage("/Script.hubWindow", self, self);
            setSUIProperty(page, "btnOk", "LocalText", "Travel");
            setSUIProperty(page, "btnCancel", "LocalText", "Decline");
            setSUIProperty(page, "text", "LocalText", "Test");
            setSUIProperty(page, "main.text", "LocalText", STATIC_MESSAGE);
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
        if (isIdValid(player)) {
            instance.requestInstanceMovement(player, "space_hub");
            //instance.movePlayerToInstance(player, instance_id, instance_name, owner, true, 1);
        }
        return SCRIPT_CONTINUE;
    }
}
