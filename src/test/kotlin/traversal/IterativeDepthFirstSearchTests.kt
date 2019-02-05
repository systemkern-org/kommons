package com.systemkern.kommons.traversal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IterativeDepthFirstSearchTests {

    @Test fun `Can traverse simple tree`() {
        val visited = mutableListOf<TestTreeNode>()
        iterativeDepthFirstSearch(
            root = simpleTreeRoot,
            getBranches = { it.neighbours }
        ) {
            visited.add(it)
        }

        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).contains(*simpleTreeRoot.neighbours.toTypedArray())
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
        assertThat(visited.size).isEqualTo(simpleTreeSize)
    }
}

