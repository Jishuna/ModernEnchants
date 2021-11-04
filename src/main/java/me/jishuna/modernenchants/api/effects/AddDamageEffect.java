package me.jishuna.modernenchants.api.effects;

import static me.jishuna.modernenchants.api.ParseUtils.readFloat;
import org.bukkit.event.entity.EntityDamageEvent;

import me.jishuna.modernenchants.api.annotations.RegisterEffect;
import me.jishuna.modernenchants.api.enchantments.EnchantmentContext;
import me.jishuna.modernenchants.api.exceptions.InvalidEnchantmentException;
import net.md_5.bungee.api.ChatColor;

@RegisterEffect(name = "add_damage")
public class AddDamageEffect extends EnchantmentEffect {
	private static final String[] DESCRIPTION = new String[] {
			ChatColor.GOLD + "Description: " + ChatColor.GREEN + "Adds or subtracts to the damage of an attack.",
			ChatColor.GOLD + "Usage: " + ChatColor.GREEN + "add_damage(amount)",
			ChatColor.GOLD + "  Amount: " + ChatColor.GREEN + "The amount of extra damage to add, negative values will subtract damage instead." };

	private final float amount;

	public AddDamageEffect(String[] data) throws InvalidEnchantmentException {
		super(data);

		this.amount = readFloat(data[0]);
	}

	public void handle(EnchantmentContext context) {
		if (context.getEvent()instanceof EntityDamageEvent event) {
			event.setDamage(event.getDamage() + amount);
		}
	}

	public static String[] getDescription() {
		return DESCRIPTION;
	}
}
