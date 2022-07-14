package live.auster.staticitems.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum ItemRarity {
    COMMON("COMMON", "#474B4E"),
    UNCOMMON("UNCOMMON", "#20603D"),
    EPIC("EPIC", "#4A192C"),
    LEGENDARY("LEGENDARY", "#FFFF00"),
    GODLY("GODLY", "#D95030"),
    UNOBTAINABLE("UNOBTAINABLE", "#EA899A");
    final TextComponent title;

    ItemRarity(String s, String c) {
        title = Component.text(s).style(Style.empty()).color(TextColor.fromHexString(c)).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false);
    }
}
