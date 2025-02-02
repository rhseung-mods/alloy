package com.rhseung.alloy.metal

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class AlloyMixingRatioComponent(val mixingRatio: Map<Metal, Int>) {
    init {
        if (mixingRatio.values.sum() != 100)
            throw IllegalArgumentException("Mixing ratio must sum to 100")
    }

    companion object {
        val DEFAULT = AlloyMixingRatioComponent(mapOf(
            Metal.COPPER to 50,
            Metal.IRON to 50
        )); // TODO

        val CODEC: Codec<AlloyMixingRatioComponent> = RecordCodecBuilder.create { builder -> builder.group(
            Codec.unboundedMap(Metal.CODEC, Codec.INT)
                .fieldOf("mixing_ratio")
                .forGetter(AlloyMixingRatioComponent::mixingRatio)
            ).apply(builder, ::AlloyMixingRatioComponent)
        };

//        val PACKET_CODEC: PacketCodec<RegistryByteBuf, AlloyMixingRatioComponent> = Record
    }
}