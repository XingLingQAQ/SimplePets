package simplepets.brainsynder.nms.entity;

import lib.brainsynder.json.JsonObject;
import lib.brainsynder.nbt.StorageTagCompound;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import simplepets.brainsynder.api.entity.misc.ITameable;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.utils.PetDataAccess;

import java.util.Optional;
import java.util.UUID;

public class EntityTameablePet extends EntityAgeablePet implements ITameable {
    private static final EntityDataAccessor<Byte> TAMEABLE_FLAGS = SynchedEntityData.defineId(EntityTameablePet.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityTameablePet.class, EntityDataSerializers.OPTIONAL_UUID);

    public EntityTameablePet(EntityType<? extends Mob> entitytypes, PetType type, PetUser user) {
        super(entitytypes, type, user);
    }

    @Override
    public void fetchPetData(JsonObject data) {
        super.fetchPetData(data);
        data.add("tamed", isTamed());
        data.add("sitting", isSitting());
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(TAMEABLE_FLAGS, (byte) 0);
        dataAccess.define(OWNER_UUID, Optional.empty());
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound object = super.asCompound();
        object.setBoolean("tamed", isTamed());
        object.setBoolean("sitting", isSitting());
        return object;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("tamed")) setTamed(object.getBoolean("tamed"));
        if (object.hasKey("sitting")) setSitting(object.getBoolean("sitting"));
        super.applyCompound(object);
    }

    @Override
    public boolean isTamed() {
        return (this.entityData.get(TAMEABLE_FLAGS) & 4) != 0;
    }

    @Override
    public void setTamed(boolean flag) {
        byte i = this.entityData.get(TAMEABLE_FLAGS);
        if (flag) {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (i | 4));
        } else {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (i & -5));
        }
    }

    @Override
    public boolean isSitting() {
        return (this.entityData.get(TAMEABLE_FLAGS) & 1) != 0;
    }

    @Override
    public void setSitting(boolean flag) {
        byte i = this.entityData.get(TAMEABLE_FLAGS);
        if (flag) {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (i | 1));
        } else {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (i & -2));
        }
    }
}
