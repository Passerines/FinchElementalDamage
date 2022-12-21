package net.passerines.finch.enchants.weaponenchants;

import net.passerines.finch.enchants.ItemEnchant;
import net.passerines.finch.events.ElementalDamageEvent;

public class FireAspectEnchant extends ItemEnchant {
    public FireAspectEnchant() {
        super("FireAspectSword", "&9Fire Aspect", EnchantmentType.WEAPON);
    }

    @Override
    public void onElementalDamage(ElementalDamageEvent event, int level) {
        //Burn mob
        event.getVictim().setFireTicks(level*50);
    }
}
