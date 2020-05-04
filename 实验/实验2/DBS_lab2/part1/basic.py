import relations
import random


def randomR():
    r = relations.R(random.randint(1, 40), random.randint(1, 1000))
    return r


def randomS():
    s = relations.S(random.randint(20, 60), random.randint(1, 1000))
    return s


def generateRS():
    R = []
    S = []
    for i in range(112):
        R.append(randomR())
    for i in range(224):
        S.append(randomS())
    return R, S


def write_r_to_disk(buffer, R):
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


def write_s_to_disk(buffer, S):
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


def print_R_S(buffer):
    for i in range(16):
        addr = "r" + str(i)
        n = buffer.readBlockFromDisk(addr)
        for j in range(7):
            a = buffer.data[n][2 * j]
            b = buffer.data[n][2 * j + 1]
            print("Relation_R", a, b)
        buffer.freeBlockInBuffer(n)
    for i in range(32):
        addr = "s" + str(i)
        n = buffer.readBlockFromDisk(addr)
        for j in range(7):
            c = buffer.data[n][2 * j]
            d = buffer.data[n][2 * j + 1]
            print("Relation_S", c, d)
        buffer.freeBlockInBuffer(n)
