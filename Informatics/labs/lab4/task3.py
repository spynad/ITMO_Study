#Ахтунг! Здесь я рекомендую делать всё одной регуляркой, ибо в 2021 году 
#за такой вариант будут ставить 0,8 от максимального количества баллов за третье задание.

import re


def task3():
    for i in range(11, 13):
        input = open('tests_3/' + str(i) + '.txt', 'r', encoding="utf-8")
        nextLine = input.readline()
        lines = ''
        while nextLine:
            lines = lines + nextLine
            nextLine = input.readline()
        result = re.match(r'^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+\.[a-zA-Z-.]+', lines)
        if result:
            result = re.search(r'(?<=@)[a-zA-Z0-9-]+\.[a-zA-Z-.]+', lines)
            print(result.group(0))
        else:
            print('incorrect')
        input.close()


task3()
