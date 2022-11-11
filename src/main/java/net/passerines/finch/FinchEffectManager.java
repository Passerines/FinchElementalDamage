package net.passerines.finch;

import de.slikey.effectlib.EffectManager;

public class FinchEffectManager {
    private static final EffectManager effectManager = new EffectManager(FinchElementalDamage.inst());
    public FinchEffectManager(){

    }
    public static EffectManager getEffectManager() {
        return effectManager;
    }
}
