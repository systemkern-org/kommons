package systemkern.extensions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import javax.persistence.Id
import org.junit.runner.RunWith

internal class KClassExtensionsTests {

    @Test fun `Can get class properties`() {
        val ret = MyDataClass::class.getEntityProperties()

        val expectedFields = listOf("version","str","obj","col")
        val idFields = listOf("id","userId")

        with(ret.map{ it.name}) {
            assertThat(this).containsAll(expectedFields)
            assertThat(this).doesNotContainAnyElementsOf(idFields)
        }
    }

}

private data class MyDataClass(
    @Id private val userId: Long,
    private val id: Long,
    val version: Long = 0L,
    private val str: String,
    private val obj: MyDataClass,
    private val col: SortedSet<MyDataClass> = sortedSetOf()
)