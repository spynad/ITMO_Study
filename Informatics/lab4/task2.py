import re


def task2():
    input = open('Macbeth.txt', 'r', encoding="utf-8")
    nextLine = input.readline()
    lines = ''
    while nextLine:
        lines = lines + nextLine
        nextLine = input.readline()
    result = re.findall(r'(?:[^!.?,]+,[^!.?,]+)+,[^!.?,]+[!.?]', lines)
    for v in result:
        print(v)


task2()
