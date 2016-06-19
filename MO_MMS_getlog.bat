echo off
cd test_MO
if not exist MO_logs. (
 mkdir MO_logs
)else (
 del /Q .\MO_logs\*
)
adb pull /data/local/tmp/  .\MO_logs
adb shell "cat /data/local/tmp/test_MO_WLAN.log" > .\MO_logs\test_MO_WLAN.log 
# adb shell "cat /data/local/tmp/MMS_RIL.log" > .\MO_logs\MMS_RIL.log.log 
echo "done"
pause
