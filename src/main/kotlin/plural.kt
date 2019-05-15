package com.systemkern.kommons

/**
 * Most singular nouns form the plural by adding -s.
 * A singular noun ending in s, x, z, ch, sh makes the plural by adding-es.
 * A singular noun ending in a consonant and then y makes the plural by dropping the y and adding-ies.
 */
val String.plural: String
    get() = when {
        irregularNouns.containsKey(this.toLowerCase()) -> irregularNouns[this.toLowerCase()]!!
        "y" == this.takeLast(1).toLowerCase() -> this.dropLast(1) + "ies"
        setOf("s", "x", "z", "ch", "sh").contains(this.takeLast(1).toLowerCase()) -> this + "es"
        else -> this + "s"
    }

private val irregularNouns = mapOf(
    "analysis" to "analyses",
    "cactus" to "cacti",
    "child" to "children",
    "crisis" to "crises",
    "criterion" to "criteria",
    "datum" to "data",
    "diagnosis" to "diagnoses",
    "elf" to "elves",
    "focus" to "foci",
    "foot" to "feet",
    "fungus" to "fungi",
    "goose" to "geese",
    "half" to "halves",
    "knife" to "knives",
    "leaf" to "leaves",
    "life" to "lives",
    "loaf" to "loaves",
    "man" to "men",
    "mouse" to "mice",
    "nucleus" to "nuclei",
    "person" to "people",
    "oasis" to "oases",
    "phenomenon" to "phenomena",
    "potato" to "potatoes",
    "syllabus" to "syllabi",
    "tomato" to "tomatoes",
    "toy" to "toys",
    "tooth" to "teeth",
    "thesis" to "theses",
    "wife" to "wives",
    "woman" to "women",

    //also only plural nouns
    "aircraft" to "aircraft",
    "athletics" to "athletics",
    "billards" to "billards",
    "deer" to "deer",
    "darts" to "darts",
    "fish" to "fish",
    "glasses" to "glasses",
    "linguistics" to "linguistics",
    "jeans" to "jeans",
    "sheep" to "sheep",
    "species" to "species",
    "trousers" to "trousers"
)
