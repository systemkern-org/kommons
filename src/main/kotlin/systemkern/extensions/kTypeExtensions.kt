package systemkern.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.withNullability

internal val defaultCollectionInstance = mapOf(
    "kotlin.collections.Array" to "arrayOf()",
    "kotlin.collections.Collection" to "setOf()",
    "kotlin.collections.Iterable" to "setOf()",
    "kotlin.collections.List" to "listOf()",
    "kotlin.collections.Set" to "setOf()",
    "kotlin.collections.Map" to "mapOf()",
    "java.util.Collection" to "setOf()",
    "java.util.Enumeration" to "enumerationOf()",
    "java.util.Iterable" to "setOf()",
    "java.util.List" to "listOf()",
    "java.util.Set" to "setOf()",
    "java.util.SortedSet" to "sortedSetOf()",
    "java.util.Map" to "mapOf()",
    "java.util.SortedMap" to "sortedMapOf()"
)

internal val defaultInstance = mapOf(
    "kotlin.String" to "\"example_string\"",
    "kotlin.Boolean" to "false",
    "kotlin.Int" to "0",
    "kotlin.Long" to "0",
    "kotlin.Double" to ".0",
    "kotlin.Float" to "0f",
    "java.util.Calendar" to "Calendar.getInstance()",
    "java.util.UUID" to "UUID.randomUUID()",
    "java.time.LocalDate" to "LocalDate.now()",
    "java.time.LocalDateTime" to "LocalDateTime.now()",
    "java.time.ZonedDate" to "ZonedDate.now()",
    "java.time.ZonedDateTime" to "ZonedDateTime.now()",
    "javax.money.MonetaryAmount" to "Money.of(1, \"EUR\")",
    "org.javamoney.moneta.Money" to "Money.of(1, \"EUR\")",
    *defaultCollectionInstance.array()
)

internal fun KType.simpleName(): String =
    "${this.withNullability(false)}"
        .substringAfterLast(".")
        .substringBefore("<")
        .substringBefore(">")

internal fun KType.isCollection(): Boolean =
    this.collectionInstantiatorOrNull() != null

internal fun KType.collectionInstantiatorOrNull(): String? {
    return defaultCollectionInstance[this.toString().substringBefore("<")]
        ?: listOf("Array", "List", "SortedMap", "SortedSet", "Set")
            .firstOrNull {
                this.toString().contains(it, ignoreCase = true)
            }
            ?.let { "${it[0].toLowerCase()}${it.substring(1)}Of()" }
}

fun KType.firstEnumValueOrNull(): Any? =
    (this.classifier as KClass<*>).java.enumConstants?.first()

private fun <K, V> Map<K, V>.pairs() =
    this.map { it.key to it.value }

private fun <K, V> Map<K, V>.array() =
    this.pairs().toTypedArray()
