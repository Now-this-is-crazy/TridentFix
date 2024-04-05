package powercyphe.tridentfix.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.geom.Arc2D;

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
}