#start to execute
echo on
echo "Start time: %date %time"
adb wait-for-device
adb shell rm /data/local/tmp/*
start adb shell "logcat time > /data/local/tmp/framework.log &"
adb wait-for-device
adb push C:\Users\user\AndroidStudioProjects\uiautomator_1\app\build\outputs\apk\debug\app-debug.apk /data/local/tmp/hami.cht.com.uiautomator_1
adb shell pm install -t -r "/data/local/tmp/hami.cht.com.uiautomator_1"
adb push C:\Users\user\AndroidStudioProjects\uiautomator_1\app\build\outputs\apk\androidTest\debug\app-debug-androidTest.apk /data/local/tmp/hami.cht.com.uiautomator_1.test
adb shell pm install -t -r "/data/local/tmp/hami.cht.com.uiautomator_1.test"
adb shell am "instrument -w -r   -e debug false -e class 'hami.cht.com.uiautomator_1.UIAutomatorTest_1Test#testFlow' hami.cht.com.uiautomator_1.test/android.support.test.runner.AndroidJUnitRunner &"
pause
