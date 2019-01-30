package com.systemkern.kommons.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import org.springframework.test.context.ContextConfiguration
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Entity
import javax.persistence.EntityNotFoundException
import javax.persistence.Id


const val basepackage = "com.systemkern.kommons.jpa"

@DataJpaTest(showSql = false)
@ContextConfiguration(classes = [KtCrudRepositoryTests::class])
@EnableJpaRepositories(basePackages = [basepackage])
@EntityScan(basePackages = [basepackage])
private open class KtCrudRepositoryTests {

    @Autowired
    private lateinit var repo: TestKtCrudRepo

    @Test fun `Can Instantiate KtCrudRepo`() {
        val original = repo.save(TestEntity())

        assertThat(repo).isNotNull
        assertThat(repo.getById(original.id)).isEqualTo(original)
        assertThat(repo.findById2(original.id)).isNotNull
        assertThat(repo.findById2(original.id)).isEqualTo(original)
    }

    @Test fun `Throws Exception if getById() entity does not exist`() {
        assertThrows<EntityNotFoundException> {
            repo.getById(randomUUID())
        }
    }
}

@Repository
private interface TestKtCrudRepo : KtCrudRepository<TestEntity, UUID>

@Entity
private data class TestEntity(
    @Id val id: UUID = randomUUID(),
    val content: String = "foo"
)
