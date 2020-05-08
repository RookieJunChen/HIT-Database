import os


def dropBlockOnDisk(addr):
    fileName = "blocks/" + addr + ".blk"
    if not os.path.exists(fileName):
        return False
    os.remove(fileName)
    return True


class Buffer:
    def __init__(self, bufsize, blksize):
        self.numIO = 0
        self.bufSize = bufsize
        self.blkSize = blksize
        self.numAllBlk = bufsize // (blksize + 1)
        self.numFreeBlk = self.numAllBlk
        self.blkcount = 0
        self.data = []
        self.used = []
        for i in range(self.numAllBlk):
            self.data.append([])
            self.used.append(False)

    def freeBuffer(self):
        self.data = []
        self.used = []
        for i in range(self.numAllBlk):
            self.data.append([])
            self.used.append(False)
        self.checkoutnumfreeblk()
        print("Buffer free")

    def getNewBlockInBuffer(self):
        if (self.numFreeBlk == 0):
            print("No free Block")
            return False;
        for i in range(8):
            if self.used[i] == False:
                self.checkoutnumfreeblk()
                self.used[i] = True
                return i

    def freeBlockInBuffer(self, free_num):
        if self.used[free_num] == True:
            self.used[free_num] = False
            self.checkoutnumfreeblk()
        self.data[free_num] = []

    def readBlockFromDisk(self, addr):
        fileName = "blocks/" + addr + ".blk"
        if self.numFreeBlk == 0:
            print("Buffer Overflow")
            return False
        f = open(fileName)
        if not f:
            print("Open File Fail")
            return False
        line = f.readline()
        line_spilt = line.split(" ")
        line_spilt = line_spilt[:-1]
        f.close()
        for i in range(self.numAllBlk):
            if self.used[i] == False:
                self.used[i] = True
                self.data[i] = line_spilt
                self.checkoutnumfreeblk()
                self.numIO += 1
                return i

    def writeBlockToDisk(self, addr, write_num):
        fileName = "blocks/" + str(addr) + ".blk"
        self.blkcount += 1
        f = open(fileName, "w")
        if not f:
            print("Open File Fail")
            return False
        for i in self.data[write_num]:
            f.write(str(i) + " ")
        f.write(str(self.blkcount) + " ")
        f.close()
        self.data[write_num] = []
        self.used[write_num] = False
        self.checkoutnumfreeblk()
        self.numIO += 1
        return True

    def checkoutnumfreeblk(self):
        count = 0
        for i in range(self.numAllBlk):
            if self.used[i] == False:
                count += 1
        self.numFreeBlk = count
