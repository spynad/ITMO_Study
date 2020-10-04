#!/bin/bash
cd lab0
wc -m *a */*a */*/*a 1> /tmp/res1 2>/dev/null
(ls -l * */* */*/* | grep -v total | sort -rk5 | head -2) 2>&1
cat -n *1 */*1 */*/*1 2>&1 | sort 
ls -lrtu *la* */*la* */*/*la* 2>/tmp/res2
(ls -lt g* */g* */*/g* | egrep "^-|^l" | sort -r -k6M -k7n) 2>&1
(ls -l *1 */*1 */*/*1 | grep "1$" | sort -r) 2>/dev/null
cd ~