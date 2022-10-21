package net.passerines.finch;

import net.passerines.finch.cmds.*;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.HealthDisplay;
import net.passerines.finch.events.NaturalHealthRegen;
import net.passerines.finch.events.handler.DamageDisplayer;
import net.passerines.finch.events.handler.DamageHandler;
import net.passerines.finch.events.handler.EquipmentChangeHandler;
import net.passerines.finch.integrations.MythicMobsBridge;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.reforge.OpenReforgeMenu;
import net.passerines.finch.trinkets.OpenTrinketMenu;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Util;
import org.bukkit.plugin.java.JavaPlugin;

public class FinchElementalDamage extends JavaPlugin {

    @Override
    public void onLoad() {
        Util.log("&bFinchElementalDamage is now loading!");
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        new DamageDisplayer();
        new EntityMap();
        new PlayerMap();
        new GetPlayersCmd();
        new ReforgeCommand();
        new GetEffectiveHealthCmd();
        new NaturalHealthRegen();
        new HealthDisplay();
        new FinchAttributesCmd();
        new ItemGiveCommand();
        ItemManager.reload();
        new DamageHandler();
        new EquipmentChangeHandler();
        new MythicMobsBridge();
        new OpenTrinketMenu();
        new OpenReforgeMenu();
        new DebugMessages();
    }

    public static FinchElementalDamage inst() {
        return FinchElementalDamage.getPlugin(FinchElementalDamage.class);
    }
}
