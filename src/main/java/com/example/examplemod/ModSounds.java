package com.example.examplemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, HaskyaMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MOMOI_AMBIENT = registerSoundEvent("entity.fuyu_momoi.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MOMOI_HURT = registerSoundEvent("entity.fuyu_momoi.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MOMOI_DEATH = registerSoundEvent("entity.fuyu_momoi.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MOMOI_HEAL = registerSoundEvent("entity.fuyu_momoi.heal");

    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MIDORI_AMBIENT = registerSoundEvent("entity.fuyu_midori.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MIDORI_HURT = registerSoundEvent("entity.fuyu_midori.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MIDORI_DEATH = registerSoundEvent("entity.fuyu_midori.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUYU_MIDORI_HEAL = registerSoundEvent("entity.fuyu_midori.heal");

    public static final DeferredHolder<SoundEvent, SoundEvent> MOMOI_AMBIENT = registerSoundEvent("entity.momoi.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> MOMOI_HURT = registerSoundEvent("entity.momoi.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> MOMOI_DEATH = registerSoundEvent("entity.momoi.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> MOMOI_HEAL = registerSoundEvent("entity.momoi.heal");

    public static final DeferredHolder<SoundEvent, SoundEvent> MIDORI_AMBIENT = registerSoundEvent("entity.midori.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIDORI_HURT = registerSoundEvent("entity.midori.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIDORI_DEATH = registerSoundEvent("entity.midori.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> MIDORI_HEAL = registerSoundEvent("entity.midori.heal");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(
                Identifier.fromNamespaceAndPath(HaskyaMod.MODID, name)
        ));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
