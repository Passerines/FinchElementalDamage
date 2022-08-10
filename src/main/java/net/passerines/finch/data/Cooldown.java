package net.passerines.finch.data;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class Cooldown<T> {

    private final int defaultCooldown;
    private final HashMap<T, Pair<Integer, Integer>> cooldowns;

    public Cooldown(int defaultCooldownTicks) {
        cooldowns = new HashMap<>();
        this.defaultCooldown = defaultCooldownTicks;
    }

    public void add(T target) {
        add(target, defaultCooldown);
    }
    public void add(T target, int ticks) {
        if(ticks>0) {
            if (cooldowns.containsKey(target)) {
                Bukkit.getScheduler().cancelTask(cooldowns.get(target).getKey());
            }
            cooldowns.put(target, new Pair<>(
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () -> {
                        cooldowns.remove(target);
                    }, ticks), Bukkit.getCurrentTick() + ticks
            ));
        }
    }
    public boolean isOffCooldown(T target) {
        return !cooldowns.containsKey(target);
    }
    public boolean isOnCooldown(T target) {
        return cooldowns.containsKey(target);
    }
    public int getTicksLeft(T target) {
        if(cooldowns.containsKey(target)) {
            return cooldowns.get(target).getValue() - Bukkit.getCurrentTick();
        } else {
            return -1;
        }
    }
    public boolean isOffCooldownAndAdd(T target) {
        boolean offcd = !cooldowns.containsKey(target);
        if(offcd) add(target);
        return offcd;
    }
}
