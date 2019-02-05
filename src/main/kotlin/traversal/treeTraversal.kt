package com.systemkern.kommons.traversal

import com.systemkern.kommons.MutableTriple
import com.systemkern.kommons.traversal.GcMode.IMMEDIATE
import com.systemkern.kommons.traversal.GcMode.LEVEL
import com.systemkern.kommons.traversal.GcMode.NONE

inline fun <N> depthFirstSearch(
    root: N,
    noinline getBranches: (N) -> Iterable<N>,
    crossinline action: (N) -> Unit
) {
    depthFirstSearch<N, Unit>(
        root = root,
        getBranches = getBranches
    ) { it: N, _: Any -> action(it) }
}

fun <N, R> depthFirstSearch(
    root: N,
    getBranches: (N) -> Iterable<N>,
    eval: (N, List<R>) -> R
): R = getBranches(root)
    .map {
        depthFirstSearch(
            root = it,
            getBranches = getBranches,
            eval = eval
        )
    }
    .let { eval(root, it) }


inline fun <reified N : Any> iterativeBreadthFirstSearch(
    gcMode: GcMode = IMMEDIATE,
    root: N,
    getBranches: (N) -> Collection<N> = { listOf() },
    action: (N) -> Unit = {}
): List<N?> {
    val ar = arrayListOf<N?>(root)
    var i = 0
    var curLevel = 0
    var prevStart = 0
    var endIndex = 0
    var startIndex = 1
    while (ar.size > i) {
        with(ar[i] ?: break) {
            val neighbours = getBranches(this)
            ar.addAll(neighbours)
            endIndex += neighbours.size
            action(this)
        }

        when (gcMode) {
            IMMEDIATE -> ar[i] = null
            LEVEL -> {
                //moving to next level
                if (i >= startIndex) {
                    curLevel++
                    //garbage collect previous level
                    for (j in startIndex downTo prevStart)
                        ar[j] = null
                    prevStart = startIndex
                    startIndex = endIndex
                }
            }
            NONE -> {
            }
        }

        i++
    }

    return ar
}

inline fun <reified N> iterativeDepthFirstSearch(
    root: N,
    getBranches: (N) -> Collection<N> = { listOf() },
    action: (N) -> Unit = {}
) {
    // Array of Node, Children and current Index in Children
    // Each array entry represents one level in the tree that we are working on

    val ar: MutableList<MutableTriple<N, List<N>?, Int>?> =
        mutableListOf(MutableTriple(root, null as List<N>?, 0))
    //(0..100).map { null }.toMutableList()

    // initialise root level
    ar[0] = MutableTriple(root, null as List<N>?, -1)

    // the level is also the index in the main array
    var currentLevel = 0
    while (currentLevel >= 0) {
        val it = ar[currentLevel] ?: break
        val node = it.first
        // if neighbours index is at -1 it is a new neighbourhood, so first evaluate node
        if (it.third == -1) {
            action(node)
            it.third = 0
            continue
        }

        val neighbours = it.second ?: getBranches(node).toList()

        // check if next all neighbours have been visited
        if (it.third >= neighbours.size) {
            // all neighbours have been visited ==> immediate GC
            ar[currentLevel] = null
            currentLevel--
            continue
        }

        //init next level node
        MutableTriple(neighbours[it.third], null as List<N>?, -1)
            .let {
                when {
                    ar.size == currentLevel + 1 -> ar.add(it)
                    ar.size >= currentLevel -> ar[currentLevel + 1] = it
                    else -> throw RuntimeException()
                }
            }
        // increase current level's index
        it.third = it.third + 1
        //move to next level
        currentLevel++
    }
}

enum class GcMode {
    NONE, IMMEDIATE, LEVEL
}
