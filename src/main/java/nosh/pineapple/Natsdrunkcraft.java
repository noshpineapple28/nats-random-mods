package nosh.pineapple;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Natsdrunkcraft implements ModInitializer {
	public static final String MOD_ID = "nats-drunk-craft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Holder<MobEffect> BLACK_LUNG =
			Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Natsdrunkcraft.MOD_ID, "black_lung"), new BlackLung());
    public static final ResourceKey<DamageType> BLACK_LUNG_DAMAGE =
			ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Natsdrunkcraft.MOD_ID, "black_lung"));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			// 10% chance to contract each time u mine coal ore
			if (state.getBlock().getName().toString().toLowerCase().contains("coal") && (Math.random() * 10 < 1))
			{
				// lasts 120 seconds, 20 tps
				MobEffectInstance blackLung = new MobEffectInstance(BLACK_LUNG, (20 * 120), 0, false, true, true);
				player.addEffect(blackLung);
			}
		});
		LOGGER.info("Hello Fabric world!");
	}
}
