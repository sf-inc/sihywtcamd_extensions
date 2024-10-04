package com.github.charlyb01.sihywtcamd_extensions.config;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "sihywtcamd_extensions")
public class ModConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("skeletons")
    @ConfigEntry.Gui.TransitiveObject
    public SkeletonsConfig skeletons = new SkeletonsConfig();

    @ConfigEntry.Category("arthropods")
    @ConfigEntry.Gui.TransitiveObject
    public ArthropodsConfig arthropods = new ArthropodsConfig();

    @ConfigEntry.Category("cosmetics")
    @ConfigEntry.Gui.TransitiveObject
    public CosmeticsConfig cosmetics = new CosmeticsConfig();

    public static ModConfig get() {
        if (!SihywtcamdExtensions.areConfigsInit) {
            AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
            SihywtcamdExtensions.areConfigsInit = true;
        }
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
