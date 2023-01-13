package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class BloodLustEnchant extends ItemEnchant {
    public BloodLustEnchant() {
        super("SwordBloodLust", "&cBloodlust", EnchantmentType.WEAPON);
    }}

        /*
    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        ItemMeta playerWeapon = event.getWeapon().getItemMeta();
        playerWeapon.getPersistentDataContainer().set(Util.getNamespacedKey("kills"), PersistentDataType.INTEGER, 0);
        event.getWeapon().setItemMeta(playerWeapon);
        //Makes the player do +level%*1% damage per 100 kills
        if(event.getAttacker() instanceof Player){

        }
    }
}
*/
