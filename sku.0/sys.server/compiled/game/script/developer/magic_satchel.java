/**
 * Notes:
 *
 */

package script.developer;

import script.*;
import script.library.create;
import script.library.utils;

import static script.library.utils.unpackString;

public class magic_satchel extends base_script {

    public magic_satchel() {
    }

    public int OnAttach(obj_id self) throws InterruptedException {
        setName(self, "X");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
        int mainMenu = mi.addRootMenu(menu_info_types.ITEM_USE, unpackString("Duplicate"));
        mi.addSubMenu(mainMenu, menu_info_types.ITEM_USE, unpackString("Clear Contents"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id dupeContainer = utils.getInventoryContainer(self);
            obj_id pInv = utils.getInventoryContainer(player);
            if (getVolumeFree(pInv) <= 0) {
                systemMsg(player, "Your inventory is full.");
            }
            obj_id[] dupeContents = getContents(dupeContainer);
            for (obj_id converted : dupeContents)
            {
                obj_id final_product = utils.stringToObjId(getTemplateName(converted));
                String[] scriptList = converted.getScripts();
                for (String srcScript : scriptList) attachScript(final_product, srcScript);
                String packedVars = getPackedObjvars(converted);
                setPackedObjvars(final_product, packedVars);
                obj_id goodie_bag = create.createObject("object/tangible/container/general/satchel.iff", pInv, "");
                moveContents(dupeContainer, goodie_bag);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
