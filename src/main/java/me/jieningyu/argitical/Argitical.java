package me.jieningyu.argitical;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;

public class Argitical implements ModInitializer {

	public static Short maxCount = 99;
	public static Short minCount = -99;

	public static final Logger LOGGER = LoggerFactory.getLogger("Argitical");
	public static final String NAMESPACE = "argitical";
	public static final RuntimeResourcePack ASSETS = RuntimeResourcePack.create(new Identifier(NAMESPACE + "assets"));
	public static final RuntimeResourcePack RECIPES = RuntimeResourcePack.create(new Identifier(NAMESPACE + "data"));

	public static final boolean debugMode = true;
	public static boolean isCreateInstalled = false;
	public static boolean enableVanillaCrafting = true;

	public static ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "item_group"))
			.icon(() -> new ItemStack(AllItems.PLUS)).build();

	public static Short toShort(int input){
		return(Short.valueOf(String.valueOf(input)));
	}

	private static void defaultInit() {
		LangHandler.addNameEnUs("item_group", "itemGroup", "Argitical");
		LangHandler.addNameZhCn("item_group", "itemGroup", "数阵工艺");
		if (maxCount > 99) {
			maxCount = 99;
			LOGGER.warn("Argitical's maxCount is > 99, fixed!");
		}
		if (minCount < -99) {
			minCount = -99;
			LOGGER.warn("Argitical's minCount is < 99, fixed!");
		}
		if (FabricLoader.getInstance().isModLoaded("create")) {
			isCreateInstalled = true;
	//		enableVanillaCrafting = false;
		}
	}

	@Override
	public void onInitialize() {
		defaultInit();
		AllItems.init(minCount, maxCount);
		LangHandler.goLang();
		RRPCallback.BEFORE_VANILLA.register(q -> q.add(ASSETS));
		RRPCallback.AFTER_VANILLA.register(q -> q.add(RECIPES));
	}
}
