package simplepets.brainsynder.menu.menuItems;

import org.bukkit.Material;
import simple.brainsynder.api.ItemMaker;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.hostile.IEntityVindicatorPet;
import simplepets.brainsynder.menu.menuItems.base.MenuItemAbstract;
import simplepets.brainsynder.pet.PetDefault;

public class Johnny extends MenuItemAbstract {
    private ItemMaker item = new ItemMaker(Material.IRON_AXE);
    public Johnny(PetDefault type) {
        super(type);
    }

    public Johnny(PetDefault type, IEntityPet entityPet) {
        super(type, entityPet);
    }

    @Override
    public ItemMaker getItem() {
        if (entityPet instanceof IEntityVindicatorPet) {
            IEntityVindicatorPet var = (IEntityVindicatorPet) entityPet;
            item.setName("&6Johnny: &e" + var.isJohnny());
        }
        return item;
    }

    @Override
    public void onLeftClick() {
        if (entityPet instanceof IEntityVindicatorPet) {
            IEntityVindicatorPet pet = (IEntityVindicatorPet) entityPet;
            if (pet.isJohnny()) {
                pet.setJohnny(false);
            } else {
                pet.setJohnny(true);
            }
        }
    }
}