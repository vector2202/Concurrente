#!/bin/bash

BUILD_DIR="build"
if [ ! -d "$BUILD_DIR" ]; then
  mkdir $BUILD_DIR
fi

echo "Compilando ColaConcurrenteLock..."
javac -d $BUILD_DIR src/ColaConcurrenteLock.java src/Nodo.java

if [ $? -eq 0 ]; then
  echo "Ejecutando ColaConcurrenteLock..."
  java -cp $BUILD_DIR ColaConcurrenteLock
else
  echo "Error en la compilación."
fi
