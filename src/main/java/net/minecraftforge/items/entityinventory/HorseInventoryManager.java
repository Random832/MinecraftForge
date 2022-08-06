package net.minecraftforge.items.entityinventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.Nullable;

public class HorseInventoryManager extends LivingInventoryManager {
    AbstractHorse entity;
    private IItemHandler horse;
    @Nullable private IItemHandler chest;

    public HorseInventoryManager(AbstractHorse entity, Container horseInventory) {
        super(entity);
        this.entity = entity;
        InvWrapper fullWrapper = new InvWrapper(horseInventory);
        horse = new RangedWrapper(fullWrapper, 0, 2);
        // TODO do we want a slot to access the chest itself?
        // It's doable but complicated.
        if(fullWrapper.getSlots() > 2)
            chest = new RangedWrapper(fullWrapper, 2, fullWrapper.getSlots());
        else
            chest = null;
    }

    @Override
    public @Nullable IItemHandler getHandler(InventoryType group) {
        if(group == InventoryType.HORSE) return horse;
        else if(group == InventoryType.INVENTORY) return chest;
        else return super.getHandler(group);
        // TODO check to see if standard armor/hand slots are valid for horses
    }
}
