package powercyphe.tridentfix.mixin;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin extends ProjectileEntity {

    @Shadow public int returnTimer;
    @Shadow private boolean dealtDamage;

    protected TridentEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickMixin(CallbackInfo ci) {
        if (!this.getWorld().isClient) {
            if (!this.dealtDamage && this.getBlockPos().getY() < 1 && this.getWorld().getDimensionEntry().getKey().get().getValue().toString().contains("minecraft:the_end")) {
                this.dealtDamage = true;
                this.returnTimer = 0;
            }
        }
    }

    @Inject(method = "tryPickup", at = @At("HEAD"), cancellable = true)
    private void pickupMixin(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getWorld().isClient) {
            TridentEntity trident = (TridentEntity)(Object) this;
            if (this.getOwner() instanceof PlayerEntity owner) {
                if (player == owner) {
                    ItemStack tridentStack = ((PersistentProjectileEntityClassAccess)trident).getItemStack();
                    if (tridentStack.getItem() == Items.TRIDENT) {
                        NbtCompound tag = tridentStack.getOrCreateNbt();
                        if (EnchantmentHelper.getLevel(Enchantments.LOYALTY, tridentStack) > 0) {
                            if (tag.contains("slot", 3)) {
                                int slot = tag.getInt("slot");
                                if (owner.getAbilities().creativeMode) {
                                    tridentStack.setCount(0);
                                }
                                    if (owner.getInventory().getEmptySlot() != -1 && tridentStack.getCount() >= 1) {
                                        if (slot == -1 && owner.getOffHandStack().isEmpty()) {
                                            owner.equipStack(EquipmentSlot.OFFHAND, tridentStack.copy());
                                        } else {
                                            if (slot == -1) {
                                                slot = 0;
                                            }
                                            if (owner.getInventory().getStack(slot).isEmpty()) {
                                                owner.getInventory().insertStack(slot, tridentStack.copy());
                                            } else {
                                                owner.getInventory().offerOrDrop(tridentStack.copy());
                                            }
                                        }

                                        owner.playSound(SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((owner.getRandom().nextFloat() - owner.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                                        owner.sendPickup(trident, 1);
                                        this.discard();
                                        cir.cancel();
                                    }
                            }
                        }
                    }
                }
            }
        }
    }

    @Redirect(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isThundering()Z"))
    private boolean channelingMixin(World world) {
        return (world.isRaining() || world.isThundering());
    }

    /*
     * The following code is sourced from the BEnchantments project by rvbsm.
     * License: https://github.com/rvbsm/benchantments/blob/1.20/LICENSE
     *
     * MIT License
     *
     * Copyright (c) 2023 rvbsm
     *
     * Permission is hereby granted, free of charge, to any person obtaining a copy
     * of this software and associated documentation files (the "Software"), to deal
     * in the Software without restriction, including without limitation the rights
     * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     * copies of the Software, and to permit persons to whom the Software is
     * furnished to do so, subject to the following conditions:
     *
     * The above copyright notice and this permission notice shall be included in
     * all copies or substantial portions of the Software.
     *
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
     * THE SOFTWARE.
     */

    @Redirect(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"))
    private float onEntityHit$getAttackDamage(ItemStack stack, EntityGroup group, EntityHitResult entityHitResult) {
            return powercyphe.tridentfix.EnchantmentHelper.getAttackDamage(stack, entityHitResult.getEntity());
    }
}

