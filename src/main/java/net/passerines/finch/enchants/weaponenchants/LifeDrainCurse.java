package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import org.bukkit.entity.Player;

public class LifeDrainCurse extends ItemEnchant {
    public LifeDrainCurse() {
        super("LifeDrainCurse", "&1Life Drain", EnchantmentType.WEAPON);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Makes the player take 2.5% of their hp per hit
        event.getAttacker().sendMessage("ATTACK DONE. LIFE DRAIN");
        if(event.getAttacker() instanceof Player && !event.getVictim().equals(event.getAttacker())){
            PlayerData vPlayerData = new PlayerData((Player) event.getAttacker());
            new ElementalDamageEvent(event.getAttacker(), event.getAttacker(), ElementalDamageEvent.Element.DARK, vPlayerData.getHealth() * (level*0.025)).apply();
        }
    }
}
