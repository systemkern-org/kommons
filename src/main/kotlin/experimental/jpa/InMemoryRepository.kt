package com.systemkern.kommons.experimental.jpa

import com.systemkern.kommons.jpa.KtCrudRepository
import java.io.Serializable
import java.util.Optional
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

/**
 * The InMemoryRepository is intended mainly for testing purposes.
 * It emulates the basic CRUD functionality of a SpringData Repository but completely in memory.
 * Also findById(), getById() and delteById() work as expected
 */
class InMemoryRepository<T : Any, ID : Serializable>(
    private val entityClass: KClass<T>,
    private val population: MutableMap<ID, T> = mutableMapOf()
) : KtCrudRepository<T, ID> {
    override fun <S : T> save(entity: S): S = entity
        .also { population[it.getId(entityClass)] = it }

    override fun <S : T> saveAll(entities: Iterable<S>): Iterable<S> = entities.map { this.save(it) }

    override fun count(): Long = population.count().toLong()
    override fun existsById(id: ID): Boolean = population.containsKey(id)
    override fun findAll() = population.values

    @Deprecated(message = "Optional is not easy to use", replaceWith = ReplaceWith("findById2(id)"))
    override fun findById(id: ID) =
        Optional.ofNullable(population[id])

    override fun findAllById(ids: Iterable<ID>): Iterable<T> =
        population.filter { it.key in ids }.values

    override fun deleteById(id: ID) {
        population.remove(id)
    }

    override fun deleteAll(ids: Iterable<T>) =
        ids.map { it.getId<T, ID>(entityClass) }
            .forEach { population.remove(it) }

    override fun delete(entity: T) {
        population.remove(entity.getId(entityClass))
    }

    override fun deleteAll() = population.clear()
}

private val acceptedIdAnnotations = setOf(
    "javax.persistence.Id",
    "org.springframework.data.annotation.Id"
)

// the Property annotated with @ID must be the right one
// otherwise there will be an exception
internal fun <T : Any, ID> T.getId(entityClass: KClass<T>): ID =
    (entityClass
        .declaredMemberProperties
        .single {
            it.javaField?.annotations?.any {
                it.annotationClass.qualifiedName in acceptedIdAnnotations
            } ?: false
        } as KProperty1<T, out ID>)
        .get(this)
