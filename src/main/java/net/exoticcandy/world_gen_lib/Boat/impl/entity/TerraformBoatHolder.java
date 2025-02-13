package net.exoticcandy.world_gen_lib.Boat.impl.entity;

import net.exoticcandy.world_gen_lib.Boat.api.TerraformBoatType;
import net.exoticcandy.world_gen_lib.Boat.api.TerraformBoatTypeRegistry;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public interface TerraformBoatHolder {
	static final String BOAT_KEY = "TerraformBoat";

	TerraformBoatType getTerraformBoat();

	void setTerraformBoat(TerraformBoatType boat);

	default boolean hasValidTerraformBoat() {
		return this.getTerraformBoat() != null;
	}

	default void readTerraformBoatFromNbt(NbtCompound nbt) {
		Identifier id = Identifier.tryParse(nbt.getString(BOAT_KEY));
		if (id != null) {
			TerraformBoatType boat = TerraformBoatTypeRegistry.INSTANCE.get(id);
			if (boat != null) {
				this.setTerraformBoat(boat);
			}
		}
	}

	default void writeTerraformBoatToNbt(NbtCompound nbt) {
		Identifier boatId = TerraformBoatTypeRegistry.INSTANCE.getId(this.getTerraformBoat());
		if (boatId != null) {
			nbt.putString(BOAT_KEY, boatId.toString());
		}
	}

	default BoatEntity.Type getImpersonatedBoatType() {
		return this.getTerraformBoat().isRaft() ? BoatEntity.Type.BAMBOO : BoatEntity.Type.OAK;
	}
}
