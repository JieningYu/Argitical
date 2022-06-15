package me.jieningyu.argitical.generator.data.recipes;

import me.jieningyu.argitical.AllItems;
import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JKeys;
import net.devtech.arrp.json.recipe.JPattern;
import net.devtech.arrp.json.recipe.JResult;
import net.devtech.arrp.json.recipe.JShapedRecipe;
import net.minecraft.util.Identifier;

public class DivideRecipeGenerator {
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static int getResult(Short firstNumber, Short secondNumber) {
		double raw = firstNumber / secondNumber * 1.0;
		return (short) raw;
	}

	private static boolean isDivideAble(Short firstNumber, Short secondNumber) {
		if (secondNumber == 0) {
			return false;
		}
		if (firstNumber % secondNumber == 0) {
			return true;
		}
		return false;
	}

	public static String numberItemId(int id) {
		return AllItems.numberItemId(id);
	}

	private static Short getMin(Short n, Short min) {
		if (n == 0) {
			return 0;
		}
		return min;
	}

	private static Short getMax(Short n, Short max) {
		if (n == 0) {
			return 0;
		}
		return max;
	}

	private static void generateBuggedRecipe(Short n) {
		JKeys key = new JKeys()
				.key("A", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, numberItemId(n))))
				.key("B", JIngredient.ofItem(AllItems.DIVIDE))
				.key("C", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, numberItemId(0))));
		Argitical.RECIPES.addRecipe(
				new Identifier(Argitical.NAMESPACE,
						"divide/" + numberItemId(n) + "_divide_" + "bugged_number"),
				new JShapedRecipe(
						new JResult(AllItems.BUGGED),
						new JPattern("ABC"), key));
	}

	public static void generate(Short numberId, Short maxCount, Short minCount) {
		Short min = getMin(numberId, minCount);
		Short max = getMax(numberId, maxCount);
		if (min > max) {
			Short temp = max;
			max = min;
			min = temp;
		}
		if (Argitical.debugMode) {
			Argitical.LOGGER.info("Generating Divide Recipe for " + String.valueOf(numberId));
		}
		for (short i = min; i <= max; i++) {
			if (i < minCount || i > maxCount) {
				continue;
			}
			if (i == 0) {
				generateBuggedRecipe(numberId);
				continue;
			}
			if (isDivideAble(numberId, i)) {
				int result = getResult(numberId, i);
				String idA = numberItemId(numberId);
				String idB = numberItemId(i);
				JKeys key = new JKeys()
						.key("A", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idA)))
						.key("B", JIngredient.ofItem(AllItems.DIVIDE))
						.key("C", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idB)));
				Argitical.RECIPES.addRecipe(
						new Identifier(Argitical.NAMESPACE,
								"divide/" + numberItemId(numberId) + "_divide_" + numberItemId(i)),
						new JShapedRecipe(
								new JResult(new Identifier(Argitical.NAMESPACE, numberItemId(result))),
								new JPattern("ABC"), key));
			}
		}
	}
}
