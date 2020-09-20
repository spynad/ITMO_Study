#!/bin/bash
cd lab0
wc -m *a */*a */*/*a 1> /tmp/res1 2>/dev/null
ls -l * */* */*/* 2>&1 | grep -v total | sort -rk5 | head -2
cat -n *1 */*1 */*/*1 2>&1 | sort 
ls -lrtu *la* */*la* */*/*la* 2>/tmp/res2
ls -lt g* */g* */*/g* 2>&1 | egrep "^-|^l" | sort -r -k6M -k7n
ls -l *1 */*1 */*/*1 2>/dev/null | grep "1$" | sort -r
cd ~