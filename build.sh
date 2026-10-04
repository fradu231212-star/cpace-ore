#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
command -v java >/dev/null || { echo 'Install 64-bit Java 21 first.'; exit 1; }
if [ ! -x .gradle-dist/gradle-8.8/bin/gradle ]; then
  mkdir -p .gradle-dist
  curl -L https://services.gradle.org/distributions/gradle-8.8-bin.zip -o .gradle-dist/gradle.zip
  unzip -q -o .gradle-dist/gradle.zip -d .gradle-dist
  rm .gradle-dist/gradle.zip
fi
./.gradle-dist/gradle-8.8/bin/gradle --no-daemon clean build
echo "Built: build/libs/spaceore-1.0.0.jar"
