package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import org.bukkit.entity.Player;

public class LifestealEnchant extends ItemEnchant {
    public LifestealEnchant() {
        super("SwordLifesteal", "&1Lifesteal", EnchantmentType.WEAPON);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Makes the player heal 1% of their hp per hit
        if(event.getAttacker() instanceof Player && !event.getVictim().equals(event.getAttacker())){
            PlayerData vPlayerData = new PlayerData((Player) event.getAttacker());
            new ElementalDamageEvent(event.getAttacker(), event.getAttacker(), ElementalDamageEvent.Element.DARK, (-1 * (event.getDamage()*0.01))).apply();
            event.getAttacker().sendMessage("ATTACK DONE. LIFESTEAL");
        }
    }
}