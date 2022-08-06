package net.minecraftforge.items.entityinventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.Nullable;

public class PlayerInventoryManager extends LivingInventoryManager {
    final IItemHandler mainInventory;
    final IItemHandler armor;
    final IItemHandler enderChest;

    public PlayerInventoryManager(Player player) {
        super(player);
        Inventory inventory = player.getInventory();
        mainInventory = new PlayerMainInvWrapper(inventory);
        armor = new PlayerArmorInvWrapper(inventory);
        enderChest = new InvWrapper(player.getEnderChestInventory());
    }

    @Nullable
    @Override
    public IItemHandler getHandler(InventoryType group) {
        if(group == InventoryType.INVENTORY) return mainInventory;
        else if(group == InventoryType.ARMOR) return armor;
        else if(group == InventoryType.ENDER_CHEST) return enderChest;
        else return super.getHandler(group);
    }
}
