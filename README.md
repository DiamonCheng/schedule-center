# 想写一个 分布式调度中心的实现， 基于 原有的frame框架。希望实现将这个程序使用spring boot 进行打包



调度中心需求

1.静态任务-静态配置，周期性触发

2.动态任务-由接口注册，一次性触发

frame hibernate spring springmvc 框架

Log：
新增branch，改造成为spring boot 应用（没有什么卵用）
-2017年11月4日13:06:05


新建子项目-失败


-TODO
定时器核心开发-。。。
1 定时任务做什么？
往MQ里面发消息？

定时任务 CRUD 同步定时任务还有问题。 估计是GeneralController没有调用到 beforeUpdate。