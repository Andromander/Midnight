package com.mushroom.midnight.common.registry;

import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.common.entity.EntityRift;
import com.mushroom.midnight.common.entity.creature.EntityRifter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = Midnight.MODID)
public class ModEntities {
    private static int currentEntityId;

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().registerAll(
                EntityEntryBuilder.create()
                        .entity(EntityRift.class)
                        .factory(EntityRift::new)
                        .id(new ResourceLocation(Midnight.MODID, "rift"), currentEntityId++)
                        .name(Midnight.MODID + ".rift")
                        .tracker(1024, 20, false)
                        .build(),
                EntityEntryBuilder.create()
                        .entity(EntityRifter.class)
                        .factory(EntityRifter::new)
                        .id(new ResourceLocation(Midnight.MODID, "rifter"), currentEntityId++)
                        .name(Midnight.MODID + ".rifter")
                        .tracker(80, 3, true)
                        .egg(0x384740, 0x5E8C6C)
                        .build()
        );
    }
}
