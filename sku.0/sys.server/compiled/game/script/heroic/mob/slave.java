package script.heroic.mob;

import script.library.combat;
import script.library.chat;
import script.dictionary;
import script.location;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

/**
 *
 * @author TyroneSWG
 */
public class slave extends script.base_script {

    public slave()
    {
    }

    public static String[] SLAVE_SPEAK =
    {
        "Thank you!", "Finally!", "Run for it!", "Thanks pal.", "Great...",
    };

    public static int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("heroic", "free_slave"));
        return SCRIPT_CONTINUE;
    }

    public static int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (combat.isInCombat(self))
            {
                return SCRIPT_CONTINUE;
            }

            int choice = rand(0, 4);
            chat.chat(self, SLAVE_SPEAK[choice]);
            sendSystemMessageTestingOnly(self, "You have freed a slave.");
            if (!hasObjVar(player, "heroics.karma"))
            {
                setObjVar(player, "heroics.karma", 1);
            } else if (hasObjVar(player, "heroics.karma"))
            {
                int points = getIntObjVar(player, "heroics.karma");
                int finalPoints = points + 1;
                setObjVar(player, "heroics.karma", finalPoints);
            }
            messageTo(self, "handleRunAway", null, 1.5f, true);
        }
        return SCRIPT_CONTINUE;
    }

    public int handleRunAway(obj_id self, dictionary params) throws InterruptedException
    {
        /*
		 * TODO: make it so the move loc is dynamic and not static. Otherwise instance
		 * #2 will have slaves run to instance #1 and that might be bad.
         */
        obj_id[] getAwayShip = getAllObjectsWithObjVar(getLocation(self), 200.0f, "runToMe");
        location freeLoc = getLocation(getAwayShip[0]);
        setMovementRun(self);
        pathTo(self, freeLoc);
        messageTo(self, "handleDelayedDestruct", null, 30, false);
        return SCRIPT_CONTINUE;
    }

    public int handleDelayedDestruct(obj_id self, dictionary params) throws InterruptedException
    {
        String[] CHATTER = {
                "Thank the maker. I am free!",
                "Sure ain't gonna miss this place!",
                "Thanks!",
                "Alright!"
        };
        chat.chat(self, CHATTER[rand(0,3)]);
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
