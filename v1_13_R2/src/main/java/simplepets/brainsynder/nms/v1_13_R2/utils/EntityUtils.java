package simplepets.brainsynder.nms.v1_13_R2.utils;

import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.IRegistry;
import net.minecraft.server.v1_13_R2.MinecraftKey;
import simplepets.brainsynder.wrapper.EntityWrapper;

public class EntityUtils {

    public static EntityTypes<?> getType (EntityWrapper wrapper) {
        return IRegistry.ENTITY_TYPE.get(new MinecraftKey(wrapper.getName().toLowerCase()));
    }
}
