package net.exoticcandy.fields_n_fables;

import net.exoticcandy.fields_n_fables.init.BackPort_1_21_5;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;

public class FieldsFablesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //BlockRenderLayerMap.INSTANCE.putBlock(RenderLayer.getCutout(), BlockInit.ECHO_SAPLING);

        BlockInit.load_client_side();
        BackPort_1_21_5.load_client_side();
        //TerraformBoatClientHelper.registerModelLayers(BlockInit.ECHO_BOAT_ID, false);
    }
}
