package net.passerines.finch.enchants.weaponenchants;

import net.kyori.adventure.text.Component;
import net.passerines.finch.enchants.ItemEnchants;
import net.passerines.finch.events.ElementalDamageEvent;

public class FireAspectEnchant extends ItemEnchants {
    public FireAspectEnchant() {
        super("FireAspectSword", "&9Fire Aspect", EnchantmentType.WEAPON, 0, 15, 0, 0);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Burn mob
        event.getVictim().setFireTicks(level*50);
    }
}
