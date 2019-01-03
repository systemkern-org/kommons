package systemkern.extensions

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

private val halIgnoreFields = setOf(
    "com.fasterxml.jackson.annotation.JsonIgnore"
)

internal fun <T : Any> KClass<T>.getEntityProperties(): List<KProperty1<T, *>> =
    declaredMemberProperties
        .filter { it.name != "id" }
        .filterNot { it.isHalRelevant() }
        .filterNot {
            it.annotations.any {
                it.annotationClass.qualifiedName in halIgnoreFields
            }
        }


internal fun <T : Any> KClass<T>.getHALRelevantProperties(): List<KProperty1<T, *>> =
    declaredMemberProperties
        .filter { it.name != "id" }
        .filter { it.isHalRelevant() }


private val halRelevantAnnotationClasses = setOf(
    "javax.persistence.OneToOne",
    "javax.persistence.OneToMany",
    "javax.persistence.ManyToOne",
    "javax.persistence.ManyToMany"
)

private fun <T : Any> KProperty1<T, *>.isHalRelevant(): Boolean =
    (this.returnType.isCollection() // return all collections
        // all entity classes
        || (this.returnType.classifier as KClass<*>)
        .annotations
        .any { it.annotationClass.qualifiedName == "javax.persistence.Entity" }
        // and all classes that are annotated correctly
        || this.annotations.any { it.annotationClass.qualifiedName in halRelevantAnnotationClasses }
        )
