package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import org.bukkit.entity.Player;

public class LifeDrainCurse extends ItemEnchant {
    public LifeDrainCurse() {
        super("ArmorLifeDrain", "&1Life Drain", EnchantmentType.WEAPON);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Makes the player take 2.5% of their hp per hit
        if(event.getAttacker() instanceof Player){
            PlayerData vPlayerData = new PlayerData((Player) event.getAttacker());
            vPlayerData.setHealth((int) (vPlayerData.getHealth() - (vPlayerData.getHealth() * (level*0.025))));
        }
    }
}
