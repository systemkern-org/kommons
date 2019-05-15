package com.systemkern.kommons.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable
import java.util.Optional
import javax.persistence.EntityNotFoundException

@NoRepositoryBean
interface KtCrudRepository<T, ID : Serializable> : CrudRepository<T, ID> {

    override fun existsById(id: ID): Boolean

    override fun findAll(): Iterable<T>

    override fun findAllById(ids: Iterable<ID>): Iterable<T>

    override fun <S : T> save(entity: S): S

    override fun <S : T> saveAll(entities: Iterable<S>): Iterable<S>

    @Deprecated(
        message = "Java Optional is not the preferable way to handle database returns",
        replaceWith = ReplaceWith("systemkern.spring.KtCrudRepository.findById2(id)")
    )
    override fun findById(id: ID): Optional<T>

    override fun delete(entity: T)

    override fun deleteById(id: ID)

    override fun deleteAll(ids: Iterable<T>)
}

/**
 * unwraps the optional returned by findById to either the entity or null
 * @see CrudRepository.findById
 */
@Suppress("DEPRECATION")
fun <T, ID : Serializable> KtCrudRepository<T, ID>.findById2(id: ID): T? =
    findById(id).orElse(null)

/**
 * @throws javax.persistence.EntityNotFoundException if no entity can be found
 */
@Throws(EntityNotFoundException::class)
inline fun <reified T, ID : Serializable> KtCrudRepository<T, ID>.getById(id: ID): T =
    findById2(id) ?: throw  EntityNotFoundException("Entity of type ${T::class} with id: $id was not found.")
