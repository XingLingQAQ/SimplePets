package simplepets.brainsynder.nms.v1_12_R1.entities.list;

import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.World;
import simple.brainsynder.nbt.StorageTagCompound;
import simplepets.brainsynder.api.Size;
import simplepets.brainsynder.api.entity.hostile.IEntityZombieVillagerPet;
import simplepets.brainsynder.api.pet.IPet;
import simplepets.brainsynder.nms.v1_12_R1.entities.AgeableEntityPet;
import simplepets.brainsynder.wrapper.ProfessionWrapper;

/**
 * {@link net.minecraft.server.v1_12_R1.EntityZombieVillager}
 */
@Size(width = 0.6F, length = 1.8F)
public class EntityZombieVillagerPet extends AgeableEntityPet implements IEntityZombieVillagerPet {
    private static final DataWatcherObject<Integer> VILLAGER_TYPE;
    private static final DataWatcherObject<Boolean> ARMS_RAISED;
    private static final DataWatcherObject<Boolean> CONVERTING;
    private static final DataWatcherObject<Integer> PROFESSION;

    static {
        VILLAGER_TYPE = DataWatcher.a(EntityZombieVillagerPet.class, DataWatcherRegistry.b);
        ARMS_RAISED = DataWatcher.a(EntityZombieVillagerPet.class, DataWatcherRegistry.h);
        CONVERTING = DataWatcher.a(EntityZombieVillagerPet.class, DataWatcherRegistry.h);
        PROFESSION = DataWatcher.a(EntityZombieVillagerPet.class, DataWatcherRegistry.b);
    }

    public EntityZombieVillagerPet(World world) {
        super(world);
    }
    public EntityZombieVillagerPet(World world, IPet pet) {
        super(world, pet);
    }

    @Override
    protected void registerDatawatchers() {
        super.registerDatawatchers();
        datawatcher.register(VILLAGER_TYPE, 0);
        datawatcher.register(ARMS_RAISED, false);
        datawatcher.register(CONVERTING, false);
        datawatcher.register(PROFESSION, 0);
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound object = super.asCompound();
        object.setString("profession", getProfession().name());
        object.setBoolean("shaking", isShaking());
        object.setBoolean("arms_raised", isArmsRaised());
        return object;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("profession")) setProfession(ProfessionWrapper.getProfession(object.getString("profession")));
        if (object.hasKey("shaking")) setShaking(object.getBoolean("shaking"));
        if (object.hasKey("arms_raised")) setArmsRaised(object.getBoolean("arms_raised"));
        super.applyCompound(object);
    }

    @Override
    public boolean isShaking() {
        return datawatcher.get(CONVERTING);
    }

    @Override
    public void setShaking(boolean value) {
        datawatcher.set(CONVERTING, value);
    }

    @Override
    public ProfessionWrapper getProfession() {
        return ProfessionWrapper.getById(datawatcher.get(PROFESSION));
    }

    @Override
    public void setProfession(ProfessionWrapper wrapper) {
        this.datawatcher.set(PROFESSION, wrapper.ordinal());
    }

    @Override
    public boolean isArmsRaised() {
        return datawatcher.get(ARMS_RAISED);
    }

    @Override
    public void setArmsRaised(boolean flag) {
        datawatcher.set(ARMS_RAISED, flag);
    }
}