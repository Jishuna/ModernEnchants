package me.jishuna.modernenchants.api.condition;

import static me.jishuna.modernenchants.api.utils.ParseUtils.checkLength;
import static me.jishuna.modernenchants.api.utils.ParseUtils.readTarget;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.jishuna.modernenchants.api.annotation.RegisterCondition;
import me.jishuna.modernenchants.api.effect.ActionTarget;
import me.jishuna.modernenchants.api.enchantment.CustomEnchantment;
import me.jishuna.modernenchants.api.enchantment.EnchantmentContext;
import me.jishuna.modernenchants.api.exception.InvalidEnchantmentException;

@RegisterCondition(name = "blocking")
public class BlockingCondition extends EnchantmentCondition {
	
	private final ActionTarget target;
	private final boolean bool;

	public BlockingCondition(String[] data) throws InvalidEnchantmentException {
		super(data);
		checkLength(data, 2);
		
		this.target = readTarget(data[0]);
		this.bool = Boolean.parseBoolean(data[1]);
	}

	@Override
	public boolean check(EnchantmentContext context, CustomEnchantment enchant) {
		LivingEntity entity = context.getTargetDirect(target);

		if (entity instanceof Player player) {
			return player.isBlocking() == bool;
		}
		return false;
	}
}