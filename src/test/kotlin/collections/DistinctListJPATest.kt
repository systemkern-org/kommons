package com.systemkern.kommons.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.Math.random
import java.util.*
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.Version


const val basepackage = "com.systemkern.kommons"

@ExtendWith(SpringExtension::class)
@DataJpaTest(showSql = false)
@ContextConfiguration(classes = [SortedSetJPATest::class])
@ComponentScan(basePackages = [basepackage])
@EnableJpaRepositories(basePackages = [basepackage])
@EntityScan(basePackages = [basepackage])
private class SortedSetJPATest {

    @Autowired lateinit var repo: TestRepository

    @Test fun `Can sort a set by values of associated entities`() {
        val original = (0..100)
            .map { TestEntity() }
            .toTypedArray()

        val distinct = original.distinctBy { it.value }.sorted()
        val distinctAr = distinct.toTypedArray()
        val duplicates = (original.toList() - distinct).toTypedArray()

        TestCollectionEntity(collection = sortedSetOf(*original)).let { repo.save(it) }

        with(repo.findAll().first()) {
            assertThat(collection).size().isEqualTo(distinct.size)

            assertThat(collection).containsExactly(*distinctAr)
            assertThat(collection).containsOnly(*distinctAr)
            if (duplicates.isNotEmpty())
                assertThat(collection).doesNotContain(*duplicates)
        }
    }
}

@Entity
private data class TestCollectionEntity(
    @Id @GeneratedValue val id: UUID? = null,
    @Version val version: Long = 0L,


    @OneToMany(cascade = [ALL], fetch = EAGER, orphanRemoval = true)
    @OrderBy("value ASC")
    val collection: SortedSet<TestEntity> = sortedSetOf()
)

@Entity
private data class TestEntity(
    @Id @GeneratedValue val id: UUID? = null,
    @Version val version: Long = 0L,

    val value: Int = (random() * 100).toInt()
) : Comparable<TestEntity> {
    @Transient
    private val log = getLogger(this.javaClass)

    override fun compareTo(other: TestEntity): Int =
        value.compareTo(other.value)
}


@Repository
private interface TestRepository : CrudRepository<TestCollectionEntity, UUID>
