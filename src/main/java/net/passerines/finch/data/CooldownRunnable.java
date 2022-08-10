package net.passerines.finch.data;

import net.passerines.finch.FinchElementalDamage;
import org.bukkit.Bukkit;

import java.util.HashMap;

//Same as Cooldown, but will run a task when cooldown ends
public class CooldownRunnable<T> {

    private final int defaultCooldown;
    private final HashMap<T, Pair<Integer, Integer>> cooldowns;

    public CooldownRunnable(int defaultCooldownTicks) {
        cooldowns = new HashMap<>();
        this.defaultCooldown = defaultCooldownTicks;
    }

    public void add(T target) {
        add(target, defaultCooldown);
    }
    public void add(T target, int ticks) {
        add(target, ticks, null);
    }
    public void add(T target, Runnable onEnd) {
        add(target, defaultCooldown, onEnd);
    }
    public void add(T target, int ticks, Runnable onEnd) {
        if(cooldowns.containsKey(target)) {
            Bukkit.getScheduler().cancelTask(cooldowns.get(target).getKey());
        }
        cooldowns.put(target, new Pair<>(
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), () -> {
                    cooldowns.remove(target);
                    if(onEnd!=null) onEnd.run();
                }, ticks), Bukkit.getCurrentTick() + ticks
        ));
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

    public void clear() {
        for(T target : cooldowns.keySet()) {
            if (cooldowns.containsKey(target)) {
                Bukkit.getScheduler().cancelTask(cooldowns.get(target).getKey());
            }
        }
    }
}
