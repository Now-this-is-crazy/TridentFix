package powercyphe.tridentfix.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import powercyphe.tridentfix.TridentFix;

import java.util.Objects;

@Mixin(TridentItem.class)
public class TridentItemMixin {

	@Inject(method = "onStoppedUsing", at = @At("HEAD"))
	private void storeSlotMixin(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
		if (!user.getWorld().isClient) {
			if (stack.getItem() == Items.TRIDENT) {
				stack.getOrCreateNbt().putInt("slot", ((PlayerEntity) user).getInventory().getSlotWithStack(stack));
			}
		}
	}

//	@Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"))
//	private boolean riptideStopUseMixin(PlayerEntity instance) {
//		return TridentFix.allowRiptideInAllFluids(instance);
//	}
//
//	@Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWaterOrRain()Z"))
//	private boolean riptideUseMixin(PlayerEntity instance) {
//		return TridentFix.allowRiptideInAllFluids(instance);
//	}
}