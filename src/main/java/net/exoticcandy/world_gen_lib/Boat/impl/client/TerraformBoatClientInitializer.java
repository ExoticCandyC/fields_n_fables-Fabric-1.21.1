package net.exoticcandy.world_gen_lib.Boat.impl.client;

import net.exoticcandy.world_gen_lib.Boat.impl.TerraformBoatInitializer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class TerraformBoatClientInitializer implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(TerraformBoatInitializer.BOAT, context -> new TerraformBoatEntityRenderer(context, false));
		EntityRendererRegistry.register(TerraformBoatInitializer.CHEST_BOAT, context -> new TerraformBoatEntityRenderer(context, true));
	}
}
