package script.hub.building;

import script.*;
import script.library.utils;

public class controller extends base_class {

    public controller() {
    }

    public static final String CONTROLLER = "object/tangible/ground_spawning/patrol_waypoint.iff";

    public int OnInitialize(obj_id self) throws InterruptedException {
        setName(self, "Hub Marker");
        location selfLoc = getLocation(self);
        obj_id objects[] = getObjectsInRange(selfLoc, 0.1f);
        boolean exists = false;
        if (objects != null || objects.length > 0)
        {
            for (obj_id object : objects) {
                if ((getTemplateName(object)).equals(CONTROLLER)) {
                    exists = true;
                }
            }
        }
        if (!exists)
        {
            createMasterObject(self);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAboutToBeTransferred(obj_id self, obj_id destContainer, obj_id transferer) throws InterruptedException {
        sendSystemMessageTestingOnly(transferer, "You cannot interact with this.");
        return SCRIPT_OVERRIDE;
    }

    public int createMasterObject(obj_id self, dictionary params) throws InterruptedException {
        createMasterObject(self);
        return SCRIPT_CONTINUE;
    }

    public void createMasterObject(obj_id self) throws InterruptedException {
        obj_id object = createObject(CONTROLLER, getLocation(self));
        obj_id planetId = getPlanetByName("dungeon_hub");
        setObjVar(planetId, "hub_entrance", self);
        persistObject(self);
        WARNING("Creating Hub Marker Object ("+object+")...");
    }
}
