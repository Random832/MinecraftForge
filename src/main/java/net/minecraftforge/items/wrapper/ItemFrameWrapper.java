package net.minecraftforge.items.wrapper;

import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class ItemFrameWrapper implements IItemHandlerModifiable {
    ItemFrame entity;

    public ItemFrameWrapper(ItemFrame entity) {
        this.entity = entity;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        if(slot == 0) entity.setItem(stack);
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return slot != 0 ? ItemStack.EMPTY : entity.getItem();
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        if (slot != 0)
            return stack;

        ItemStack stackInSlot = entity.getItem();

        // slot limit is 1 so we can be a lot simpler than InvWrapper
        if (!stackInSlot.isEmpty())
            return stack;

        if(stack.getCount() > 1)
        {
            if (!simulate)
            {
                entity.setItem(stack.split(1));
                return stack;
            }
            else
            {
                return ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()-1);
            }
        } else {
            if(!simulate)
            {
                entity.setItem(stack);
            }
            return ItemStack.EMPTY;
        }
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(slot != 0 || amount == 0) {
            return ItemStack.EMPTY;
        }
        ItemStack stackInSlot = entity.getItem();
        if (stackInSlot.getCount() <= amount)
        {
            if(!simulate) {
                entity.setItem(ItemStack.EMPTY);
            }
            return stackInSlot;
        }
        else
        {
            // how did we get here?
            if (!simulate) {
                return stackInSlot.split(amount);
            } else {
                return ItemHandlerHelper.copyStackWithSize(stackInSlot, amount);
            }
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }
}
