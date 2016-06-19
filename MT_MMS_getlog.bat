echo off
cd test_MO
if not exist MT_logs. (
 mkdir MT_logs
)else (
 del /Q .\MT_logs\*
)
adb pull /data/local/tmp/  .\MT_logs
adb shell "cat /data/local/tmp/MMS_framework.log" > .\MT_logs\MMS_framework.log 
adb shell "cat /data/local/tmp/MMS_RIL.log" > .\MT_logs\MMS_RIL.log.log 
echo "done"
pause
