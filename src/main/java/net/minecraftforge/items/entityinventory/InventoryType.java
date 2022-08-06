package net.minecraftforge.items.entityinventory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InventoryType {
    private static final Map<String, InventoryType> actions = new ConcurrentHashMap<>();

    /**
     * Returns all registered actions.
     * This collection can be kept around, and will update itself in response to changes to the map.
     * See {@link ConcurrentHashMap#values()} for details.
     */
    public static Collection<InventoryType> getGroups()
    {
        return Collections.unmodifiableCollection(actions.values());
    }

    /**
     * Gets or creates a new ToolAction for the given name.
     */
    public static InventoryType get(String name)
    {
        return actions.computeIfAbsent(name, InventoryType::new);
    }

    /**
     * Returns the name of this tool action
     */
    public String name()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "ToolAction[" + name + "]";
    }

    private final String name;

    /**
     * Use {@link #get(String)} to get or create a ToolAction
     */
    private InventoryType(String name)
    {
        this.name = name;
    }

    /** The main or general-purpose inventory of the entity. Chest contents for horses. */
    static final InventoryType INVENTORY = get("inventory");
    /** Main hand, off hand */
    static final InventoryType HANDS = get("hands");
    /** Feet, legs, chest, head */
    static final InventoryType ARMOR = get("armor");
    /** Ender chest for players */
    static final InventoryType ENDER_CHEST = get("ender_chest");
    /** Saddle, Armor/Carpet, TODO Chest itself. */
    static final InventoryType HORSE = get("horse");
}
