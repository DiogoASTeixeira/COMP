#!/bin/bash

jjtree $1.jjt
javacc $1.jj
javac *.java