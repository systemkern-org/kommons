package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.Entity
import javax.persistence.Id

const val basepackage = "com.systemkern.kommons"

@ExtendWith(SpringExtension::class)
@DataJpaTest(showSql = false)
@ContextConfiguration(classes = [KtCrudRepositoryTests::class])
@ComponentScan(basePackages = [basepackage])
@EnableJpaRepositories(basePackages = [basepackage])
@EntityScan(basePackages = [basepackage])
internal class KtCrudRepositoryTests {

    @Autowired
    lateinit var repo: KtCrudDataTestRepository

    @Test fun `Can instantiate a KtCrudRepository`() {
        assertThat(repo).isNotNull
    }

    @Test fun `Can get entity by id`() {
        val orig = repo.save(KtCrudData(42))
        with(repo.getById(42)) {
            assertThat(this).isNotNull
            assertThat(id).isEqualTo(orig.id)
            assertThat(string).isEqualTo(orig.string)

        }
    }

    @Test fun `Can return null on not existing entity`() {
        assertThat(repo.findById2(-1)).isNull()
    }

    @Test fun `Throws exception if trying to get not existing entity`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            repo.getById(-1)
        }
    }
}

@Entity
internal data class KtCrudData(
    @Id val id: Long,
    val string: String = ""
)

@Repository
internal interface KtCrudDataTestRepository : KtCrudRepository<KtCrudData, Long>
