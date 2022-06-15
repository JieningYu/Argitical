package me.jieningyu.argitical;

import net.devtech.arrp.json.lang.JLang;
import net.minecraft.util.Identifier;

public class LangHandler {
	public static JLang LangEnUs = new JLang();
	public static JLang LangZhCn = new JLang();
	public static void addNameEnUs(String objectId, String objectType, String name){
		LangEnUs = LangEnUs.entry(objectType + "." + Argitical.NAMESPACE + "." + objectId , name);
	}
	public static void addNameZhCn(String objectId, String objectType, String name){
		LangZhCn = LangZhCn.entry(objectType + "." + Argitical.NAMESPACE + "." + objectId , name);
	}
	public static void goLang() {
		Argitical.ASSETS.addLang(new Identifier(Argitical.NAMESPACE, "en_us"), LangEnUs);
		Argitical.ASSETS.addLang(new Identifier(Argitical.NAMESPACE, "zh_cn"), LangZhCn);
	}
}
