package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import org.bukkit.entity.Player;

public class BloodLustEnchant extends ItemEnchant {
    public BloodLustEnchant() {
        super("SwordBloodLust", "&cBloodlust", EnchantmentType.WEAPON);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Makes the player do +level%*10% damage per hit
        if(event.getAttacker() instanceof Player){
            PlayerData vPlayerData = new PlayerData((Player) event.getAttacker());
            vPlayerData.setAttack((int) (vPlayerData.getAttack() + (vPlayerData.getAttack() * (level*0.1))));
        }
    }
}
