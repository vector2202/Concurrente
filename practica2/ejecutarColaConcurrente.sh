#!/bin/bash

BUILD_DIR="build"
if [ ! -d "$BUILD_DIR" ]; then
  mkdir $BUILD_DIR
fi

echo "Compilando ColaConcurrente ..."
javac -d $BUILD_DIR src/ColaConcurrente.java src/Nodo.java

if [ $? -eq 0 ]; then
  echo "Ejecutando ColaConcurrente..."
  java -cp $BUILD_DIR ColaConcurrente
else
  echo "Error en la compilación."
fi
