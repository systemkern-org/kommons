package com.systemkern.kommons.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID
import javax.persistence.Id

class InMemoryRepositoryTests {

    @Test fun `can get Entity ID`() {
        InMemoryEntity().getId<InMemoryEntity, UUID>(InMemoryEntity::class)
    }

    @Test fun `Can Instantiate an empty Repository`() {
        val repo: KtCrudRepository<InMemoryEntity, UUID> = InMemoryRepository(InMemoryEntity::class)
        assertThat(repo.findAll()).isEmpty()
    }

    @Test fun `Can save entity`() {
        val repo = InMemoryRepository<InMemoryEntity, UUID>(InMemoryEntity::class)
        repo.save(InMemoryEntity())
        assertThat(repo.findAll()).isNotEmpty
    }

    @Test fun `Can get entity by Id`() {
        val repo = InMemoryRepository<InMemoryEntity, UUID>(InMemoryEntity::class)
        val original = repo.save(InMemoryEntity())

        with(repo.getById(original.id)) {
            assertThat(this).isSameAs(original)
        }
    }

    @Test fun `Can delete entity`() {
        val repo = InMemoryRepository<InMemoryEntity, UUID>(InMemoryEntity::class)
        val original = repo.save(InMemoryEntity())
        repo.delete(original)
        assertThat(repo.findAll()).isEmpty()
    }

    @Test fun `Can delete entity by id`() {
        val repo = InMemoryRepository<InMemoryEntity, UUID>(InMemoryEntity::class)
        val original = repo.save(InMemoryEntity())
        repo.deleteById(original.id)
        assertThat(repo.findAll()).isEmpty()
    }
}

internal data class InMemoryEntity(
    @Id val id: UUID = UUID.randomUUID(),
    val content: String = "foo"
)
