#!/bin/bash
cd lab0
cp -r gigalith1 amoonguss3/deino
#cp: amoonguss3/deino/gigalith1: Permission denied
chmod u+w amoonguss3
chmod u+w amoonguss3/deino
cp -r gigalith1 amoonguss3/deino
chmod u-w amoonguss3/deino
chmod u-w amoonguss3
cat muk6 > gigalith1/geoudemuk
#bash: gigalith1/geoudemuk: Permission denied
chmod u+w gigalith1
cat muk6 > gigalith1/geoudemuk
#cat: cannot open muk6
chmod u+r muk6
cat muk6 > gigalith1/geoudemuk
chmod u-r muk6
chmod u-w gigalith1
cat amoonguss3/steelix gigalith1/electrode > muk6_58
#cat: cannot open amoonguss3/steelix
chmod u+r amoonguss3/steelix
cat amoonguss3/steelix gigalith1/electrode > muk6_58
chmod u-r amoonguss3/steelix
cp muk6 woobat4/raichu
#cp: cannot open muk6: Permission denied
chmod u+r muk6
cp muk6 woobat4/raichu
chmod u-r muk6
ln -s amoonguss3 Copy_32
ln muk6 gigalith1/bunearymuk
#ln: cannot create link gigalith1/bunearymuk: Permission denied
chmod u+w gigalith1
ln muk6 gigalith1/bunearymuk
chmod u-w gigalith1
ln -s ../larvitar1 woobat4/geodudelarvitar
cd ~