# Stake Math (Kotlin)

A small **Kotlin** library (Maven + `kotlin-maven-plugin` + `kotlin.test`) implementing the staking reward math from my on-chain vault:

```
reward = stakedAmount * elapsedSeconds * rewardRate / ACC_PRECISION   (ACC_PRECISION = 1e12)
```

It's the Kotlin version of the same formula I built in Solidity, Rust/Anchor, and Java. `BigInteger` keeps wei-scale amounts exact, and inputs are validated with `require`.

## Build & test

```bash
mvn test
```

Tests confirm the reward matches the reference values (100 tokens over 100,000s, 150 over 150,000s — the same numbers verified in the Solidity and Anchor suites), zero when no time elapses, and rejection of negative inputs. Runs in CI on every push.

## Files

```
src/main/kotlin/com/liander/StakeMath.kt        the reward calculation
src/test/kotlin/com/liander/StakeMathTest.kt     kotlin.test + JUnit 5 tests
pom.xml                                           Maven + kotlin-maven-plugin
```

## Stack

- **Kotlin 2.0**, **Maven**, **JUnit 5** / `kotlin.test`

## License

MIT
