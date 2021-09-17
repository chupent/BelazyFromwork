# MYSQL

### SQL执行顺序

 (8) SELECT  (9) DISTINCT <Select_list> 

 (1) FROM <LEFT_TABLE>   

 (3) <join type> JOIN <right_table>

 (2)	ON <join_condition>

 (4) WHERE <where_condition> 

 (5) GROUP BY <grou_by_list>

 (6) WITH {CUBE|ROLLUP} 

 (7) HAVING <having_condition>

 (10) ORDER BY <order_by_list>

 (11) LIMIT <limit_number>

1.FROM：对FROM的左边的表和右边的表计算产生笛卡尔积。产生虚表VT1.

2.ON:对虚表VT1进行ON筛选，只有那些符合<join-conditoin>的行才会被记录在虚表Vt2中

3.JOIN:如果指定了OUTER JOIN(比如：left join,right join),那么保留表中未匹配的行就会作为外部行添加到虚表VT2中，产生虚表VT3 ，如果from子句中包含两个以上的JOIN的话，那么就会对上一个JOIN连接产生的结果VT3 和下一个表重复执行1-3这三个步骤，一直到处理完所以的表为止。

4.Where:对虚拟表VT3进行where条件过滤，只有符合<where-condition>的记录才会被插入到虚拟表VT4中。

5.group by:根据group by子句中的列，对VT4中的记录进行分组操作，产生VT5

6.cube | rollup:对VT5进行cube或者rollup操作，产生VT6

7.having:对虚拟表VT6应用having过滤，只有符合<having-condition>的记录才会被记录到虚拟表VT7中

8.select：执行select 操作，选择指定的列，插入到虚拟表VT8中

9.distinct:对VT8中的记录进行去重，产生虚表VT9

10.order by:将虚拟表VT9中的记录按照<order by list>进行排序操作，产生虚拟表VT10

11:limit:取出指定行的记录，产生虚表V11，并将结果返回



### 查看SQL执行语句执行效率

```
EXPLAIN + SQL语句
```

- id列的编号是 select 的序列号，有几个 select 就有几个id,并且id的顺序是按 select 出现的顺序增长的
- select_type:select的类型
- table:表名称或表别名
- type:访问类型,即MySQL决定如何查找表中的行,查找数据行记录的大概范围。依次从最优到最差分别为:system > const > eq_ref > ref > range > index > ALL一般来说，得保证查询达到range级别，最好达到ref。
- possible_keys:显示查询可能使用哪些索引来查找。
- key:显示mysql实际采用哪个索引来优化对该表的访问,如果没有使用索引，则该列是 NULL。
- key_len:显示了mysql在索引里使用的字节数。
- ref:显示了在key列记录的索引中，表查找值所用到的列或常量，常见的有：const（常量）。
- rows:mysql估计要读取并检测的行数，注意这个不是结果集里的行数。
- extra:包含MySQL解决查询的详细信息。

