echo Switching to NDK $NDK_VERSION
mv android-ndk-$NDK_VERSION $ANDROID_HOME/ndk-bundle
RESULT=$?; if [ $RESULT -ne 0 ]; then exit $RESULT; fi;

./gradlew assembleAndroidTest --stacktrace
