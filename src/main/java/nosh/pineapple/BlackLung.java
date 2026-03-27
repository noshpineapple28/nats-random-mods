package nosh.pineapple;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BlackLung extends MobEffect {

    protected BlackLung() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        if (duration % 120 == 60)
            // 20 % chance each time this occurs
            return (Math.random() * 10 < 2);
        return false;
    }

    @Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		if (entity instanceof Player) {
			DamageSource damageSource = new DamageSource(
					level.registryAccess()
							.lookupOrThrow(Registries.DAMAGE_TYPE)
							.get(Natsdrunkcraft.BLACK_LUNG_DAMAGE.identifier()).get()
			);
            ((Player) entity).hurtServer(level, damageSource, 1.0f);
		}

		return super.applyEffectTick(level, entity, amplifier);
	}
}
