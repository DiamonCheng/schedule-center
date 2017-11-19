# 想写一个 分布式调度中心的实现， 基于 原有的frame框架。希望实现将这个程序使用spring boot 进行打包



调度中心需求

1.静态任务-静态配置，周期性触发

2.动态任务-由接口注册，一次性触发

任务触发使用MessageQueue进行调度，本项目中使用测试的MQ版本为 apache-rocket-mq：4.1.0 xxx （见父POM）

frame hibernate spring springmvc 框架










----------------------------------------------------------
Log：

新增branch，改造成为spring boot 应用（没有什么卵用）

-2017年11月4日13:06:05


新建子项目-失败



定时器核心开发-。。。

1 定时任务做什么？
往MQ里面发消息？？？目前没有实现
定时任务执行
生成定时任务流水记录，插入流水表，如果插入失败，
获取锁失败（直接不执行定时任务？）。

定时任务 CRUD 同步定时任务还有问题。 估计是GeneralController没有调用到 beforeUpdate。

已经解决，方法为暴力删除大量的从xml中加载的方法解析过程，并坚持采用简单的回调。

框架分布式化-OK

需要加入定时任务流水表（上锁）


2017年11月5日19:59:27

编写任务执行插入锁的操作-没有全部完工


2017年11月6日22:17:02

配置文件外置化，MqProducer Spring 实例


2017年11月7日09:37:30

taskEntity新增字段 taskTopic，taskTag，paramTemplate


2017年11月7日12:33:45

编写SDK
consumer->defaultConcurrectListener->for each message -> ? extends AbstractScheduleListener.start()
SDK编写完成，还没有进行测试-2017年11月7日21:01:45


2017年11月7日22:29:20

编写了对接sdk的message发送逻辑，message接收逻辑。（producer in consumer module，consumer in provider module）
TODO-继续编写收到  scheduleTaskMessage对其进行处理的逻辑（即更新任务完成，或者任务失败）


2017年11月8日21:59:01

做了默认用户密码可配置化；
对消费消息之后的成功与失败场景逻辑操作进行编码。

--TODO 对失败的任务进行异常管理;对任务调度进行测试（包括成功场景，失败场景，多实例场景）


2017年11月9日22:53:23
添加了instanceId的概念。
准备添加任务异常管理，添加到一半发现下拉框多选框新增挂掉了。
、、TODO，任务异常Browse页面


2017年11月10日22:07:16
修复昨天出的BUG，原因是XML中的类无法找到，异常未被显示。 DeffersonCheng 

2017年11月19日18:20:07





