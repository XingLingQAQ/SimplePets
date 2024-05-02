package simplepets.brainsynder.nms.entity.list;

import lib.brainsynder.nbt.StorageTagCompound;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityBeePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityAgeablePet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.animal.Bee}
 */
public class EntityBeePet extends EntityAgeablePet implements IEntityBeePet {
    private static final EntityDataAccessor<Byte> FLAGS = SynchedEntityData.defineId(EntityBeePet.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> ANGER = SynchedEntityData.defineId(EntityBeePet.class, EntityDataSerializers.INT);

    public EntityBeePet(PetType type, PetUser user) {
        super(EntityType.BEE, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(FLAGS, (byte) 4);
        dataAccess.define(ANGER, 0);
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound object = super.asCompound();
        object.setBoolean("angry", isAngry());
        object.setBoolean("nectar", hasNectar());
        object.setBoolean("stinger", hasStung());
        object.setBoolean("flipped", isFlipped());
        return object;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("angry")) setAngry(object.getBoolean("angry"));
        if (object.hasKey("nectar")) setHasNectar(object.getBoolean("nectar"));
        if (object.hasKey("stinger")) setHasStung(object.getBoolean("stinger"));
        if (object.hasKey("flipped")) setFlipped(object.getBoolean("flipped"));
        super.applyCompound(object);
    }

    @Override
    public boolean isAngry() {
        return entityData.get(ANGER) > 0;
    }

    @Override
    public void setAngry(boolean angry) {
        entityData.set(ANGER, (angry) ? 25562256 : 0);
    }

    @Override
    public void setSpecialFlag(int flag, boolean value) {
        byte flagByte = entityData.get(FLAGS);
        if (value) {
            flagByte = (byte)(flagByte | flag);
        } else {
            flagByte = (byte)(flagByte & ~flag);
        }

        if (flagByte != entityData.get(FLAGS)) this.entityData.set(FLAGS, flagByte);
    }

    @Override
    public boolean getSpecialFlag(int flag) {
        return (this.entityData.get(FLAGS) & flag) != 0;
    }
}