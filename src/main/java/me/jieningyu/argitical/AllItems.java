package me.jieningyu.argitical;

import me.jieningyu.argitical.generator.assets.ItemModelGenerator;
import me.jieningyu.argitical.generator.assets.NumberModelGenerator;
import me.jieningyu.argitical.generator.data.recipes.DivideRecipeGenerator;
import me.jieningyu.argitical.generator.data.recipes.MinusRecipeGenerator;
import me.jieningyu.argitical.generator.data.recipes.MultiplyRecipeGenerator;
import me.jieningyu.argitical.generator.data.recipes.PlusRecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllItems {
	public static Item PLUS = new Item(new Item.Settings().group(Argitical.ITEM_GROUP));
	public static Item MINUS = new Item(new Item.Settings().group(Argitical.ITEM_GROUP));
	public static Item MULTIPLY = new Item(new Item.Settings().group(Argitical.ITEM_GROUP));
	public static Item DIVIDE = new Item(new Item.Settings().group(Argitical.ITEM_GROUP));

	public static Item BUGGED = new Item(new Item.Settings());
	public static Item SMELLY = new Item(new Item.Settings());

	private static void newItem(String itemId, Item item, String itemNameEnUs, boolean generateModel) {
		Registry.register(Registry.ITEM, new Identifier(Argitical.NAMESPACE, itemId), item);
		if (generateModel) {
			ItemModelGenerator.generateModel(itemId);
		}
		LangHandler.addNameEnUs(itemId, "item", itemNameEnUs);
	}

	public static String numberItemId(int id) {
		return "number_" + NumberModelGenerator.positiveOrNegative(Argitical.toShort(id)) + "_" + String.valueOf(Math.abs(id));
	}

	private static void registerNumbers(short minCount, short maxCount) {
		for (short i = 0; i <= maxCount - minCount; i++) {
			Short num = Argitical.toShort(minCount + i);
			String id = numberItemId(num);
			newItem(id, new Item(new Item.Settings().group(Argitical.ITEM_GROUP)), String.valueOf(num), false);
			LangHandler.addNameZhCn(id, "item", String.valueOf(num));
			NumberModelGenerator.generate(num);

			PlusRecipeGenerator.generate(num, maxCount, minCount);
			MinusRecipeGenerator.generate(num, maxCount, minCount);
			MultiplyRecipeGenerator.generate(num, maxCount, minCount);
			DivideRecipeGenerator.generate(num, maxCount, minCount);
		}
	}

	public static void init(short minCount, short maxCount) {
		newItem("plus", PLUS, "Plus", true);
		newItem("minus", MINUS, "Minus", true);
		newItem("multiply", MULTIPLY, "Multiply", true);
		newItem("divide", DIVIDE, "Divide", true);

		LangHandler.addNameZhCn("plus", "item", "加");
		LangHandler.addNameZhCn("minus", "item", "减");
		LangHandler.addNameZhCn("multiply", "item", "乘");
		LangHandler.addNameZhCn("divide", "item", "除");

		newItem("bugged_number", BUGGED, "Bugged Number", true);
		newItem("smelly_item", SMELLY, "Smelly Item", true);
		LangHandler.addNameZhCn("bugged_number", "item", "错误数字");
		LangHandler.addNameZhCn("smelly_item", "item", "恶臭物品");

		registerNumbers(minCount, maxCount);
	}
}
