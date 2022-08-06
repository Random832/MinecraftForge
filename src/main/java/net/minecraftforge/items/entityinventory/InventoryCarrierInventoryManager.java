package net.minecraftforge.items.entityinventory;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class InventoryCarrierInventoryManager extends LivingInventoryManager {
    private final InvWrapper inventory;

    public <T extends LivingEntity & InventoryCarrier> InventoryCarrierInventoryManager(T entity) {
        this(entity, entity);
    }

    public InventoryCarrierInventoryManager(LivingEntity living, InventoryCarrier carrier) {
        super(living);
        this.inventory = new InvWrapper(carrier.getInventory());
    }

    @Override
    public @Nullable IItemHandler getHandler(InventoryType group) {
        return group == InventoryType.INVENTORY ? inventory : super.getHandler(group);
    }
}
