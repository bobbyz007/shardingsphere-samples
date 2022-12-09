## 一、项目概述

### 1、项目说明

为了帮助用户更好的使用分片功能，降低配置复杂度和提升使用体验，Apache ShardingSphere 5.0.0 版本推出了一种新的分片配置方式：AutoTable。

顾名思义，AutoTable 类型的数据表，交由 ShardingSphere 自动管理分片，用户只需要指定分片数量和使用的数据源，无需再关心表的具体分布。

auto-tables功能介绍可参考：
https://www.xujun.org/note-139332.html