package net.exoticcandy.world_gen_lib.Vine;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.session.telemetry.TelemetrySender;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class EC_Vine
{
    public Block VINE_STEM;
    public Block VINE_PLANT;
    public String MOD_ID;
    public String VineName;
    public boolean flammable;
    public boolean side_vine;

    public class EC_Vine_Plant extends AbstractPlantStemBlock
    {
        public MapCodec<EC_Vine_Plant> CODEC = createCodec(EC_Vine_Plant::new);
        public VoxelShape SHAPE = Block.createCuboidShape((double)4.0F, (double)9.0F, (double)4.0F, (double)12.0F, (double)16.0F, (double)12.0F);

        public MapCodec<EC_Vine_Plant> getCodec() {
            return CODEC;
        }

        public EC_Vine_Plant(AbstractBlock.Settings settings)
        {
            super(settings, Direction.DOWN,
                    Block.createCuboidShape((double)4.0F, (double)9.0F, (double)4.0F, (double)12.0F, (double)16.0F, (double)12.0F),
                    false, 0.1);
        }

        public EC_Vine_Plant(Direction direction, AbstractBlock.Settings settings)
        {
            super(settings, direction,
                    Block.createCuboidShape((double)4.0F, (double)9.0F, (double)4.0F, (double)12.0F, (double)16.0F, (double)12.0F),
                    false, 0.1);
        }

        public EC_Vine_Plant(VoxelShape SHAPE, Direction direction, AbstractBlock.Settings settings)
        {
            super(settings, direction, SHAPE, false, 0.1);
        }

        public int getGrowthLength(Random random)
        {
            return VineLogic.getGrowthLength(random);
        }
        public Block getPlant()
        {
            return VINE_PLANT;
        }

        public boolean chooseStemState(BlockState state)
        {
            return VineLogic.isValidForWeepingStem(state);
        }
    }

    public class EC_Vine_Plant_Block extends AbstractPlantBlock
    {
        public MapCodec<EC_Vine_Plant_Block> CODEC = createCodec(EC_Vine_Plant_Block::new);
        public VoxelShape SHAPE = Block.createCuboidShape((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F);

        public MapCodec<EC_Vine_Plant_Block> getCodec() {
            return CODEC;
        }

        public EC_Vine_Plant_Block(AbstractBlock.Settings settings)
        {
            super(settings, Direction.DOWN,
                    Block.createCuboidShape((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F), false);
        }

        public EC_Vine_Plant_Block(Direction direction, AbstractBlock.Settings settings)
        {
            super(settings, direction,
                    Block.createCuboidShape((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F), false);
        }

        public EC_Vine_Plant_Block(VoxelShape SHAPE, Direction direction, AbstractBlock.Settings settings)
        {
            super(settings, direction, SHAPE, false);
        }

        public AbstractPlantStemBlock getStem() {
            return (AbstractPlantStemBlock)VINE_STEM;
        }
    }

    private Identifier id(String path)
    {
        return Identifier.of(MOD_ID, path);
    }

    private <T extends Block> T register(String name, T block)
    {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    private <T extends Item> T register_item(String name, T item)
    {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    private <T extends Block> T registerWithItem(String name, T block, Item.Settings settings)
    {
        T registered = register(name, block);
        register_item(name, new BlockItem(registered, settings));
        return registered;
    }

    private <T extends Block> T registerWithItem(String name, T block)
    {
        return registerWithItem(name, block, new Item.Settings());
    }

    public EC_Vine(String MOD_ID, String vine_ID, String VineName, Direction direction, boolean flammable, boolean isSideVine, MapColor VineColor, BlockSoundGroup SoundGroup, int light_level)
    {
        this.MOD_ID = MOD_ID;
        this.VineName = VineName;
        this.flammable = flammable;
        this.side_vine = isSideVine;
        if(this.side_vine)
        {
            VINE_STEM = registerWithItem(vine_ID, new VineBlock(AbstractBlock.Settings.create().mapColor(VineColor).replaceable().noCollision().ticksRandomly().strength(0.2F).sounds(SoundGroup).burnable().luminance((state) -> light_level).pistonBehavior(PistonBehavior.DESTROY)));
        }
        else {
            if (direction == Direction.DOWN) {
                VINE_STEM = registerWithItem(vine_ID, new EC_Vine_Plant(Block.createCuboidShape((double) 4.0F, (double) 5.0F, (double) 4.0F, (double) 12.0F, (double) 16.0F, (double) 12.0F), direction, AbstractBlock.Settings.create().mapColor(VineColor).ticksRandomly().noCollision().breakInstantly().sounds(SoundGroup).luminance((state) -> light_level).pistonBehavior(PistonBehavior.DESTROY)));
                VINE_PLANT = register(vine_ID + "_plant", new EC_Vine_Plant_Block(Block.createCuboidShape((double) 4.0F, (double) 0.0F, (double) 4.0F, (double) 12.0F, (double) 16.0F, (double) 12.0F), direction, AbstractBlock.Settings.create().mapColor(VineColor).noCollision().breakInstantly().sounds(SoundGroup).luminance((state) -> light_level).pistonBehavior(PistonBehavior.DESTROY)));
            } else {
                VINE_STEM = registerWithItem(vine_ID, new EC_Vine_Plant(Block.createCuboidShape((double) 4.0F, (double) 0.0F, (double) 4.0F, (double) 12.0F, (double) 14.0F, (double) 12.0F), direction, AbstractBlock.Settings.create().mapColor(VineColor).ticksRandomly().noCollision().breakInstantly().sounds(SoundGroup).luminance((state) -> light_level).pistonBehavior(PistonBehavior.DESTROY)));
                VINE_PLANT = register(vine_ID + "_plant", new EC_Vine_Plant_Block(Block.createCuboidShape((double) 4.0F, (double) 0.0F, (double) 4.0F, (double) 12.0F, (double) 16.0F, (double) 12.0F), direction, AbstractBlock.Settings.create().mapColor(VineColor).noCollision().breakInstantly().sounds(SoundGroup).luminance((state) -> light_level).pistonBehavior(PistonBehavior.DESTROY)));
            }
        }
    }

    /* ********************  Load Functions  ******************** */
    public void loadGeneric()
    {
        if(this.flammable)
        {
            FlammableBlockRegistry.getDefaultInstance().add(VINE_STEM , 15, 60);
            if(!this.side_vine)
                FlammableBlockRegistry.getDefaultInstance().add(VINE_PLANT, 15, 60);
        }

        CompostingChanceRegistry.INSTANCE.add(VINE_STEM , 0.5f);
        if(!this.side_vine)
            CompostingChanceRegistry.INSTANCE.add(VINE_PLANT, 0.5f);
    }

    public void loadClient()
    {
        if(this.side_vine)
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), VINE_STEM);
        else
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), VINE_STEM, VINE_PLANT);
    }

    /* ***************  Data Generator Functions  *************** */
    public <T extends FabricBlockLootTableProvider> void datagen_loot_table(T inst)
    {
        if(this.side_vine)
            inst.addVinePlantDrop(VINE_STEM, VINE_STEM);
            //inst.addDrop(VINE_STEM, inst.dropsWithSilkTouchOrShears(VINE_STEM, ItemEntry.builder(VINE_STEM)));
        else
            inst.addVinePlantDrop(VINE_STEM, VINE_PLANT);
    }

    public void datagen_language(FabricLanguageProvider.TranslationBuilder translationBuilder)
    {
        if(this.side_vine)
            translationBuilder.add(VINE_STEM, VineName);
        else
        {
            translationBuilder.add(VINE_STEM, VineName);
            translationBuilder.add(VINE_PLANT, VineName);
        }
    }

    public void datagen_block_model(BlockStateModelGenerator blockStateModelGenerator)
    {
        if(this.side_vine)
        {
            blockStateModelGenerator.registerWallPlant(VINE_STEM);
        }
        else
        {
            blockStateModelGenerator.registerPlantPart(VINE_STEM, VINE_PLANT, BlockStateModelGenerator.TintType.NOT_TINTED);
            blockStateModelGenerator.registerItemModel(VINE_STEM, "_plant");
            blockStateModelGenerator.excludeFromSimpleItemModelGeneration(VINE_PLANT);
        }
    }
}
