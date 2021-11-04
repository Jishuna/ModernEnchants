package me.jishuna.modernenchants.api.enchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import me.jishuna.modernenchants.api.conditions.ConditionRegistry;
import me.jishuna.modernenchants.api.conditions.EnchantmentCondition;
import me.jishuna.modernenchants.api.effects.DelayEffect;
import me.jishuna.modernenchants.api.effects.EffectRegistry;
import me.jishuna.modernenchants.api.effects.EnchantmentEffect;
import me.jishuna.modernenchants.api.exceptions.InvalidEnchantmentException;

public class EnchantmentLevel {
	private final List<EnchantmentEffect> effects = new ArrayList<>();
	private final List<EnchantmentCondition> conditions = new ArrayList<>();
	private boolean hasDelay;
	private final int minExperienceLevel;

	public EnchantmentLevel(ConfigurationSection section, EffectRegistry effectRegistry,
			ConditionRegistry conditionRegistry) throws InvalidEnchantmentException {
		this.minExperienceLevel = section.getInt("min-enchanting-level");
		
		for (String actionString : section.getStringList("effects")) {
			EnchantmentEffect effect = effectRegistry.parseString(actionString);

			if (effect != null) {
				effects.add(effect);
			}

			if (effect instanceof DelayEffect) {
				this.hasDelay = true;
			}
		}

		for (String conditionString : section.getStringList("conditions")) {
			EnchantmentCondition condition = conditionRegistry.parseString(conditionString);

			if (condition != null) {
				conditions.add(condition);
			}
		}
	}

	public List<EnchantmentEffect> getEffects() {
		return effects;
	}

	public List<EnchantmentCondition> getConditions() {
		return conditions;
	}

	public int getMinExperienceLevel() {
		return minExperienceLevel;
	}

	public boolean hasDelay() {
		return hasDelay;
	}
}
