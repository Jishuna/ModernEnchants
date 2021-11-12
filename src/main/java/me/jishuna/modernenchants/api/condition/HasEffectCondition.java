package me.jishuna.modernenchants.api.condition;

import static me.jishuna.modernenchants.api.utils.ParseUtils.checkLength;
import static me.jishuna.modernenchants.api.utils.ParseUtils.readTarget;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

import me.jishuna.modernenchants.api.annotation.RegisterCondition;
import me.jishuna.modernenchants.api.effect.ActionTarget;
import me.jishuna.modernenchants.api.enchantment.CustomEnchantment;
import me.jishuna.modernenchants.api.enchantment.EnchantmentContext;
import me.jishuna.modernenchants.api.exception.InvalidEnchantmentException;

@RegisterCondition(name = "has_effect")
public class HasEffectCondition extends EnchantmentCondition {
	private static final Set<String> ALL_TYPES = Arrays.stream(PotionEffectType.values()).map(PotionEffectType::getName)
			.collect(Collectors.toSet());

	private final ActionTarget target;
	private final PotionEffectType type;
	private final boolean bool;

	public HasEffectCondition(String[] data) throws InvalidEnchantmentException {
		super(data);
		checkLength(data, 3);

		this.target = readTarget(data[0]);

		String typeString = data[1].toUpperCase();
		if (!ALL_TYPES.contains(typeString))
			throw new InvalidEnchantmentException("Invalid potion effect type: " + typeString);

		this.type = PotionEffectType.getByName(typeString);
		this.bool = Boolean.parseBoolean(data[2]);
	}

	@Override
	public boolean check(EnchantmentContext context, CustomEnchantment enchant) {
		LivingEntity entity = context.getTargetDirect(target);

		if (entity == null)
			return false;
		return entity.hasPotionEffect(type) == bool;
	}

}