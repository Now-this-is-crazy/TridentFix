package powercyphe.tridentfix.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import powercyphe.tridentfix.TridentFix;

//@Mixin(ServerWorld.class)
//public class LoadWorldMixin {
//    @Inject(method = "onPlayerConnected" , at = @At("HEAD"))
//    private void loadWorldMixin(ServerPlayerEntity player, CallbackInfo ci) {
//        if (!player.getWorld().isClient) {
//                MutableText text = Text.literal("§8[").append(Text.literal(TridentFix.MOD_ID).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x99CCFF))).append(Text.literal(
//                        "§8] §7It is recommended to download the mod client-side due to certain aspects of the mod not working without.")));
//                player.sendMessage(text);
//        }
//    }
//}
