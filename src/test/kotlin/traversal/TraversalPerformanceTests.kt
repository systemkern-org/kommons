package com.systemkern.kommons.traversal

import com.systemkern.kommons.MutableTriple
import com.systemkern.kommons.d
import com.systemkern.kommons.i
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.lang.Math.pow
import java.text.NumberFormat
import java.time.Duration.between
import java.time.LocalTime.now
import kotlin.system.measureTimeMillis

@Disabled
internal class TraversalPerformanceTests {

    private var algorithmWithLimit: List<MutableTriple<String, (branches: Int, treeDepth: Int) -> Int, Int>> =
        listOf(
            MutableTriple(
                "Iterative Breadth-First 1",
                iterativeBreadthFirstWithReferrences,
                Int.MAX_VALUE
            ),
            MutableTriple(
                "Iterative Breadth-First 2",
                iterativeBreadthFirst,
                Int.MAX_VALUE
            ),
            MutableTriple(
                "Iterative Depth-First    ",
                iterativeDepthFirst,
                Int.MAX_VALUE
            ),
            MutableTriple(
                "Recursive Depth-First    ",
                recursiveDepthFirst,
                Int.MAX_VALUE
            )
        )

    @Test fun `Run Performance Comparison`() {
        val minBranches = 2
        val maxBranches = 2
        val minimumDepth = 24
        val maximumDepth = 30

        (minBranches..maxBranches)
            .flatMap { br -> (minimumDepth..maximumDepth).map { br to it } }
            .forEachIndexed { idx, (branches, depth) ->
                val expectedNodes = (pow(branches.d, depth.d).i * 2 - 1)
                val expectedNodesStr = NumberFormat.getIntegerInstance().format(expectedNodes)

                println("---- Test Set $idx,branches $branches, depth: $depth, nodes: $expectedNodesStr ----")
                for (triple in algorithmWithLimit) {
                    val (name, algorithm, limit) = triple
                    if (expectedNodes > limit) {
                        println("$name: nodes: $expectedNodesStr, limit: $limit -- SKIPPED")
                        continue
                    }

                    val duration = measureTimeMillis {
                        val start = now()
                        try {
                            val count = algorithm(branches, depth)
                            assertThat(count).isEqualTo(expectedNodes)
                        } catch (t: Throwable) {
                            println("$name: could not complete for $expectedNodesStr nodes: time to fail: ${between(start, now()).toMillis() / 1000}s -- ${t::class.simpleName}")
                            //set new limit for this algorithm
                            triple.third = expectedNodes
                        }
                    }
                    println("$name: visited $expectedNodesStr nodes in ${duration / 1000}s")
                }
                println("")
            }
    }
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
