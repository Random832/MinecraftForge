package net.minecraftforge.items.entityinventory;

import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public interface IEntityInventoryManager {
    @Nullable
    IItemHandler getHandler(InventoryType group);
}
