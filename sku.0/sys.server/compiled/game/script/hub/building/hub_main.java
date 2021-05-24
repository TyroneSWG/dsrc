package script.hub.building;

import script.*;
import script.library.instance;

public class hub_main extends script.base_script {

	public hub_main() {
	}
	public static String HUB_SCENE = "dungeon_hub";

	public int OnAttach(obj_id self) throws InterruptedException {
		instance.registerInstance(self, "space_hub");
		return SCRIPT_CONTINUE;
	}
	public int OnInitialize(obj_id self) throws InterruptedException {
		instance.registerInstance(self, "space_hub");
		return SCRIPT_CONTINUE;
	}
	public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item)
			throws InterruptedException {
		if (!isPlayer(item)) {
			return SCRIPT_CONTINUE;
		}
		if (!hasObjVar(self, "hub")) {
			setObjVar(self, "hub.population", 0);
		}
		int new_count = 0;
		setObjVar(self, "hub.population", new_count++);
		attachScript(item, "hub.player.player_hub");
		messageTo(item, "setupForInitialHubVisit", null, 15.0f, true);
		return SCRIPT_CONTINUE;
	}

	public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) {
		if (!isPlayer(item)) {
			return SCRIPT_CONTINUE;
		}
		if (!hasObjVar(self, "hub")) {
			setObjVar(self, "hub.population", 0);
		}
		int lose_count = getIntObjVar(self, "hub.population");
		int count = lose_count--;
		setObjVar(self, "hub.population", count);
		detachScript(item, "hub.player.player_hub");
		return SCRIPT_CONTINUE;
	}
}
