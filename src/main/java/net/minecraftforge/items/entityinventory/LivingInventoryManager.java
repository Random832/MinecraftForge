package net.minecraftforge.items.entityinventory;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import org.jetbrains.annotations.Nullable;

public class LivingInventoryManager implements IEntityInventoryManager {
    final EntityHandsInvWrapper hands;
    final EntityArmorInvWrapper armor;

    public LivingInventoryManager(LivingEntity entity) {
        hands = new EntityHandsInvWrapper(entity);
        armor = new EntityArmorInvWrapper(entity);
    }

    @Nullable
    @Override
    public IItemHandler getHandler(InventoryType group) {
        if (group == InventoryType.ARMOR) return armor;
        else if (group == InventoryType.HANDS) return hands;
        else return null;
    }
}
