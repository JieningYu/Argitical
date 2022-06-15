package me.jieningyu.argitical.generator.data.recipes;

import me.jieningyu.argitical.AllItems;
import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JKeys;
import net.devtech.arrp.json.recipe.JPattern;
import net.devtech.arrp.json.recipe.JResult;
import net.devtech.arrp.json.recipe.JShapedRecipe;
import net.minecraft.util.Identifier;

public class MinusRecipeGenerator {
	private static int getResult(Short firstNumber, Short secondNumber) {
		return firstNumber - secondNumber;
	}

	public static String numberItemId(int id) {
		return AllItems.numberItemId(id);
	}

	public static void generateOpposite(short n, short min, short max) {
		if (n == 0) {
			return;
		}
		short opposed = (short) -n;
		if (opposed < min || opposed > max) {
			return;
		}
		JKeys key = new JKeys()
				.key("A", JIngredient.ofItem(AllItems.MINUS))
				.key("B", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, numberItemId(n))));
		Argitical.RECIPES.addRecipe(
				new Identifier(Argitical.NAMESPACE,
						"opposite/" + numberItemId(n)),
				new JShapedRecipe(
						new JResult(new Identifier(Argitical.NAMESPACE, numberItemId(opposed))),
						new JPattern("AB"), key));
	}

	public static void generate(Short numberId, Short maxCount, Short minCount) {
		Short max = Argitical.toShort(numberId - minCount);
		Short min = Argitical.toShort(numberId - maxCount);
		if (Argitical.debugMode) {
			Argitical.LOGGER.info("Generating Minus Recipe for " + String.valueOf(numberId));
		}
		generateOpposite(numberId, minCount, maxCount);
		for (short i = min; i <= max; i++) {
			if (i < minCount || i > maxCount) {
				continue;
			}
			int result = getResult(numberId, i);
			String idA = numberItemId(numberId);
			String idB = numberItemId(i);
			JKeys key = new JKeys()
					.key("A", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idA)))
					.key("B", JIngredient.ofItem(AllItems.MINUS))
					.key("C", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idB)));
			Argitical.RECIPES.addRecipe(
					new Identifier(Argitical.NAMESPACE,
							"minus/" + numberItemId(numberId) + "_minus_" + numberItemId(i)),
					new JShapedRecipe(
							new JResult(new Identifier(Argitical.NAMESPACE, numberItemId(result))),
							new JPattern("ABC"), key));
		}
	}
}
