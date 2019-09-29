@echo off

for /f "delims=" %%b in ('dir *.settings /b /ad /s') do @rd /q /s "%%b"

for /f "delims=" %%b in ('dir *.externalToolBuilders /b /ad /s') do @rd /q /s "%%b"

del /f /s /q .classpath

del /f /s /q .project

mvn clean
