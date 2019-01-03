package systemkern.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import javax.persistence.Id

internal class KClassExtensionsTests {

    @Test fun `Can get class properties`() {
        val expectedFields = listOf("version", "str", "obj")
        val idFields = listOf("id", "userId")

        val ret = MyDataClass::class.getEntityProperties()

        with(ret.map { it.name }) {
            assertThat(this).containsAll(expectedFields)
            assertThat(this).doesNotContainAnyElementsOf(idFields)
        }
    }

}

private data class MyDataClass(
    @Id private val userId: Long,
    private val id: Long,
    private val version: Long = 0L,
    private val str: String,
    private val obj: MyDataClass,
    private val col: SortedSet<MyDataClass> = sortedSetOf()
)
