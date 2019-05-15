package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PluralTests {

    @Test fun `Can get plural of regular nouns`() {
        assertThat("entity".plural).isEqualTo("entities")
        assertThat("car".plural).isEqualTo("cars")
    }

    @Test fun `Can get plural of irregular nouns`() {
        assertThat("analysis".plural).isEqualTo("analyses")
        assertThat("cactus".plural).isEqualTo("cacti")
        assertThat("child".plural).isEqualTo("children")
        assertThat("crisis".plural).isEqualTo("crises")
        assertThat("criterion".plural).isEqualTo("criteria")
        assertThat("datum".plural).isEqualTo("data")
        assertThat("diagnosis".plural).isEqualTo("diagnoses")
        assertThat("elf".plural).isEqualTo("elves")
        assertThat("focus".plural).isEqualTo("foci")
        assertThat("foot".plural).isEqualTo("feet")
        assertThat("fungus".plural).isEqualTo("fungi")
        assertThat("goose".plural).isEqualTo("geese")
        assertThat("half".plural).isEqualTo("halves")
        assertThat("knife".plural).isEqualTo("knives")
        assertThat("leaf".plural).isEqualTo("leaves")
        assertThat("life".plural).isEqualTo("lives")
        assertThat("loaf".plural).isEqualTo("loaves")
        assertThat("man".plural).isEqualTo("men")
        assertThat("mouse".plural).isEqualTo("mice")
        assertThat("nucleus".plural).isEqualTo("nuclei")
        assertThat("person".plural).isEqualTo("people")
        assertThat("oasis".plural).isEqualTo("oases")
        assertThat("phenomenon".plural).isEqualTo("phenomena")
        assertThat("potato".plural).isEqualTo("potatoes")
        assertThat("syllabus".plural).isEqualTo("syllabi")
        assertThat("tomato".plural).isEqualTo("tomatoes")
        assertThat("toy".plural).isEqualTo("toys")
        assertThat("tooth".plural).isEqualTo("teeth")
        assertThat("thesis".plural).isEqualTo("theses")
        assertThat("wife".plural).isEqualTo("wives")
        assertThat("woman".plural).isEqualTo("women")
    }

    @Test fun `Can get plural of plural only nouns`() {
        assertThat("aircraft".plural).isEqualTo("aircraft")
        assertThat("athletics".plural).isEqualTo("athletics")
        assertThat("billards".plural).isEqualTo("billards")
        assertThat("deer".plural).isEqualTo("deer")
        assertThat("darts".plural).isEqualTo("darts")
        assertThat("fish".plural).isEqualTo("fish")
        assertThat("glasses".plural).isEqualTo("glasses")
        assertThat("linguistics".plural).isEqualTo("linguistics")
        assertThat("jeans".plural).isEqualTo("jeans")
        assertThat("sheep".plural).isEqualTo("sheep")
        assertThat("species".plural).isEqualTo("species")
        assertThat("trousers".plural).isEqualTo("trousers")
    }

    @Test fun `Cannot get plural of already plural nouns`() {
        assertThat("foci".plural).isEqualTo("foci")
        assertThat("phenomena".plural).isEqualTo("phenomena")
    }

    @Test fun `What should we do with phrases`() {
        assertThat("This is a phrase".plural).isEqualTo("This is a phrases")
    }
}
