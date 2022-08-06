package net.minecraftforge.items.entityinventory;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class VanillaInventoryCodeHooks {

    /**
     * Returns an item handler for either a block entity, a composter-like block, or an entity at a position.
     *
     * The result should not be cached because:
     * - it may be from an entity [which may move away from the position and may not invalidate capabilities correctly]
     * - it may be from a composter, whose handler is only usable for a single operation [and does not support invalidation]
     */
    public static <T> Optional<Pair<T, Object>> getHandler(Level level, double x, double y, double z, Capability<T> cap, @Nullable Direction side) {
        int i = Mth.floor(x);
        int j = Mth.floor(y);
        int k = Mth.floor(z);
        BlockPos blockpos = new BlockPos(i, j, k);
        BlockState state = level.getBlockState(blockpos);

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && state.getBlock() instanceof WorldlyContainerHolder composter) {
            //noinspection unchecked
            return Optional.of(Pair.of((T) new SidedInvWrapper(composter.getContainer(state, level, blockpos), side), composter));
        }

        if (state.hasBlockEntity()) {
            BlockEntity blockEntity = level.getBlockEntity(blockpos);
            if (blockEntity != null) {
                return blockEntity.getCapability(cap, side).map(handler -> ImmutablePair.of(handler, blockEntity));
            }
        }

        List<Entity> list = level.getEntities((Entity) null, new AABB(x - .5, y - .5, z - .5, x + .5, y + .5, z + .5), e -> e.getCapability(cap, side).isPresent());
        if (!list.isEmpty()) {
            final Entity entity = list.get(level.random.nextInt(list.size()));
            return entity.getCapability(cap, side).map(handler -> ImmutablePair.of(handler, entity));
        }

        return Optional.empty();
    }
}