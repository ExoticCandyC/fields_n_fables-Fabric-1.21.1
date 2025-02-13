package net.exoticcandy.fields_n_fables.mixin;


import com.terraformersmc.terraform.boat.impl.TerraformBoatInitializer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TerraformBoatInitializer.class)
public class ExampleMixin {

	// Shadow the private static final fields from the target class
	@Shadow @Final @Mutable
	private static Identifier BOAT_ID;

	@Shadow @Final @Mutable
	private static Identifier CHEST_BOAT_ID;

	// Inject into the static initializer (<clinit>) at the end ("TAIL")
	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void modifyIdentifiers(CallbackInfo ci) {
		BOAT_ID = Identifier.of("fields_n_fables", "boat");
		CHEST_BOAT_ID = Identifier.of("fields_n_fables", "chest_boat");
	}
}

/*
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}
*/