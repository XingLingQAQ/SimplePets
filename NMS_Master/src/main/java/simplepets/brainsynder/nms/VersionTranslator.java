package simplepets.brainsynder.nms;

import lib.brainsynder.ServerVersion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.entity.CreatureSpawnEvent;

@Deprecated
public class VersionTranslator {
    public static final String ENTITY_DATA_MAP = "f";
    public static final String ENTITY_FACTORY_FIELD = "bm";

    public static void setItemSlot(ArmorStand stand, EquipmentSlot enumitemslot, ItemStack itemstack, boolean silent) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static boolean addEntity (Level level, Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static <T extends Entity> T getEntityHandle(org.bukkit.entity.Entity entity) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static <T extends Level> T getWorldHandle(World world) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static BlockState getBlockState(BlockData blockData) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static BlockData fromNMS(BlockState blockData) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static ItemStack toNMSStack(org.bukkit.inventory.ItemStack itemStack) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }

    public static org.bukkit.inventory.ItemStack toBukkit(ItemStack itemStack) {
        throw new UnsupportedOperationException ("Missing support for "+ ServerVersion.getVersion().name());
    }
}