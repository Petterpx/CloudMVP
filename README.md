CloudMVP
一个简单易用的Android MVP开发库，使用Navigation的傻瓜式单Activity+Fragment开发。

处理并优化了 Navigation 中的一些细节问题，Fragment返回键傻瓜式配置，多栈跳转的优化。

使用RxJava 优化页面启动的数据加载，默认关闭状态，根据需求决定是否需要。

更简单的 Presenter 注解工厂，用起来只需要一行代码。

使用Lifecycle 赋予 Presenter 生命周期，避免onResume()等方法的重写，减少代码复杂度。

以下Class你可能需要注意：

BaseActivity. Activity基类;

BaseFragment.  对应 V 层;

BasePresenter. 对应 P 层；

Latte.  默认配置层

M 层 应该按照业务逻辑划分自行定义,这里并没有进行封装。


使用方式：

配置管理：

默认采用单Activity+多Fragment开发，Activity默认继承BaseActivity即可，已经处理了全局Activity,Handler,Context。更多配置项请自行定义,并在Activity setLatteinit()中返回true,那么将只会默认处理Activity。

实例代码


//添加配置信息,适用于单Activity，默认配置,如果需要自行配置，请重写setLatteinit()返回值
if (setLatteinit()) {
    Latte.getConfigurator().withBaseActivity(this);
} else {
    Latte.getConfigurator()
            .withContext(this)
            .withBaseActivity(this)
            .withHandler(new Handler())
            .configure();
}
​
​
public boolean setLatteinit() {
        return false;
}
**自行配置信息**：


public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Handler handler=new Handler();
        Latte.init(this)
                .withHandler(handler)
                ...
                .configure();
    }
​
}

public class MainActivity extends BaseActivity {
​
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
​
    @Override
    public boolean setLatteinit() {
        return true;
    }
}

实例Demo:

M(根据业务自行配置):


public interface IHomeModel ...

public class IHomeImpl implements IHomeModel ...
V:


public interface IHomeView extends IBaseView ...

@CreatePresenter(HomePresenter.class)
public class HomeDelegate extends BaseFragment<HomePresenter> implements IHomeView ...

P:


public class HomePresenter extends BasePresenter<IHomeView> {
  private IHomeModel iModel;
  private IHomeView iView;
​
    @Override
    public void getView(IHomeView view) {
        this.iView = view;
        iModel = new IHomeImpl();
    }
}
