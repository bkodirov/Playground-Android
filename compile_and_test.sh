#!/bin/bash
sh gradlew androidDependencies
sh gradlew --stacktrace :app:assemble :app:assembleAndroidTest --max-workers=8
sh gradlew --stacktrace :app:assemble :app:assembleAndroidTest --max-workers=8
echo "y" | gcloud firebase test android run \
              --type instrumentation \
              --device model=walleye,version=28,locale=en,orientation=portrait \
              --app app/build/outputs/apk/TODO/debug/*.apk \
              --test app/build/outputs/apk/androidTest/TODO/debug/*.apk \
              --directories-to-pull /sdcard/ \
              --environment-variables coverage=true,coverageFile=/sdcard/coverage.ec \
              --no-auto-google-login \
              --results-bucket gs://fubotv-dev-test-lab-results 2>&1 | tee -i labresults.txt