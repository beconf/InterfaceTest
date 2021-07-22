# InterfaceTest
### 这是什么？
这是一个UI界面

### 这套UI界面可以用来干什么？
当有一套SDK，这个SDK包含几十个函数，怎么来测试接口功能是否正常？
用上这套UI，就不需要自己去添加一个个Button，或者实现ListView，只需向
对应的子类中，添加接口调用即可

### 如何使用？
1. 只添加一个测试项
    * 新建一个类实现BaseItem抽象方法：getName()测试项名称，itemClickResult()点击测试项执行的操作
    * 可参考VersionItem的添加
2. 同时添加两个测试项
    * 新建一个类实现BaseTwoItems抽象方法：getSecondName()第二个测试项名称，secondItemClickResult()第二个测试项点击执行的操作
    * 可参考Test1的添加
3. 同时添加三个测试项
    * 新建一个类实现BaseThreeItems抽象方法：getThirdName()第三个测试项名称，thirdItemClickResult()第三个测试项点击执行的操作
    * 可参考Test2的添加
4. 添加一个带有切换Fragment的测试项
    * 新建一个类实现BaseItem抽象方法
    * 新建一个类继承BaseFragment类
    * 可参考FragmentTest
5. 添加一个Item由Activity或Fragment响应点击信息
    * 设置BaseItem的子类的setClickListener事件
    * 参考FragmentTest中TestClickItem的添加
