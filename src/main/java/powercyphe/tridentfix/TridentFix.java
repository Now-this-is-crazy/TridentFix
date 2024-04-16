package powercyphe.tridentfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class TridentFix implements ModInitializer {
	public static final GameRules.Key<GameRules.BooleanRule> BEDROCKIMPALING = GameRuleRegistry.register("useBedrockImpaling", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> CHANNELINGDURINGRAIN = GameRuleRegistry.register("channelingDuringRain", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
//	public static final GameRules.Key<GameRules.BooleanRule> ALLOWRIPTIDEINALLFLUIDS = GameRuleRegistry.register("allowRiptideInAllFluids", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	public static final String MOD_ID = "tridentfix";

	@Override
	public void onInitialize() {
	}

	public static float useBedrockImpalingForTridentEntity(ItemStack stack, EntityGroup group, EntityHitResult entityHitResult) {
		if (entityHitResult.getEntity().getWorld().getGameRules().getBoolean(BEDROCKIMPALING)) {
			return powercyphe.tridentfix.EnchantmentHelper.getAttackDamage(stack, entityHitResult.getEntity());
		}
		return net.minecraft.enchantment.EnchantmentHelper.getAttackDamage(stack, group);
	}

	public static float useBedrockImpalingForTridentItem(ItemStack stack, Entity target) {
		if (target.getWorld().getGameRules().getBoolean(BEDROCKIMPALING)) {
			return powercyphe.tridentfix.EnchantmentHelper.getAttackDamage(stack, target);
		}
		return net.minecraft.enchantment.EnchantmentHelper.getAttackDamage(stack, ((LivingEntity)target).getGroup());
	}

//	public static boolean allowRiptideInAllFluids(PlayerEntity player) {
//		if (player.getWorld().getGameRules().getBoolean(ALLOWRIPTIDEINALLFLUIDS)) {
//			return (player.isInFluid() || player.isTouchingWaterOrRain());
//		}
//		return player.isTouchingWaterOrRain();
//	}

	public static boolean channelingDuringRain(World world) {
		if (world.getGameRules().getBoolean(CHANNELINGDURINGRAIN)) {
			return (world.isRaining() || world.isThundering());
		}
		return world.isThundering();
	}
}