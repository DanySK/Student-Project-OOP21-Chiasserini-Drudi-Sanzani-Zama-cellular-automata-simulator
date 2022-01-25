# Cellular Automaton Simulator

[![Build](https://github.com/FiloSanza/ca-sim/actions/workflows/gradle.yml/badge.svg)](https://github.com/FiloSanza/ca-sim/actions/workflows/gradle.yml)

ca-sim is a cellular automaton simulator, it supports:
- [ ] [Conway's Game Of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)
- [ ] [Rule 110](https://en.wikipedia.org/wiki/Rule_110)
- [ ] [CoDi](https://en.wikipedia.org/wiki/CoDi)
- [ ] [Langton's Ant](https://en.wikipedia.org/wiki/Langton%27s_ant)
- [ ] [Predators and Prey](https://en.wikipedia.org/wiki/Wa-Tor)

## Build

You can build ca-sim using Gradle, first you have to clone the repo:

```bash
git clone git@github.com:FiloSanza/ca-sim.git
```

You can then build it on linux or mac-os with:

```bash
cd ca-sim
./gradlew build
```

If you are on Windows you can use:

```cmd
cd ca-sim
gradlew.bat build
```

The gradle scripts automatically executes additional checks using PMD, SpotBugs and CheckStyle, if you want to disable them you can run (on linux and mac-os):

```bash
./gradle build -x check       #to disable all the checks
./gradle build -x [checkName] #checkName can be checkstyle[Main|Test] pmd[Main|Test] spotbugs[Main|Test]
```

If you are on Windows you can use:

```cmd
rem to disable all the checks
gradlew.bat build -x check
rem checkName can be checkstyle[Main|Test] pmd[Main|Test] spotbugs[Main|Test]
gradlew.bat build -x [checkName] 
```