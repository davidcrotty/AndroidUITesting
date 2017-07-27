# Purpose

This project was uploaded to learn the caveats of espresso UI Testing with the Android Media Player framework. Secondly
the objective is to look at some of the common use cases for espresso and how to get it to work, for example multiple
Activities with Intents.

With Google continiously changing the Espresso API's it would be good to draw up a list of common scenarios and provide how to's
on usage for not only my own learning, but other's who experience the same problems.

# Branches

master - current development branch
experiment/disable_animations - Looking at the complexity of disabling animations during UI Testing. This branch is now
considered closed as the current method is as simple as updating your gradle plugin (2.3.0). And inserting the following into
your modules build.gradle you are testing on.

```
android {
    testOptions {
        animationsDisabled = true
    }
}
```

# Future developments
 * Provide a usage example with RX Schedulers
 * Demonstrate ToothPick dependency injection through tests
