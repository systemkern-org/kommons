package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CombinatoricsTests {

    @Test fun `Can calculate number of nodes in Tree`() {
        assertThat(calculateTreeSize(branches = 2, treeDepth = 2)).isEqualTo(7)
        assertThat(calculateTreeSize(branches = 3, treeDepth = 3)).isEqualTo(40)
        assertThat(calculateTreeSize(branches = 4, treeDepth = 3)).isEqualTo(85)
        assertThat(calculateTreeSize(branches = 2, treeDepth = 10)).isEqualTo(2_047)
        assertThat(calculateTreeSize(branches = 3, treeDepth = 8)).isEqualTo(9_841)
    }
}
