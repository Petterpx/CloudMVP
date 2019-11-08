# CloudMVP

一个简单易用的Android MVP开发库，已用于公司实际项目，不断完善，纯手工封装。

- 面向接口的MVP模板，方便解耦；
- Presenter采用动态代理，避免View为null；
- 更简单的 Presenter ,Model注解工厂，用起来只需要一行代码；
- 使用Lifecycle 赋予 Presenter 生命周期，避免onResume()等方法的重写，减少代码复杂度。

更多小工具，自行翻看，LiveBus（方案来自美团技术团队）替代EventBus。


以下Class你可能需要注意：

BaseFragment.  对应 V 层超类;

BasePresenter. 对应 P 层超类；

BaseModel. 对应M 层超类；



Fragment使用fragmentationx库二次封装	

#### 使用Demo:

#### 契约类

```java
public interface TestControl {
    interface testView extends IView {

        void test();

    }

    interface testPresenter extends IPresenter<testView, testModel> {
    }

    interface testModel extends IModel {
    }
}
```



#### Model

```java
public class TestModels extends BaseModel<TestControl.testPresenter> implements TestControl.testModel{

}
```



#### View

```java
//注解Presenter
@CreatePresenter(TestPresenter.class)
public class TestFragment extends BaseFragment<TestControl.testPresenter> implements TestControl.testView {
    @Override
    public Object setLayout() {
        return R.layout.test_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    public static TestFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void test() {
        Toast.makeText(getContext(), "测试方法", Toast.LENGTH_SHORT).show();
    }
}
```



#### Presenter

```java
//注解Model
@CreateModel(TestModels.class)
public class TestPresenter extends BasePresenter<TestControl.testView, TestControl.testModel> implements TestControl.testPresenter {
    @Override
    public void initPresenter() {
      	//测试方法
        getView().test();
    }
}
```



