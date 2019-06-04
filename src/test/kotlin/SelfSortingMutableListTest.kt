package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SelfSortingMutableListTest {

    @Test fun addAndSortSeveralElements(){
        val ssmList = SelfSortingMutableList<Int>()
        ssmList.add(8)
        ssmList.add(2)
        ssmList.add(1)
        ssmList.add(4)
        ssmList.add(3)

        assertThat(ssmList[0]).isEqualTo(1)
        assertThat(ssmList[1]).isEqualTo(2)
        assertThat(ssmList[2]).isEqualTo(3)
        assertThat(ssmList[3]).isEqualTo(4)
        assertThat(ssmList[4]).isEqualTo(8)
    }

    @Test fun addNegativeElements(){
        val ssmList = SelfSortingMutableList<Int>()
        ssmList.add(-8)
        ssmList.add(-2)
        ssmList.add(1)
        ssmList.add(4)
        ssmList.add(3)

        assertThat(ssmList[0]).isEqualTo(-8)
        assertThat(ssmList[1]).isEqualTo(-2)
        assertThat(ssmList[2]).isEqualTo(1)
        assertThat(ssmList[3]).isEqualTo(3)
        assertThat(ssmList[4]).isEqualTo(4)
    }

}