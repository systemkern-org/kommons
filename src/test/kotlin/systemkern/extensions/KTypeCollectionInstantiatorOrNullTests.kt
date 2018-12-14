package systemkern.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.starProjectedType

internal class KTypeCollectionInstantiatorOrNullTests {

    @Test fun `Can create collection instantiator for Kotlin Enumeration`() {
        val ret = java.util.Enumeration::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("enumerationOf()")
    }

    @Test fun `Can create collection instantiator for Kotlin Iterable`() {
        val ret = kotlin.collections.Iterable::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("setOf()")
    }

    @Test fun `Can create collection instantiator for Kotlin List`() {
        val ret = kotlin.collections.List::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("listOf()")
    }

    @Test fun `Can create collection instantiator for Kotlin Map`() {
        val ret = kotlin.collections.Map::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("mapOf()")
    }

    @Test fun `Can create collection instantiator for Kotlin Set`() {
        val ret = kotlin.collections.Set::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("setOf()")
    }

    @Test fun `Can create collection instantiator for Kotlin Collection`() {
        val ret = kotlin.collections.Collection::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("setOf()")
    }

    @Test fun `Can create collection instantiator for Java SortedMap`() {
        val ret = java.util.SortedMap::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("sortedMapOf()")
    }

    @Test fun `Can create collection instantiator for Java SortedSet`() {
        val ret = java.util.SortedSet::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("sortedSetOf()")
    }

    @Test fun `Can create collection instantiator for Array`() {
        val ret = Array<Any>::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isEqualTo("arrayOf()")
    }

    @Test fun `Cannot create collection instantiator for Integer`() {
        val ret = Integer::class.starProjectedType.collectionInstantiatorOrNull()
        assertThat(ret).isNull()
    }

}
