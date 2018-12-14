package systemkern.extensions

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties


internal fun <T : Any> KClass<T>.getEntityProperties(): List<KProperty1<T, *>> =
    declaredMemberProperties
        .filter { it.name != "id" }
        .filterNot { it.isHalRelevant() }
        .filter {
            it.annotations.any() {
                setOf(JsonIgnore::class).contains(it.annotationClass)
            }
        }


internal fun <T : Any> KClass<T>.getHALRelevantProperties(): List<KProperty1<T, *>> =
    declaredMemberProperties
        .filter { it.name != "id" }
        .filter { it.isHalRelevant() }


private fun <T : Any> KProperty1<T, *>.isHalRelevant(): Boolean =
    (this.returnType.isCollection() // return all collections
        // all entity classes
        || (this.returnType.classifier as KClass<*>)
        .annotations
        .any { it.annotationClass == Entity::class }
        // and all classes that are annotated correctly
        || this.annotations.any {
        setOf(
            OneToOne::class,
            OneToMany::class,
            ManyToOne::class,
            ManyToMany::class
        ).contains(it.annotationClass)

    })

internal fun <T : CrudRepository<*, *>> KClass<T>.getAnnotationUrl(): String? {
    return (this.annotations.firstOrNull {
        it.annotationClass == RepositoryRestResource::class
    } as RepositoryRestResource?)
        ?.let {
            if (it.path.isNotEmpty()) "/" + it.path
            else null
        }
}