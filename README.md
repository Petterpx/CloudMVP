# CloudMVP

一个简单易用的Android MVP开发库，MVP 是一种开发思想，并非一种生硬的模板写法。所以 CloudMVP 并不是一个MVP 框架。

集成JetPack-Lifecycle，用来更好的处理生命周期。ButterKnife 用来省去finId,Innersionbar来实现沉浸式状态栏。

推荐使用JetPack-Navigation来更好管理Fragment。 (需要自行导入)。

后期会逐渐向 JetPack 组件迁移。

以下Class你可能需要注意：

BaseFragment.  对应 V 层。

BasePresenter. 对应 P 层；

M 层 应该按照业务逻辑划分自行定义,这里并没有进行封装。
