from SyntaxTree import SyntaxTree
from graphviz import Graph
import matplotlib.pyplot as plt
import PIL.Image as Image

# 辅助后面使用的字符数组,用于确定属性所属的关系名
employee = ["ENAME", "DNO"]
department = ["DNAME", "DNO"]
project = ["PNAME", "PNO", "DNO"]
works_on = ["ESSN", "PNO"]

sql1 = "SELECT [ ENAME = ’Mary’ & DNAME = ’Research’ ] ( EMPLOYEE JOIN DEPARTMENT )"
sql2 = "PROJECTION [ BDATE ] ( SELECT [ ENAME = ’John’ & DNAME = ’ Research’ ] ( EMPLOYEE JOIN DEPARTMENT ) )"
sql3 = "SELECT [ ESSN = ’01’ ] ( PROJECTION [ ESSN, PNAME ] ( WORKS_ON JOIN PROJECT ) )"


# 断开语句构建树
def parsesql(sql_statement):
    sql = sql_statement.split()
    tree = SyntaxTree()

    index = 0
    while True:
        if index >= len(sql):
            break
        elif sql[index] == 'SELECT' or sql[index] == 'PROJECTION':
            tree.op = sql[index]
            index += 2
            condition = ''
            # 把[]里的内容全部记录下来
            while sql[index] != ']':
                condition += sql[index]
                condition += ' '
                index += 1
            index += 1
            tree.cond = condition
        elif sql[index] == 'JOIN':
            # 连接操作创建子树
            tree.op = sql[index]
            tree.lfchild = SyntaxTree()
            tree.lfchild.attr = sql[index - 1]
            tree.rchild = SyntaxTree()
            tree.rchild.attr = sql[index + 1]
            index += 1
        elif sql[index] == '(':
            # 遇到(再创建一个子树
            index += 1
            statement = ''
            while index < len(sql) and sql[index] != ')':
                statement += sql[index]
                statement += ' '
                index += 1
            index += 1
            tree.lfchild = parsesql(statement)  # 递归构造子树
        else:
            index += 1

    return tree


# 辅助函数
def lookfor(sql):
    sql = sql.split()
    if sql[0] in employee:
        return "EMPLOYEE"
    elif sql[0] in department:
        return "DEPARTMENT"
    elif sql[0] in project:
        return "PROJECT"
    elif sql[0] in works_on:
        return "WORKS_ON"
    return None


# 辅助函数
def lookfor2(sql):
    sql = sql.split()
    if sql[0] in employee:
        return employee
    elif sql[0] in department:
        return department
    elif sql[0] in project:
        return project
    elif sql[0] in works_on:
        return works_on
    return None


# 辅助函数
def findthesame(list1, list2):
    str = ''
    for i in range(len(list1)):
        element1 = list1[i]
        if element1 in list2:
            str += element1
    return str


# 下移SELECT语句
def down_select(syntax_tree, sql, relation2):
    if syntax_tree.op == 'SELECT':
        condition = syntax_tree.cond
        sql = condition.split('&')
        relation = []
        # 找出关系名
        for i in range(len(sql)):
            if lookfor(sql[i]) is not None:
                relation.append(lookfor(sql[i]))
                relation2.append(lookfor2(sql[i]))
        syntax_tree = down_select(syntax_tree.lfchild, sql, relation2)
    elif syntax_tree.op == 'PROJECTION':
        # 跳过投影
        syntax_tree.lfchild = down_select(syntax_tree.lfchild, sql, relation2)
    elif syntax_tree.op == 'JOIN':
        # 有JOIN，将SELECT与关系名下推
        first_tree = SyntaxTree()
        first_tree.op = 'SELECT'
        first_tree.cond = sql[0]
        first_tree.lfchild = syntax_tree.lfchild
        syntax_tree.lfchild = first_tree
        if len(sql) == 1:
            return syntax_tree
        second_tree = SyntaxTree()
        second_tree.op = 'SELECT'
        second_tree.cond = sql[1]
        second_tree.rchild = syntax_tree.rchild
        syntax_tree.rchild = second_tree
    return syntax_tree


# 下移PROJECTION语句
def down_proj(syntax_tree, sql, relation2, same):
    if syntax_tree.op == 'SELECT':
        syntax_tree.lfchild = down_proj(syntax_tree.lfchild, sql, relation2, same)
    elif syntax_tree.op == 'PROJECTION':
        # 将投影下推
        sql = syntax_tree.cond.split(",")
        for i in range(len(sql)):
            if lookfor(sql[i]) is not None:
                if lookfor2(sql[i]) in relation2:
                    pass
                else:
                    relation2.append(lookfor2(sql[i]))
        # 找相交的元素
        same += findthesame(relation2[0], relation2[1])
        syntax_tree.lfchild = down_proj(syntax_tree.lfchild, sql, relation2, same)
    elif syntax_tree.op == 'JOIN':
        # 有JOIN，将条件和投影下推
        if len(sql) > 0 and '=' not in sql[0]:
            first_tree = SyntaxTree()
            first_tree.op = 'PROJECTION'
            first_tree.cond = sql[0] + ', ' + same
            first_tree.lfchild = syntax_tree.lfchild
            syntax_tree.lfchild = first_tree
            if len(sql) > 1:
                second_tree = SyntaxTree()
                second_tree.op = 'PROJECTION'
                second_tree.cond = sql[1] + ', ' + same
                second_tree.rchild = syntax_tree.rchild
                syntax_tree.rchild = second_tree
            elif same != '':
                second_tree = SyntaxTree()
                second_tree.op = 'PROJECTION'
                second_tree.cond = same
                second_tree.rchild = syntax_tree.rchild
                syntax_tree.rchild = second_tree
    return syntax_tree


def showtree(syntax_tree, g, parent=''):
    if syntax_tree.op != '':
        this = syntax_tree.op + '\n' + syntax_tree.cond
        if parent != '':
            g.edge(parent, this)
        else:
            pass
    if syntax_tree.lfchild is not None:
        if syntax_tree.lfchild.attr != '':
            g.edge(this, syntax_tree.lfchild.attr)
        showtree(syntax_tree.lfchild, g, parent=this)
    if syntax_tree.rchild is not None:
        if syntax_tree.rchild.attr != '':
            g.edge(this, syntax_tree.rchild.attr)
        showtree(syntax_tree.rchild, g, parent=this)


def show(sql, filename):
    # 优化前的树
    g = Graph(filename, format='png', node_attr={'shape': 'plaintext'})
    etree = parsesql(sql)
    showtree(etree, g)
    g.render()
    img = Image.open(filename + '.gv.png')
    plt.figure('before optim')
    plt.imshow(img)
    plt.axis('off')

    # 优化后的树
    og = Graph(filename + 'optim', format='png', node_attr={'shape': 'plaintext'})
    # 两步优化
    relation2 = []
    otree = down_select(etree, '', relation2)
    otree = down_proj(otree, '', relation2, '')
    showtree(otree, og)
    og.render()
    img = Image.open(filename + 'optim' + '.gv.png')
    plt.figure('after optim')
    plt.imshow(img)
    plt.axis('off')
    plt.show()


if __name__ == '__main__':
    show(sql1, "./pics/sql1")
    show(sql2, "./pics/sql2")
    show(sql3, "./pics/sql3")
