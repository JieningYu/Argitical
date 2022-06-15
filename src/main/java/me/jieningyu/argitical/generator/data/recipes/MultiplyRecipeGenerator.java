package me.jieningyu.argitical.generator.data.recipes;

import me.jieningyu.argitical.AllItems;
import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JKeys;
import net.devtech.arrp.json.recipe.JPattern;
import net.devtech.arrp.json.recipe.JResult;
import net.devtech.arrp.json.recipe.JShapedRecipe;
import net.minecraft.util.Identifier;

public class MultiplyRecipeGenerator {
	private static int getResult(Short firstNumber, Short secondNumber) {
		return firstNumber * secondNumber;
	}

	public static String numberItemId(int id) {
		return AllItems.numberItemId(id);
	}

	private static Short getMin(Short n, Short min){
		if (n == 0){
			return min;
		}
		float rawA = min/n;
		double raw = Math.ceil(rawA);
		Short last = (short) raw;
		return last;
	}
	private static Short getMax(Short n, Short max){
		if (n == 0){
			return max;
		}
		float rawA = max/n;
		double raw = Math.floor(rawA);
		Short last = (short) raw;
		return last;
	}

	public static void generate(Short numberId, Short maxCount, Short minCount) {
		Short min = getMin(numberId, minCount);
		Short max = getMax(numberId, maxCount);
		if (min > max){
			Short temp = max;
			max = min;
			min = temp;
		}
		if (Argitical.debugMode) {
			Argitical.LOGGER.info("Generating Multiply Recipe for " + String.valueOf(numberId));
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
					.key("B", JIngredient.ofItem(AllItems.MULTIPLY))
					.key("C", JIngredient.ofItem(new Identifier(Argitical.NAMESPACE, idB)));
			Argitical.RECIPES.addRecipe(
					new Identifier(Argitical.NAMESPACE,
							"multiply/" + numberItemId(numberId) + "_multiply_" + numberItemId(i)),
					new JShapedRecipe(
							new JResult(new Identifier(Argitical.NAMESPACE, numberItemId(result))),
							new JPattern("ABC"), key));
		}
	}
}
