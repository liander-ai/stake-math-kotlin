package com.liander

import java.math.BigInteger

/**
 * Staking reward math, mirroring the on-chain vault I built on Solana and EVM:
 *
 *   reward = stakedAmount * elapsedSeconds * rewardRate / ACC_PRECISION
 *
 * rewardRate is scaled by ACC_PRECISION (1e12), and BigInteger is used so
 * wei-scale token amounts never overflow.
 */
object StakeMath {

    /** Fixed-point scaling for rewardRate (1e12). */
    val ACC_PRECISION: BigInteger = BigInteger.valueOf(1_000_000_000_000L)

    fun reward(amount: BigInteger, elapsedSeconds: Long, rewardRate: BigInteger): BigInteger {
        require(amount.signum() >= 0) { "amount must be >= 0" }
        require(elapsedSeconds >= 0) { "elapsedSeconds must be >= 0" }
        require(rewardRate.signum() >= 0) { "rewardRate must be >= 0" }
        return amount
            .multiply(BigInteger.valueOf(elapsedSeconds))
            .multiply(rewardRate)
            .divide(ACC_PRECISION)
    }
}
