import re


def task1():
    for i in range(1, 6):
        input = open('tests_1/' + str(i) + '.txt', 'r', encoding="utf-8")
        nextLine = input.readline()
        lines = ''
        while nextLine:
            lines = lines + nextLine
            nextLine = input.readline()
        result = re.findall(r'[А-Я]\w+(?= +[А-Я]\.[А-Я]\.$| +[А-Я]\.[А-Я]\.\s)', lines)
        result.sort()
        print("\nРезультаты")
        for v in result:
            print(v)


task1()
