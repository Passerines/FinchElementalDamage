package net.passerines.finch.aItems.glaive;

import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchGlaives;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class IronGlaive extends FinchGlaives {


    public IronGlaive() {
        super("IronGlaive");
        this.attack = 10;
        this.defense = 5;
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        displayName = Chat.formatC("&fIron Insect Glaive");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
    }

    @Override
    public void ifInsect(PlayerInteractEvent event) {
        super.ifInsect(event);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(1);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        //
        return writeId(item);
    }
}
