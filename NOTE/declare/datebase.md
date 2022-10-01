数据库标准
1、表名：项目名_表名
2、字段名：数据类型_表名（数据类型：varchar=vc; date=d; 数值=f 等）
3、使用联合索引；
4、使用默认自增ID为主键；
5、使用时间拉链表；
    5.1、d_begin_date 、 d_end_date 、 d_update 、d_create_data 的基本使用
    以数据1为例：
        数据1首次新增到数据库中，d_begin_date与d_end_date后端传入，d_begin_date为创建时间；d_end_date为结束时间【默认为9999-12-31】； 
                             d_update为空【此时后端不用传入】；
                             d_create_data 数据库默认当前时间【后端不用传入】；
                             其中d_begin_date与d_end_date为数据1的生命周期；
        数据1更新某个字段时，在该更新该数据时，并更新d_update【此时后端传入】；
        注：只记录该条数据的是否有效；若记录更新日志，则创建日志表来记录；
            若涉及到更新状态类字段，则必须更新d_end_date与d_update为当前时间，并插入一条新数据；
        数据1生命周期结束时，需要增加生命周期，则新增一条，d_begin_date=上次的d_end_date，并传入新的d_end_date；
        注：唯一ID数据，只 传入/更新 一次d_begin_date与d_end_date字段

