@echo off
setlocal
cd /d "%~dp0"

echo ==============================================
echo   Space Ore - Minecraft 1.21.1 NeoForge
echo ==============================================
where java >nul 2>nul || (echo Java 21 not found. Install 64-bit Java 21. & pause & exit /b 1)
java -version

if not exist .gradle-dist\gradle-8.8\bin\gradle.bat (
  echo.
  echo Downloading Gradle 8.8. Internet is required on first run...
  if not exist .gradle-dist mkdir .gradle-dist
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$u='https://services.gradle.org/distributions/gradle-8.8-bin.zip'; $o='.gradle-dist\gradle.zip'; Invoke-WebRequest -UseBasicParsing $u -OutFile $o; Expand-Archive -Force $o '.gradle-dist'; Remove-Item $o"
  if errorlevel 1 (echo Failed to download Gradle. Check your Internet connection. & pause & exit /b 1)
)

echo.
echo Building. The first build downloads Minecraft and NeoForge dependencies...
call .gradle-dist\gradle-8.8\bin\gradle.bat --no-daemon clean build
if errorlevel 1 (echo. & echo BUILD FAILED. & pause & exit /b 1)

echo.
echo BUILD SUCCESSFUL!
echo JAR: %cd%\build\libs\spaceore-1.0.0.jar
pause
