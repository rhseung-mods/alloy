package com.rhseung.alloy.init

import com.rhseung.alloy.Mod
import net.minecraft.entity.damage.DamageEffects
import net.minecraft.entity.damage.DamageType
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object ModDamageTypes : IModInit {
    fun of(id: Identifier): RegistryKey<DamageType> {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, id);
    }

    val MELTING = of(Mod.id("melting"));

    fun bootstrap(damageTypeRegisterable: Registerable<DamageType>) {
        damageTypeRegisterable.register(MELTING, DamageType("melting", 0.1F, DamageEffects.BURNING));
    }
}