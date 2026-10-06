# Online Voting System

A console-based voter registration and voting system built with Core Java and OOP.
Project-Based Learning (PBL) project for Java Programming, Chennai Institute of Technology.

**Author:** Prince Kumar Soni (2104251040711), B.E. Computer Science and Engineering

## Features
- Register voters (minimum age 18, unique voter ID, case-insensitive)
- View the three pre-loaded candidates
- Cast exactly one vote per registered voter
- Ranked results
- Invalid console input handled without crashing

## Project structure
| File | Role |
|------|------|
| `User.java` | Abstract base class (name, age, abstract `displayInfo()`) |
| `Voter.java` | Extends `User`; adds voter ID and voted status |
| `Candidate.java` | Candidate ID, name, party, vote count |
| `VotingService.java` | Business rules and data (ArrayLists) |
| `Main.java` | Menu-driven console interface |
| `TestRunner.java` | 18 test cases (plain Java, no JUnit) |
| `Bench.java` | Timing and memory benchmark |

## Requirements
Any JDK 8 or newer. No external libraries.

## Build and run
```bash
cd src
javac *.java
java Main          # run the application
java TestRunner    # run the 18 test cases
java Bench         # run the benchmark
```

## Known limitations
- Data is in memory only (lost on exit); no database
- No voter authentication
- The "Leading" line in results is shown even for ties or zero votes
