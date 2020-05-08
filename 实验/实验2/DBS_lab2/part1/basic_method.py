import relations
import random


def generator():
    r = []
    s = []
    for i in range(112):
        tmpr = relations.R(random.randint(1, 40), random.randint(1, 1000))
        while tmpr in r:
            tmpr = relations.R(random.randint(1, 40), random.randint(1, 1000))
        r.append(tmpr)

    for i in range(224):
        tmps = relations.S(random.randint(20, 60), random.randint(1, 1000))
        while tmps in s:
            tmps = relations.S(random.randint(20, 60), random.randint(1, 1000))
        s.append(tmps)
    return r, s


def WriteRtoDisk(buffer, R):
    cnt = 0
    write_num = 0
    d = []
    for r in R:
        d.append(r.A)
        d.append(r.B)
        cnt += 1
        if cnt % 7 == 0:
            d.append(0)
            if write_num != 15:
                d.append(write_num + 1)
            else:
                d.append(0)
            buffer.data[0].extend(d)
            buffer.writeBlockToDisk("r" + str(write_num), 0)
            write_num += 1
            d = []
            buffer.freeBlockInBuffer(0)


def WriteStoDisk(buffer, S):
    cnt = 0
    write_num = 0
    d = []
    for s in S:
        d.append(s.C)
        d.append(s.D)
        cnt += 1
        if cnt % 7 == 0:
            d.append(0)
            if write_num != 15:
                d.append(write_num + 1)
            else:
                d.append(0)
            buffer.data[0].extend(d)
            buffer.writeBlockToDisk("s" + str(write_num), 0)
            write_num += 1
            d = []
            buffer.freeBlockInBuffer(0)
