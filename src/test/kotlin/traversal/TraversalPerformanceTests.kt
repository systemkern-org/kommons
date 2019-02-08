package com.systemkern.kommons.traversal

import com.systemkern.kommons.MutableTriple
import com.systemkern.kommons.calculateTreeSize
import com.systemkern.kommons.l
import com.systemkern.kommons.to
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.text.NumberFormat
import java.time.Duration.between
import java.time.LocalTime.now
import kotlin.system.measureTimeMillis

@Disabled
internal class TraversalPerformanceTests {

    private var algorithmWithLimit: List<MutableTriple<String, (branches: Int, treeDepth: Int) -> Int, Long>> =
        listOf(
            MutableTriple(
                "Recursive Depth-First             ",
                recursiveDepthFirst,
                Long.MAX_VALUE
            ),
            MutableTriple(
                "Stack Based Depth-First           ",
                stackBasedDepthFirstSearch,
                Long.MAX_VALUE
            ),
            MutableTriple(
                "Iterative Referenced Breadth-First",
                iterativeBreadthFirstWithReferrences,
                Long.MAX_VALUE
            ),
            MutableTriple(
                "Iterative Breadth-First           ",
                iterativeBreadthFirst,
                Long.MAX_VALUE
            ),
            MutableTriple(
                "Iterative Depth-First             ",
                iterativeDepthFirst,
                Long.MAX_VALUE
            )
        )

    @Test fun `Run Performance Comparison`() {
        val minBranches = 2
        val maxBranches = 10
        val minimumDepth = 2
        val maximumDepth = 30

        (minBranches..maxBranches)
            .flatMap { branches ->
                (minimumDepth..maximumDepth)
                    .map { depth -> branches to depth to calculateTreeSize(branches = branches, treeDepth = depth) }
            }
            .filter { it.third > 0 } // some values actually roll over to negative
            .sortedBy { it.third }
            .forEachIndexed { idx, (branches, depth, expected) ->

                println("---- Test Set $idx, branches $branches, depth: $depth, nodes: ${expected.str} ----")
                for (triple in algorithmWithLimit) {
                    val (name, algorithm, limit) = triple
                    if (expected > limit)
                        continue

                    val start = now()
                    try {
                        val duration = measureTimeMillis {
                            val count = algorithm(branches, depth)
                            assertThat(count).isEqualTo(expected.l)
                        }
                        println("$name: visited ${expected.str} nodes in ${duration / 1000}s")
                    } catch (t: Throwable) {
                        println("$name: could not complete for ${expected.str} nodes: time to fail: ${between(start, now()).toMillis() / 1000}s -- ${t::class.simpleName}")
                        //set new limit for this algorithm
                        triple.third = expected.l
                    }
                }
                println("")
            }
    }
}

private val Number.str: String get() = NumberFormat.getInstance().format(this)


private val recursiveDepthFirst: (branches: Int, treeDepth: Int) -> Int = { branches, treeDepth ->
    var count = 0
    depthFirstSearch(
        root = TestTreeNode()
            .apply { generateNeighbours(branches, treeDepth) },
        getBranches = { it.neighbours }
    ) { _ ->
        count++
    }
    count
}

private val stackBasedDepthFirstSearch: (branches: Int, treeDepth: Int) -> Int = { branches, treeDepth ->
    var count = 0
    stackBasedDepthFirstSearch(
        root = TestTreeNode(),
        getBranches = { node ->
            if (node.depth >= treeDepth) listOf()
            else (1..branches).map {
                TestTreeNode()
                    .apply { depth = node.depth + 1 }
            }
        }
    ) {
        count++
    }
    count
}

private val iterativeBreadthFirstWithReferrences: (branches: Int, treeDepth: Int) -> Int = { branches, treeDepth ->
    iterativeBreadthFirstSearch(
        root = TestTreeNode(),
        getBranches = {
            if (it.depth < treeDepth) {
                it.generateNeighbours(n = branches, depth = 1)
            }
            it.neighbours
        }
    ).size
}

private val iterativeBreadthFirst: (branches: Int, treeDepth: Int) -> Int = { branches, treeDepth ->
    iterativeBreadthFirstSearch(
        root = TestTreeNode(),
        getBranches = { node ->
            if (node.depth >= treeDepth) listOf()
            else (1..branches).map {
                TestTreeNode()
                    .apply { depth = node.depth + 1 }
            }
        }
    ).size
}

private val iterativeDepthFirst: (branches: Int, treeDepth: Int) -> Int = { branches, treeDepth ->
    var count = 0
    iterativeDepthFirstSearch(
        root = TestTreeNode(),
        getBranches = { node ->
            if (node.depth >= treeDepth) listOf()
            else (1..branches).map {
                TestTreeNode()
                    .apply { depth = node.depth + 1 }
            }
        }
    ) {
        count++
    }
    count
}
