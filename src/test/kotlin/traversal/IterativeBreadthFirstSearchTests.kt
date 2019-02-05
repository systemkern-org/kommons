package com.systemkern.kommons.traversal

import com.systemkern.kommons.traversal.GcMode.LEVEL
import com.systemkern.kommons.traversal.GcMode.NONE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IterativeBreadthFirstSearchTests {

    @Test fun `Can traverse simple tree`() {
        val visited =
            iterativeBreadthFirstSearch(
                root = simpleTreeRoot,
                getBranches = { it.neighbours }
            )

        assertThat(visited.size).isEqualTo(simpleTreeSize)
        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
    }

    @Test fun `Can run without garbage collection`() {
        val visited =
            iterativeBreadthFirstSearch(
                gcMode = NONE,
                root = simpleTreeRoot,
                getBranches = { it.neighbours }
            )

        assertThat(visited.size).isEqualTo(simpleTreeSize)
        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
    }

    @Test fun `Can garbage collect per level`() {
        val visited =
            iterativeBreadthFirstSearch(
                gcMode = LEVEL,
                root = simpleTreeRoot,
                getBranches = { it.neighbours }
            )

        //size is still the same
        assertThat(visited.size).isEqualTo(simpleTreeSize)
        // visited should contain only the last level
        assertThat(visited).doesNotContain(simpleTreeRoot)
        assertThat(visited.filterNotNull()).isEmpty()
    }
}

