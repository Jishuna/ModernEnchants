package me.jishuna.modernenchants.api.effect;

import static me.jishuna.modernenchants.api.utils.ParseUtils.checkLength;
import static me.jishuna.modernenchants.api.utils.ParseUtils.readDouble;
import static me.jishuna.modernenchants.api.utils.ParseUtils.readTarget;

import me.jishuna.modernenchants.api.annotation.RegisterEffect;
import me.jishuna.modernenchants.api.enchantment.EnchantmentContext;
import me.jishuna.modernenchants.api.exception.InvalidEnchantmentException;
import net.md_5.bungee.api.ChatColor;

@RegisterEffect(name = "damage")
public class DamageEffect extends EnchantmentEffect {
	private static final String[] DESCRIPTION = new String[] {
			ChatColor.GOLD + "Description: " + ChatColor.GREEN + "Deal damage to the target.",
			ChatColor.GOLD + "Usage: " + ChatColor.GREEN + "damage(target,amount)",
			ChatColor.GOLD + "  - Target: " + ChatColor.GREEN
					+ "The entity to damage, either \"user\" or \"opponent\".",
			ChatColor.GOLD + "  - Amount: " + ChatColor.GREEN + "The amount of damage to deal, 2 damage = 1 heart." };

	private final ActionTarget target;
	private final double damage;

	public DamageEffect(String[] data) throws InvalidEnchantmentException {
		super(data);
		checkLength(data, 2);

		this.target = readTarget(data[0]);
		this.damage = readDouble(data[1], "amount");

		if (this.damage <= 0)
			throw new InvalidEnchantmentException("Damage must be greater than 0");
	}

	@Override
	public void handle(EnchantmentContext context) {
		context.getTarget(target).ifPresent(entity -> entity.damage(damage));
	}
	
	public static String[] getDescription() {
		return DESCRIPTION;
	}
}