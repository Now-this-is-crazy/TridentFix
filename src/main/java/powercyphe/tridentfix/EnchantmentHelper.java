package powercyphe.tridentfix;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

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

public class EnchantmentHelper extends net.minecraft.enchantment.EnchantmentHelper {
    public static float getAttackDamage(ItemStack stack, Entity target) {
        if (!target.isLiving()) return getAttackDamage(stack, EntityGroup.DEFAULT);
        else if (target.isTouchingWaterOrRain()) return getAttackDamage(stack, EntityGroup.AQUATIC);
        return getAttackDamage(stack, ((LivingEntity) target).getGroup());
    }
}