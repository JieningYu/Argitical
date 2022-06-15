package me.jieningyu.argitical.generator.assets;

import me.jieningyu.argitical.AllItems;
import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.util.Identifier;

public class NumberModelGenerator {
	public static String formatNumber(short numberId) {
		if (numberId < 10 && numberId > -10) {
			return "0" + String.valueOf(Math.abs(numberId));
		}
		return String.valueOf(Math.abs(numberId));
	}

	public static String positiveOrNegative(short numberId) {
		if (numberId >= 0) {
			return "positive";
		}
		return "negative";
	}

	public static void generate(short numberId) {
		String formattedNumber = formatNumber(numberId);

		String layerZero = Argitical.NAMESPACE + ":item/number/" + positiveOrNegative(numberId) + "/left/" + formattedNumber.charAt(0);
		String layerOne = Argitical.NAMESPACE + ":item/number/" + positiveOrNegative(numberId) + "/right/" + formattedNumber.charAt(1);

		JModel model = new JModel("item/generated").addTexture("layer0", layerZero).addTexture("layer1", layerOne).addTexture("layer2", Argitical.NAMESPACE + ":item/number/panel");

		Argitical.ASSETS.addModel(model, new Identifier(Argitical.NAMESPACE, "item/" + AllItems.numberItemId(numberId)));
	}
}
