package net.minecraftforge.items.entityinventory;

import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.ItemFrameWrapper;
import org.jetbrains.annotations.Nullable;

public class SimpleInventoryManager implements IEntityInventoryManager {
    final IItemHandler handler;

    public SimpleInventoryManager(ContainerEntity entity) {
        this.handler = new InvWrapper(entity);
    }

    public SimpleInventoryManager(ItemFrame entity) {
        this.handler = new ItemFrameWrapper(entity);
    }

    @Nullable
    @Override
    public IItemHandler getHandler(InventoryType group) {
        return group == InventoryType.INVENTORY ? handler : null;
    }
}