package org.sgx.mojnovymod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import org.sgx.mojnovymod.item.ModItems;

public class TeaCropBlock extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 5);

    public TeaCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.TEA_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
}