package com.liander

import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test

class StakeMathTest {

    private val e18 = BigInteger.TEN.pow(18)
    private val rate = BigInteger.valueOf(1_000_000)
    private val amount = BigInteger.valueOf(1000) * e18

    @Test
    fun rewardMatchesTheFormula() {
        // 1000 tokens, rate 1e6, 100_000s -> 100 tokens (matches the Solidity/Anchor tests)
        assertEquals(BigInteger.valueOf(100) * e18, StakeMath.reward(amount, 100_000, rate))
    }

    @Test
    fun scalesWithDuration() {
        assertEquals(BigInteger.valueOf(150) * e18, StakeMath.reward(amount, 150_000, rate))
    }

    @Test
    fun zeroWhenNoTimeElapsed() {
        assertEquals(BigInteger.ZERO, StakeMath.reward(amount, 0, rate))
    }

    @Test
    fun rejectsNegativeInputs() {
        assertFailsWith<IllegalArgumentException> { StakeMath.reward(BigInteger.valueOf(-1), 100, rate) }
        assertFailsWith<IllegalArgumentException> { StakeMath.reward(amount, -1, rate) }
    }
}
