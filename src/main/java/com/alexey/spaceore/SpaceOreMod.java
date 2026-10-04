package com.alexey.spaceore;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;

@Mod(SpaceOreMod.MOD_ID)
public class SpaceOreMod {
    public static final String MOD_ID = "spaceore";
    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredBlock<Block> COSMIC_ORE = BLOCKS.registerSimpleBlock("cosmic_ore",
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE).strength(4.0f, 9.0f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> COSMIC_BLOCK = BLOCKS.registerSimpleBlock("cosmic_block",
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(6.0f, 30.0f));

    public static final DeferredItem<BlockItem> COSMIC_ORE_ITEM = ITEMS.registerSimpleBlockItem(COSMIC_ORE);
    public static final DeferredItem<BlockItem> COSMIC_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(COSMIC_BLOCK);

    public static final DeferredItem<Item> COSMIC_RAW = ITEMS.registerSimpleItem("raw_cosmic_ore");
    public static final DeferredItem<Item> COSMIC_INGOT = ITEMS.registerSimpleItem("cosmic_ingot", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> COSMIC_SHARD = ITEMS.registerSimpleItem("cosmic_shard", new Item.Properties().fireResistant());

    public static final Tier COSMIC_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,
            10.0f,
            5.0f,
            18,
            () -> Ingredient.of(COSMIC_INGOT.get())
    );

    public static final DeferredItem<SwordItem> COSMIC_SWORD = ITEMS.register("cosmic_sword",
            () -> new SwordItem(COSMIC_TIER, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(COSMIC_TIER, 7, -2.4f))));
    public static final DeferredItem<PickaxeItem> COSMIC_PICKAXE = ITEMS.register("cosmic_pickaxe",
            () -> new PickaxeItem(COSMIC_TIER, new Item.Properties().fireResistant()));
    public static final DeferredItem<SwordItem> COSMIC_BLADE = ITEMS.register("cosmic_blade",
            () -> new SwordItem(COSMIC_TIER, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(COSMIC_TIER, 10, -2.8f))));
    public static final DeferredItem<Item> STAR_CORE = ITEMS.registerSimpleItem("star_core", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> COSMIC_CRYSTAL = ITEMS.registerSimpleItem("cosmic_crystal", new Item.Properties().fireResistant());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.spaceore.main"))
            .icon(() -> new ItemStack(COSMIC_INGOT.get()))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .displayItems((params, output) -> {
                output.accept(COSMIC_ORE_ITEM);
                output.accept(COSMIC_BLOCK_ITEM);
                output.accept(COSMIC_RAW);
                output.accept(COSMIC_INGOT);
                output.accept(COSMIC_SHARD);
                output.accept(COSMIC_CRYSTAL);
                output.accept(STAR_CORE);
                output.accept(COSMIC_SWORD);
                output.accept(COSMIC_PICKAXE);
                output.accept(COSMIC_BLADE);
            }).build());

    public SpaceOreMod(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
        LOGGER.info("Space Ore loaded: cosmic resources await in the End!");
    }
}
