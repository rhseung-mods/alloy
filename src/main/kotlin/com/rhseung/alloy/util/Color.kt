package com.rhseung.alloy.util

import net.minecraft.item.ToolMaterial
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting
import kotlin.math.roundToInt

class Color {
    val R: Int
    val G: Int
    val B: Int
    val H: Int
    val S: Float
    val V: Float

    constructor(R: Int, G: Int, B: Int) {
        this.R = R
        this.G = G
        this.B = B

        val r = R / 255.0F
        val g = G / 255.0F
        val b = B / 255.0F

        val max = maxOf(r, g, b)
        val min = minOf(r, g, b)

        this.H = ((when (max) {
            min -> 0.0F
            r -> (g - b) / (max - min)
            g -> 2 + (b - r) / (max - min)
            b -> 4 + (r - g) / (max - min)
            else -> 0.0F
        } * 60).roundToInt() + 360) % 360

        this.S = when (max) {
            0.0F -> 0.0F
            else -> (max - min) / max
        }

        this.V = max
    }

    constructor(H: Int, S: Float, V: Float) {
        this.H = H
        this.S = S
        this.V = V

        val max = (V * 255).roundToInt()
        val min = (max * (1 - S)).roundToInt()

        when (H) {
            in 300 until 360 -> {
                this.R = max
                this.G = min
                this.B = (-((H - 360) / 60.0) * (max - min) + this.G).roundToInt()
            }

            in 0 until 60 -> {
                this.R = max
                this.B = min
                this.G = ((H / 60.0) * (max - min) + this.B).roundToInt()
            }

            in 60 until 120 -> {
                this.G = max
                this.B = min
                this.R = (-(H / 60.0 - 2) * (max - min) + this.B).roundToInt()
            }

            in 120 until 180 -> {
                this.G = max
                this.R = min
                this.B = ((H / 60.0 - 2) * (max - min) + this.R).roundToInt()
            }

            in 180 until 240 -> {
                this.B = max
                this.R = min
                this.G = (-(H / 60.0 - 4) * (max - min) + this.R).roundToInt()
            }

            in 240 until 300 -> {
                this.B = max
                this.G = min
                this.R = ((H / 60.0 - 4) * (max - min) + this.G).roundToInt()
            }

            else -> error("impossible")
        }
    }

    constructor(hex: Int) : this((hex shr 16) and 0xFF, (hex shr 8) and 0xFF, hex and 0xFF);

    override fun toString(): String {
        return Integer.toHexString(1 shl 24 or (R shl 16) or (G shl 8) or B).substring(1);
    }

    fun toFloatSize(): Triple<Float, Float, Float> {
        return Triple(R / 255.0F, G / 255.0F, B / 255.0F);
    }

    fun toInt(): Int {
        return Integer.parseInt(toString(), 16);
    }

    fun toTextColor(): TextColor {
        return TextColor.fromRgb(toInt());
    }

    fun darker(delta: Int): Color {
        return Color(
            (R - delta).coerceIn(0, 255),
            (G - delta).coerceIn(0, 255),
            (B - delta).coerceIn(0, 255)
        );
    }

    fun brighter(delta: Int): Color {
        return Color(
            (R + delta).coerceIn(0, 255),
            (G + delta).coerceIn(0, 255),
            (B + delta).coerceIn(0, 255)
        );
    }

    companion object {
        val WOOD = Color(150, 116, 65);
        val STONE = Color(149, 145, 141);
        val IRON = Color(215, 215, 215);
        val DIAMOND = Color(110, 236, 210);
        val NETHERITE = Color(98, 88, 89);
        val QUARTZ = Color(14931140);
        val COPPER = Color(11823181);

        // Armor Trim Color
//        register(registry, QUARTZ, Items.QUARTZ, Style.EMPTY.withColor(14931140), 0.1F);
//        register(registry, IRON, Items.IRON_INGOT, Style.EMPTY.withColor(15527148), 0.2F, Map.of(EquipmentModels.IRON, "iron_darker"));
//        register(registry, NETHERITE, Items.NETHERITE_INGOT, Style.EMPTY.withColor(6445145), 0.3F, Map.of(EquipmentModels.NETHERITE, "netherite_darker"));
//        register(registry, REDSTONE, Items.REDSTONE, Style.EMPTY.withColor(9901575), 0.4F);
//        register(registry, COPPER, Items.COPPER_INGOT, Style.EMPTY.withColor(11823181), 0.5F);
//        register(registry, GOLD, Items.GOLD_INGOT, Style.EMPTY.withColor(14594349), 0.6F, Map.of(EquipmentModels.GOLD, "gold_darker"));
//        register(registry, EMERALD, Items.EMERALD, Style.EMPTY.withColor(1155126), 0.7F);
//        register(registry, DIAMOND, Items.DIAMOND, Style.EMPTY.withColor(7269586), 0.8F, Map.of(EquipmentModels.DIAMOND, "diamond_darker"));
//        register(registry, LAPIS, Items.LAPIS_LAZULI, Style.EMPTY.withColor(4288151), 0.9F);
//        register(registry, AMETHYST, Items.AMETHYST_SHARD, Style.EMPTY.withColor(10116294), 1.0F);

        val BLACK = Color(0);
        val DARK_BLUE = Color(170);
        val DARK_GREEN = Color(43520);
        val DARK_AQUA = Color(43690);
        val DARK_RED = Color(11141120);
        val DARK_PURPLE = Color(11141290);
        val GOLD = Color(16755200);
        val GRAY = Color(11184810);
        val DARK_GRAY = Color(5592405);
        val BLUE = Color(5592575);
        val GREEN = Color(5635925);
        val AQUA = Color(5636095);
        val RED = Color(16733525);
        val LIGHT_PURPLE = Color(16733695);
        val YELLOW = Color(16777045);
        val WHITE = Color(16777215);

        fun Pair<Color, Color>.gradient(ratioOfFirst: Float): Color {
            return Color(
                (this.first.H * ratioOfFirst + this.second.H * (1 - ratioOfFirst)).roundToInt(),
                this.first.S * ratioOfFirst + this.second.S * (1 - ratioOfFirst),
                this.first.V * ratioOfFirst + this.second.V * (1 - ratioOfFirst)
            );
        }

        fun ToolMaterial.toColor() = when (this) {
            ToolMaterial.WOOD -> WOOD
            ToolMaterial.GOLD -> GOLD
            ToolMaterial.STONE -> STONE
            ToolMaterial.IRON -> IRON
            ToolMaterial.DIAMOND -> DIAMOND
            ToolMaterial.NETHERITE -> NETHERITE
            else -> error("Unknown tool material")
        }

        fun Formatting.toColor() = when (this) {
            Formatting.BLACK -> BLACK
            Formatting.DARK_BLUE -> DARK_BLUE
            Formatting.DARK_GREEN -> DARK_GREEN
            Formatting.DARK_AQUA -> DARK_AQUA
            Formatting.DARK_RED -> DARK_RED
            Formatting.DARK_PURPLE -> DARK_PURPLE
            Formatting.GOLD -> GOLD
            Formatting.GRAY -> GRAY
            Formatting.DARK_GRAY -> DARK_GRAY
            Formatting.BLUE -> BLUE
            Formatting.GREEN -> GREEN
            Formatting.AQUA -> AQUA
            Formatting.RED -> RED
            Formatting.LIGHT_PURPLE -> LIGHT_PURPLE
            Formatting.YELLOW -> YELLOW
            Formatting.WHITE -> WHITE
            else -> error("Formatting Modifier cannot be converted to Color")
        }
    }
}