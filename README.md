# Playground

[![CircleCI](https://circleci.com/gh/bkodirov/Playground-Android.svg?style=svg)](https://circleci.com/gh/bkodirov/Playground-Android)
[![codecov](https://codecov.io/gh/bkodirov/Playground-Android-/branch/master/graph/badge.svg)](https://codecov.io/gh/bkodirov/Playground-Android-)

# The purpose of the project
Project follows various purposes:
1. New arch component in real life scenario.
    - How to use them;
    - How to unit test components;
2. Usage of the Dagger2. Injecting mock implementations during instrumentation tests

# How to build
sh gradlew assemble -  To assemble all build types
sh gradlew testTODO - To run Unit tests
sh gradlew connectedTestTODO - To run instrumented tests
sh compile_and_test.sh - Script which assembles apk files, runs unit tests, sends apk to Google Test Lab to run instrumented tests

# CI/CD pipeline
This project uses Circle CI as a Continuous Integration system.
Every PR or push to certain branches kicks off CI pipeline.
Currently CI has following steps:
    - Compile the project
    - Build APK files
    - Run Unit tests
    - Run instrumentation tests on Google Cloud
    - Collect test results and report on the PR.

## TODOs
- Remove Dagger module inheritance. Instead use [suggested approach by Dagger Authors.](https://google.github.io/dagger/testing.html)
- CI/CD process should be completed
- SetUp JaCoCo properly 
- HTTP and IO errors should be displayed properly on UI
- Integrating analytics
- Showcases for Feature Flag(Like LaunchDarkly)


