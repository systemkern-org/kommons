package com.systemkern.kommons.traversal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StackBasedDepthFirstSearchTests {

    @Test fun `Can Navigate visit tree`() {
        val visited = mutableSetOf<TestTreeNode>()
        stackBasedDepthFirstSearch(
            root = simpleTreeRoot,
            getBranches = { it.neighbours }
        ) {
            visited.add(it)
        }

        assertThat(visited.size).isEqualTo(simpleTreeSize)
        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
    }

    @Test fun `Can Navigate eval tree`() {
        val visited = mutableSetOf<TestTreeNode>()
        stackBasedDepthFirstSearch(
            root = simpleTreeRoot,
            getBranches = { it.neighbours }
        ) {
            visited.add(it)
        }

        assertThat(visited.size).isEqualTo(simpleTreeSize)
        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
    }

}
