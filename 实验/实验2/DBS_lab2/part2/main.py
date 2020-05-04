from SyntaxTree import SyntaxTree
from graphviz import Digraph

employee = ["ESSN", "ADDRESS", "SALARY", "SUPERSSN", "ENAME", "DNO"]
department = ["DNO", "DNAME", "DNEMBER", "MGRSSN", "MGRSTARTDATE"]
project = ["PNAME", "PNO", "PLOCATION", "DNO"],
works_on = ["HOURS", "ESSN", "PNO"]


# 断开语句构建树
def parse(sql_statement):
    sql = sql_statement.split()
    execute_tree = SyntaxTree()

    index = 0
    while True:
        if index >= len(sql):
            break
        elif sql[index] == 'SELECT' or sql[index] == 'PROJECTION':
            execute_tree.op = sql[index]
            index += 2  # 从[开始到]里面的全部记录下来
            condition = ''
            while sql[index] != ']':
                condition += sql[index]
                condition += ' '
                index += 1
            index += 1
            execute_tree.cond = condition
        elif sql[index] == 'JOIN':
            # 连接操作需要创建子树，所以分开写
            execute_tree.op = sql[index]
            execute_tree.lfchild = SyntaxTree()
            execute_tree.lfchild.attr = sql[index - 1]
            execute_tree.rchild = SyntaxTree()
            execute_tree.rchild.attr = sql[index + 1]
            index += 1
        elif sql[index] == '(':
            # 每次遇到这个说明需要创建一棵子树，由于题目中的查询都是只有一个分支，所以可以直接进入下一个子树中
            index += 1
            statement = ''
            while index < len(sql) and sql[index] != ')':
                statement += sql[index]
                statement += ' '
                index += 1
            index += 1
            execute_tree.lfchild = parse(statement)  # 递归构造子树
        else:
            index += 1

    return execute_tree


# 辅助函数
def search(sql):
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


# 启发式优化
def optimize(syntax_tree, sql):
    """
    根据所选语句
    这里的启发式优化主要是拆分SELECT语句中的AND
    下移SELECT语句
    下移PROJECTION语句
    划分子树
    """
    if syntax_tree.op == 'SELECT':
        condition = syntax_tree.cond
        sql = condition.split('&')
        relation = []
        for i in range(len(sql)):
            if search(sql[i]) is not None:
                relation.append(search(sql[i]))
        syntax_tree = optimize(syntax_tree.lfchild, sql)
    elif syntax_tree.op == 'PROJECTION':
        syntax_tree.lfchild = optimize(syntax_tree.lfchild, sql)
    elif syntax_tree.op == 'JOIN':
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


def print_tree(syntax_tree):
    if syntax_tree.op != '':
        print(syntax_tree.op, syntax_tree.cond)
    else:
        print(syntax_tree.attr)
    if syntax_tree.lfchild is not None:
        print_tree(syntax_tree.lfchild)
    if syntax_tree.rchild is not None:
        print_tree(syntax_tree.rchild)


if __name__ == '__main__':
    sql1 = "SELECT [ ENAME = ’Mary’ & DNAME = ’Research’ ] ( EMPLOYEE JOIN DEPARTMENT )"
    sql2 = "PROJECTION [ BDATE ] ( SELECT [ ENAME = ’John’ & DNAME = ’ Research’ ] ( EMPLOYEE JOIN DEPARTMENT) )"
    sql3 = "SELECT [ ESSN = ’01’ ] ( PROJECTION [ ESSN, PNAME ] ( WORKS_ON JOIN PROJECT ) )"

    print('--------------------------------------------')
    etree1 = parse(sql1)
    print_tree(etree1)
    print('--------------------------------------------')
    otree1 = optimize(etree1, '')
    print_tree(otree1)
    print('--------------------------------------------')
    print('--------------------------------------------')
    etree2 = parse(sql2)
    print_tree(etree2)
    print('--------------------------------------------')
    otree2 = optimize(etree2, '')
    print_tree(otree2)
    print('--------------------------------------------')
    print('--------------------------------------------')
    etree3 = parse(sql3)
    print_tree(etree3)
    print('--------------------------------------------')
    otree3 = optimize(etree3, '')
    print_tree(otree3)
    print('--------------------------------------------')
