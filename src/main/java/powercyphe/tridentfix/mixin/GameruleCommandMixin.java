package powercyphe.tridentfix.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import powercyphe.tridentfix.TridentFix;

import java.util.Objects;

//@Mixin(GameRuleCommand.class)
//public class GameruleCommandMixin {
//    @Inject(method = "executeSet", at = @At("RETURN"))
//    private static <T extends GameRules.Rule<T>> void gameruleMixin(CommandContext<ServerCommandSource> context, GameRules.Key<T> key, CallbackInfoReturnable<Integer> cir) {
//        PlayerEntity player = context.getSource().getPlayer();
//        World world = context.getSource().getWorld();
//        T rule = context.getSource().getServer().getGameRules().get(key);
//        if (rule == world.getGameRules().get(TridentFix.ALLOWRIPTIDEINALLFLUIDS)) {
//            MutableText text = Text.literal("§8[").append(Text.literal(TridentFix.MOD_ID).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x99CCFF))).append(Text.literal(
//                    "§8] §7For this change to take effect the server needs to be restarted. Please also keep in mind that this feature is only available to players who have the mod installed on the client.")));
//            Objects.requireNonNull(player).sendMessage(text);
//        }
//    }
//}
