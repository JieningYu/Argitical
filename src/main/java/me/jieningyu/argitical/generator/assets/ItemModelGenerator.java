package me.jieningyu.argitical.generator.assets;

import me.jieningyu.argitical.Argitical;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.util.Identifier;

public class ItemModelGenerator {
	public static void generateModel(String itemId){
		JModel model = new JModel("item/generated").addTexture("layer0", Argitical.NAMESPACE + ":item/" + itemId);
		Argitical.ASSETS.addModel(model, new Identifier(Argitical.NAMESPACE, "item/" + itemId));
	}
}
