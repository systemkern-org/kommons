package com.systemkern.kommons.traversal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RecursiveDepthFirstSearchTests {

    @Test fun `Can Navigate visit tree`() {
        val visited = mutableSetOf<TestTreeNode>()
        depthFirstSearch(
            root = simpleTreeRoot,
            getBranches = { it.neighbours }
        ) { node ->
            visited.add(node)
        }

        assertThat(visited.size).isEqualTo(simpleTreeSize)
        assertThat(visited).contains(simpleTreeRoot)
        assertThat(visited).containsAll(simpleTreeRoot.recursiveChildren)
        assertThat(visited).containsOnly(simpleTreeRoot, *simpleTreeRoot.recursiveChildren.toTypedArray())
    }

    @Test fun `Can Navigate eval tree`() {
        val visited = mutableSetOf<TestTreeNode>()
        depthFirstSearch<TestTreeNode>(
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
