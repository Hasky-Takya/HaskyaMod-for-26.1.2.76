package com.example.examplemod;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public final class ModToolMaterials {
    /**
     * Items in this tag can repair tools made from {@link #QINGHUISHI}.
     */
    public static final TagKey<Item> QINGHUISHI_TOOL_MATERIALS = ItemTags.create(
            Identifier.fromNamespaceAndPath(HaskyaMod.MODID, "qinghuishi_tool_materials")
    );

    /**
     * Qinghuishi currently uses diamond-level mining and combat properties.
     */
    public static final ToolMaterial QINGHUISHI = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            101,
            32.0F,
            3.0F,
            10,
            QINGHUISHI_TOOL_MATERIALS
    );

    private ModToolMaterials() {
    }
}
