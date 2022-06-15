package me.jieningyu.argitical.generator.data.recipes;

import me.jieningyu.argitical.AllItems;
import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JKeys;
import net.devtech.arrp.json.recipe.JPattern;
import net.devtech.arrp.json.recipe.JResult;
import net.devtech.arrp.json.recipe.JShapedRecipe;
import net.minecraft.util.Identifier;

public class PlusRecipeGenerator {
	private static int getResult(Short firstNumber, Short secondNumber) {
		return firstNumber + secondNumber;
	}

	public static String numberItemId(int id) {
		return AllItems.numberItemId(id);
	}

	public static void generate(Short numberId, Short maxCount, Short minCount) {
		Short min = Argitical.toShort(minCount - numberId);
		Short max = Argitical.toShort(maxCount - numberId);
		if (Argitical.debugMode) {
			Argitical.LOGGER.info("Generating Plus Recipe for " + String.valueOf(numberId));
		}
		for (short i = min; i <= max; i++) {
			if (i < minCount || i > maxCount) {
				continue;
			}
			int result = getResult(numberId, i);
			String idA = numberItemId(numberId);
			String idB = numberItemId(i);
			JKeys key = new JKeys()
					.key("A", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idA)))
					.key("B", JIngredient.ofItem(AllItems.PLUS))
					.key("C", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idB)));
			Argitical.RECIPES.addRecipe(
					new Identifier(Argitical.NAMESPACE,
							"plus/" + numberItemId(numberId) + "_plus_" + numberItemId(i)),
					new JShapedRecipe(
							new JResult(new Identifier(Argitical.NAMESPACE, numberItemId(result))),
							new JPattern("ABC"), key));
		}
	}
}
