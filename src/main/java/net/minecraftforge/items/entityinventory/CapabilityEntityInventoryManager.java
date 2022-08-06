package net.minecraftforge.items.entityinventory;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.function.Function;

public class CapabilityEntityInventoryManager {
    public static Capability<IEntityInventoryManager> ENTITY_INVENTORY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    static ResourceLocation KEY = new ResourceLocation("forge", "entity_item_manager");

    static void registerCapabilities(RegisterCapabilitiesEvent e) {
        e.register(IEntityInventoryManager.class);
    }

    public static <T extends Entity> LazyOptional<IEntityInventoryManager> create(T entity, Function<T, IEntityInventoryManager> factory)
    {
        return LazyOptional.of(() -> factory.apply(entity));
    }
}
