package com.systemkern.kommons.traversal

import org.junit.jupiter.api.Test

internal class NodeTests {

    @Test fun `Can create a Tree Datastructure`() {
        TestTreeNode(
            TestTreeNode(),
            TestTreeNode(
                TestTreeNode(),
                TestTreeNode(
                    TestTreeNode()
                ),
                TestTreeNode()
            ),
            TestTreeNode()
        )
    }
}


internal class TestTreeNode(
    val neighbours: MutableList<TestTreeNode> = mutableListOf<TestTreeNode>(),
    depth: Int = 0
) : List<TestTreeNode> by neighbours {
    constructor(vararg nodes: TestTreeNode) : this(neighbours = nodes.toMutableList())

    var depth: Int = depth
        internal set(value) {
            field = value
            neighbours.forEach {
                it.depth = value + 1
            }
        }

    var parent: TestTreeNode? = null
        internal set (value) {
            field = value
            if (value == null) this.depth = 0
            else this.depth = value.depth + 1
            neighbours.forEach { it.parent = this }
        }

    init {
        if (parent == null) {// I am root
            this.parent = null // trigger recursive setting
        }
    }
}


// This function is just another way of traversing the tree
// Actually a much simpler one
internal val TestTreeNode.recursiveChildren: List<TestTreeNode>
    get() =
        com.systemkern.kommons.flatten(this.neighbours, neighbours.flatMap { it.recursiveChildren })


internal fun TestTreeNode.generateNeighbours(n: Int, depth: Int) {
    if (depth <= 0) return
    repeat(n) {
        TestTreeNode().let {
            this.neighbours.add(it)
            it.parent = this
            it.generateNeighbours(n, depth - 1)
        }
    }
}

internal const val simpleTreeSize = 9
internal val simpleTreeRoot = TestTreeNode(
    TestTreeNode(),
    TestTreeNode(
        TestTreeNode(),
        TestTreeNode(
            TestTreeNode(),
            TestTreeNode()
        ),
        TestTreeNode()
    ),
    TestTreeNode()
)

/*
val TestTreeNode.path: List<TestTreeNode>
    get() {
        val res = mutableListOf(this)
        var cur: TestTreeNode = this
        while (cur.parent != null) {
            cur = cur.parent ?: break
            res.add(cur)
        }
        return res
    }
}
*/
