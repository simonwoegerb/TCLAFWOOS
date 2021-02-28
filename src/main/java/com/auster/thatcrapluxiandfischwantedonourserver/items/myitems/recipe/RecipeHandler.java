package com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.recipe;

import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.CustomItems;
import com.auster.thatcrapluxiandfischwantedonourserver.items.myitems.items.ExplosiveArrow;
import com.auster.thatcrapluxiandfischwantedonourserver.utils.RunOnStartUp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class RecipeHandler implements RunOnStartUp {

    @Override
    public void onStartup(Plugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, "explosivearrow");
        ShapedRecipe recipe = new ShapedRecipe(key, CustomItems.createItem(ExplosiveArrow.class, 1));
        recipe.shape("GGG", "GSG", "GGG");
        recipe.setIngredient('G', new ItemStack(Material.GUNPOWDER));
        recipe.setIngredient('S', new ItemStack(Material.SPECTRAL_ARROW, 3));
        Bukkit.addRecipe(recipe);

    }
}
