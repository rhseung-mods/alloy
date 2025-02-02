package com.rhseung.alloy.util

import com.rhseung.alloy.metal.Metal
import java.lang.reflect.Field

object Utils {
    @Throws(NoSuchFieldException::class)
    private fun getField(clazz: Class<*>, fieldName: String): Field {
        return try {
            clazz.getDeclaredField(fieldName);
        } catch (e: NoSuchFieldException) {
            val superClass = clazz.superclass;
            if (superClass == null) {
                throw e;
            } else {
                getField(superClass, fieldName);
            }
        }
    }

    fun get(receiver: Any, propertyName: String): Any? {
        val clazz: Class<*> = receiver.javaClass

        var field: Field? = null
        try {
            field = getField(clazz, propertyName)
        } catch (e: NoSuchFieldException) {
            return null
        }

        field.isAccessible = true

        return try {
            field[receiver]
        } catch (e: IllegalAccessException) {
            null
        }
    }

    fun alloyName(mixingRatio: Map<Metal, Int>): String {
        assert(mixingRatio.isNotEmpty());
        return mixingRatio.entries.sortedBy { it.key.lowerName }.joinToString("_") { it.key.lowerName + it.value } + "_alloy";
    }

    fun alloyTitleName(mixingRatio: Map<Metal, Int>): String {
        assert(mixingRatio.isNotEmpty());
        return mixingRatio.entries.sortedBy { it.key.titleName }.joinToString(" ") { it.key.titleName + " " + it.value + "%" } + " Alloy";
    }

    fun alloyColor(mixingRatio: Map<Metal, Int>): Color {
        assert(mixingRatio.isNotEmpty());

        var color: Color? = null;
        for ((metal, ratio) in mixingRatio) {
            if (color == null)
                color = metal.color * ratio;
            else
                color += metal.color * ratio;
        }

        return color!!;
    }
}