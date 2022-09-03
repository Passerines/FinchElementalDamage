package net.passerines.finch.events.handler;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ArrowHandler {
    ArrowHandler(Player player, Sound sound, int rotation, int damage){
        Location loc = player.getEyeLocation();
        Arrow arrow = (Arrow) loc.getWorld().spawnEntity(loc, EntityType.ARROW);
        arrow.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(rotation)));
        player.getWorld().playSound(player.getLocation(), sound, 1, 1);
        arrow.setShooter(player);
        arrow.setDamage(damage);
    }
}
