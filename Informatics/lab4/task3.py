import re


def task3():
    for i in range(1, 11):
        input = open('tests_3/' + str(i) + '.txt', 'r', encoding="utf-8")
        nextLine = input.readline()
        lines = ''
        while nextLine:
            lines = lines + nextLine
            nextLine = input.readline()
        result = re.match(r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z-.]+', lines)
        if result:
            result = re.search(r'(?<=@)[a-zA-Z0-9-]+\.[a-zA-Z-.]+', lines)
            print(result.group(0))
        else:
            print('incorrect')


task3()
