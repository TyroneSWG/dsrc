package script.theme_park.stp;

import script.*;
import script.library.hue;

public class setcolor_objvar extends base_class {

    public setcolor_objvar() {
    }

    public int OnAttach(obj_id self) throws InterruptedException {
        setHue(self, rand(0,63));
        return SCRIPT_CONTINUE;
    }
    public void setHue(obj_id self, int value) throws InterruptedException
    {
        PROFILER_START("debugging.setHue");
        ranged_int_custom_var[] c = hue.getPalcolorVars(self);
        if (c != null)
        {
            for (ranged_int_custom_var vC : c) {
                vC.setValue(value);
            }
        }
        PROFILER_STOP("debugging.setHue");
    }
}
