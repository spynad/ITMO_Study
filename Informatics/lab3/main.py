import json
import yaml
import time
import sys
import timeit

def work1():
    input = open('input.json', 'r', encoding="utf-8")
    output = open('output.yaml', 'w', encoding="utf-8")
    nextLine = input.readline()
    totalLines = 0
    lines = list()
    indent = -2

    while nextLine:
        lines.append(nextLine)
        totalLines += 1
        nextLine = input.readline()

    for i in range(0, totalLines):
        if lines[i].lstrip()[0] == '{':
            indent += 2
        elif lines[i].lstrip()[0] == '}':
            indent -= 2
        else:
            if lines[i].lstrip()[0] == '"':
                temp = lines[i].split(':', maxsplit=1)
                temp[0] = temp[0].replace('"', '').lstrip().expandtabs(2)
                if temp[1] != '\n':
                    temp[1] = ' \'' + temp[1][1:len(temp[1])].replace('"', '\'')
                if temp[1][len(temp[1]) - 2] == ',':
                    temp[1] = temp[1].rstrip('\n')
                    temp[1] = temp[1].rstrip(',')
                    temp[1] = temp[1] + '\n'
                output.write(indent * ' ' + temp[0] + ':' + temp[1])
    input.close()
    output.close()
def work2():
    input = open('input.json', 'r', encoding="utf-8")
    jsonData = json.load(input)
    output = open('output1.yaml', 'w+', encoding="utf-8")
    yaml.dump(jsonData, output, allow_unicode=True)
    input.close()
    output.close()


if __name__ == '__main__':
    start_time1 = time.time()
    for i in range(0, 10):
        work1()
    print("Written parser: --- %s seconds ---" % (time.time() - start_time1))
    start_time2 = time.time()
    for i in range(0, 10):
        work2()
    print("Library parser: --- %s seconds ---" % (time.time() - start_time2))

