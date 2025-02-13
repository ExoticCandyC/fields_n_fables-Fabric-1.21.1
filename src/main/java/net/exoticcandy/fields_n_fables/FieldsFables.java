package net.exoticcandy.fields_n_fables;

import net.exoticcandy.fields_n_fables.data.TreeDecorators.WisteriaVineDecorator;
import net.exoticcandy.fields_n_fables.init.BackPort_1_21_5;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldsFables implements ModInitializer {
	public static final String MOD_ID = "fields_n_fables";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		WisteriaVineDecorator.load();

		//LOGGER.info("Hello Fabric world!");
		BackPort_1_21_5.load_generic();
		BlockInit.load_generic();
	}
}