#!/bin/bash

BUILD_DIR="build"
if [ ! -d "$BUILD_DIR" ]; then
  mkdir $BUILD_DIR
fi

echo "Compilando Scheduler ..."
javac -d $BUILD_DIR src/Scheduler.java src/Tarea.java

if [ $? -eq 0 ]; then
  echo "Ejecutando Scheduler..."
  java -cp $BUILD_DIR Scheduler
else
  echo "Error en la compilación."
fi
