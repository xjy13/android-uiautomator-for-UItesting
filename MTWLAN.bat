echo on
echo "Starting time: %date% %time%"
adb wait-for-device
adb shell rm /data/local/tmp/*
start adb shell "logcat -b radio -b main -b events -b system -v time > /data/local/tmp/MTWLAN_android.log &"
adb wait-for-device
adb push tools/busybox /data/local/tmp
adb push tools/busyboxsh /data/local/tmp
adb shell chmod 777 /data/local/tmp/busybox
adb shell sh /data/local/tmp/busybox


REM Set up SendMMS device
adb push test_MT_WLAN /data/local/tmp
adb push "D:\My tool\IAC_SQA_MOMTWLAN\UiAutomator2.jar" /data/local/tmp
adb shell "sh /data/local/tmp/test_MT_WLAN > /data/local/tmp/test_MT_WLAN.log &"
pause

