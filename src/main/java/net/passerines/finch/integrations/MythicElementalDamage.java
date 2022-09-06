package net.passerines.finch.integrations;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.skills.SkillMechanic;
import net.passerines.finch.events.ElementalDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class MythicElementalDamage extends SkillMechanic implements ITargetedEntitySkill {

    protected final ElementalDamageEvent.Element type;
    protected final int amount;

    public MythicElementalDamage(MythicLineConfig config) {
        super(MythicBukkit.inst().getSkillManager(), config.getLine(), config);
        type = ElementalDamageEvent.Element.valueOf(config.getString(new String[]{"type", "t", "element", "e"}, "NEUTRAL"));
        amount = config.getInteger(new String[]{"amount", "a", "damage", "d"}, 0);
    }


    @Override
    public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        Entity caster = BukkitAdapter.adapt(data.getCaster().getEntity());
        if (bukkitTarget != null) {
            new ElementalDamageEvent(caster, bukkitTarget, type, amount).apply();
        }
        return SkillResult.SUCCESS;
    }
}
