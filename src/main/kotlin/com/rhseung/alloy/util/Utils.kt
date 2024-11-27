package com.rhseung.alloy.util

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
}