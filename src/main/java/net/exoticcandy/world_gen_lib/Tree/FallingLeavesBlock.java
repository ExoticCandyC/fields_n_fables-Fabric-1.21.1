package net.exoticcandy.world_gen_lib.Tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FallingLeavesBlock extends LeavesBlock {
    public static final MapCodec<FallingLeavesBlock> CODEC = createCodec(FallingLeavesBlock::new);

    public MapCodec<FallingLeavesBlock> getCodec() {
        return CODEC;
    }

    public SimpleParticleType FALLING_PARTICLE = null;

    protected FallingLeavesBlock(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    public FallingLeavesBlock(SimpleParticleType fallingLeavesParticle, AbstractBlock.Settings settings)
    {
        super(settings);
        FALLING_PARTICLE = fallingLeavesParticle;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(FALLING_PARTICLE != null) {
            super.randomDisplayTick(state, world, pos, random);
            if (random.nextInt(10) == 0) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                    ParticleUtil.spawnParticle(world, pos, random, FALLING_PARTICLE);
                }
            }
        }
    }
}
