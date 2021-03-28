package script.theme_park.stp.ai;

import script.*;
import script.library.chat;
import script.library.anims;

/**
 *
 * @author Roachie
 */
public class brain extends script.base_script {

    public brain()
    {
    }
    
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnHearSpeech(obj_id self, obj_id target, String speech) throws InterruptedException
    {
        if (speech.contains("surrender"))
        {
            doAnimationAction(self, anims.PLAYER_HANDS_ABOVE_HEAD);
            chat.chat(self, "I surrender!");
        }
        if (speech.contains("hey"))
        {
            doAnimationAction(self, anims.PLAYER_LOOKNERVOUSLY);
            chat.chat(self, "Who are you talking to?");
        }
        if (speech.contains("weak"))
        {
            chat.chat(self, "Who you calling weak?");
            doAnimationAction(self, anims.PLAYER_THROW_OBJECT_BOTH);
            playClientEffectObj(self, "clienteffect/sienar_bomb.cef", target, "root");
            setObjVar(target, "ai.bomb_queue", true);
            attack(target);
        }
        return SCRIPT_CONTINUE;
    }
    public void attack(obj_id target) throws InterruptedException
    {
        int hp = getHealth(target);
        if (hp > 5000)
        {
            damage(target, 6.0f, 1, hp / 25);
            if (hasObjVar(target, "ai.bomb_queue"))
            {  
                dictionary d = new dictionary();
                d.put("target", target);
                messageTo(target, "setDebuff", d, 1.0f, true);
                if (!hasObjVar(target, "ai.bomb_queue_starter"))
                {
                    messageTo(target, "attack", d, 2.0f, true);
                    if (getIntObjVar(target, "ai.debuff_count") < 1)
                    {
                        int debuff_count = 0;
                        setObjVar(target, "ai.debuff_count", debuff_count++);
                    }
                }
            }
        }
    }
    public void setDebuff(obj_id target) throws InterruptedException
    {
        dictionary d = new dictionary();
        setObjVar(d.getObjId("target"), "ai.bomb_queue", 1);
        messageTo(target, "clearDebuff", d, 15.0f, true);
    }
    public void clearDebuff(obj_id target) throws InterruptedException
    {
        dictionary d = new dictionary();
        removeObjVar(d.getObjId("target"), "ai.bomb_queue");
    }
}
