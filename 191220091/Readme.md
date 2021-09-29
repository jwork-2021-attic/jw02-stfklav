# W02 191220091

### 任务一

##### `example`的类图：

![GourdLine](http://www.plantuml.com/plantuml/png/RLFBRYen3DtdAxAyzo9-0N6HfXIfWee7hLHr4GQNZXeIb8TQIldtMd4yP0Hix9nnxEESmz07vKBSQpcsqMqV6WD2D2Q0UrCro8Lrc8gJaFZJLct__4sfHpodEmYFMXaydOMejV9UZk9cew5pjo-nwSkhLFbqm2iYv_vJ7NApNZcaLuxfbODAYaXPuRGynsEHzKreh65-jv2rqX6e4vYuf-LpYzcqId7vDADuFwy8kPkjvfHCreGi9iyKgtKrpUku77SvRdZbp49SzabZHyUvCX_jcguy1Bi8hZ4x1hhxSQDhBBtfTM6vE8kL96UH-0egnNGzypt4ZXQtE3BmtpbrB1t1VTj1cbIJuh8N2TabDmRuBZnqGhU6fZw7Tw0lxA1aDUx5LKXooONb0v8nMmIfm8eDY38fvo4PZH7uWZg6JApqjkptVmRy7scX0RCMILugOfVoGwGwSbt-X8Yzl5DP-8yq6eBPnht-1G00)




##### `Scene`中`main`方法执行过程中的对象时序图：

![exampleTimeline](http://www.plantuml.com/plantuml/png/jLFDJZCn3BpdANByFgg-GbKuWuIqm0LnSBUch9ecLPBbHwNlZfLwcbJBbLxMcJZsU3nTfamnZpjdxu9FcUFjuDcOmKluIZtRBaG9pS5O-Rb0wt__IvW4rt33-TwHbzFHcDvHIlPcNAqSNxoTModw_699JtjvOZHE7xJNOhEsoQnjC_kjKSUE-_odZQeMeWt9MDedIL98Gnw2hq9CaITo8pUK8JJOlfDJSb6_6_skC83MDCekaj-UgURGvJZu3O09BSmpnHfgu5Qs3ndnHBG4XGGE-_52hIcT2bp7I5-j0AXxRkHEkbfJHr2fKQOiJeMx8DnvsbR6XvGf3_qnlt6vmDQBFL0TIKBxGVCQyTHBrIIYo2Cy8xkWcBXu0mX_SZzcbR7rlPtFlnVu_y2dqmj02vt5b36H1Sj1iv881-cbOA6AsgPUdHQwPByUT-u7)





##### 尝试从面向对象编程角度理解并阐述`example`的设计理念，具体阐述这样写的好处与可改进之处：

example 中，除了Scene，有Geezer，Line，Position，BubbleSorter四个类，以及Gourd一个枚举类，其中Position是Line的内部类，BubbleSorter是接口Sorter的子类，Gourd是接口Linable的子类。

面相对象编程作为以“对象”为中心的编程思想，与面向过程的方法相比，不再局限于计算机的机器本质，而更加侧重于对现实世界的模拟。`example`中，模拟现实的对象有爷爷Geezer，包含一排七个位置的Line以及其中的七个position，包含七个对象实例的葫芦娃Gourd，用来冒泡排序的器件BubbleSorter，而接口Linable和Sorter则描绘出一般化的可被排序的对象和排序器，Linable可以是葫芦娃也可以是别的需要排序的类，Sorter只对整数数组排序并记录步骤，不必在乎具体是给哪种类型排序，爷爷只对Linable和Sorter这两个接口对象操作，不必在乎其中具体用什么方法给谁排的序，这样如果要换排序方法或者排序对象不用修改过多代码，使程序更加一般化，修改更方便，增强了类的封装性和独立性。

从模拟现实的角度看整个执行过程，Line先将七个葫芦娃放入（随机的）不全正确的位置，等待排序。然后生成一个冒泡排序器，爷爷拿到排序器后对葫芦娃进行排序，结果是将每个葫芦娃和Line中正确的对应位置关联起来，并拿到（返回）排序分步步骤的记录，最后将步骤写入指定文件保存。

具体看排序过程，爷爷拿到排序器sorter和乱置的line，先利用line的方法toArray()，通过其中每个position类中的成员变量linable，将其中要排序的Linable（即葫芦娃）按原本顺序取至数组，再将每个葫芦娃所代表的数取出放入整数数组ranks[]，交由排序器对整数数组进行升序排序并记录下排序步骤，随后爷爷取出整数数组的排序步骤记录并将其拆分成一步一步，对葫芦娃分步Gourd执行，将其关联到不同的position，每执行一步就用line的方法toString()将执行后的结果可视化并打印在屏幕上，同时写进自己的总记录。最终所有步骤执行完后将可视化分步执行结果的记录返回，此时每个葫芦娃和line中正确的对应位置关联。

整个排序过程中，line, Gourd, Sorter都直接和爷爷联系，由爷爷调用，Sorter和具体的line中Gourd的排序没有直接关系，只是爷爷用来获得正确步骤的器具，具体排序是由爷爷自己执行的。因此情境相当于：爷爷看到位置错乱的葫芦娃们，通过Sorter查询到如何通过交换位置正确排序的步骤，然后爷爷根据这些步骤依次交换葫芦娃们的位置，最终将他们放入正确顺序。

需要改进的地方：爷爷在执行交换步骤时，`private void execute(String *step*)`中，还是用到了具体的Gourd而非Linable，`Gourd.getGourdByRank(Integer.parseInt(couple[0])).swapPosition(Gourd.getGourdByRank(Integer.parseInt(couple[1])));`用来交换不同Gourd关联的位置，使得要将排序对象换成非Gourd有些麻烦。改进方法：可以将这一步中Gourd的`swapPosition`改为postion中的`swapLinable`，这样爷爷就可以只操作line中的position而不用具体操作某一种排序对象了，增强封装性。




### 任务二

#### 蛇精指挥256个小妖怪站成一队

##### 类图：

![MonsterLine](https://user-images.githubusercontent.com/80143498/135128953-e647e2bd-30cd-4fee-bb01-6b9f03ddd19e.png)




##### 可视化结果展示链接：
###### 64个妖怪排成一排（使用冒泡排序）：
[![asciicast](https://asciinema.org/a/438272.svg)](https://asciinema.org/a/438272)

###### 64个妖怪排成一排（使用选择排序）：
[![asciicast](https://asciinema.org/a/438270.svg)](https://asciinema.org/a/438270)



### 任务三

#### 蛇精指挥256个小妖怪站成一个16x16方阵

##### 类图：

![MonsterMatrix](http://www.plantuml.com/plantuml/png/jLF1JiCm3BtdAt84flt2D3Wp2Qd01N5mEpEYPScKkB1fxD-nLhjDMQ_qqkJ5iTzxTfQP856xytOLGoPCAw3a3iQum9iFQD1MCV7Id8pbpqTOtznUbfbnMMwGdZm4tfsDQJpaR1_QklOukZktdFJjtOBizdp5o3f_mrwIpSeYixBCRAfGXHuR-jT2WnUFBW3BaZgPWs9sv69G8wQGL_0jbfJ5QcNx1Lx89Vblv5-h0CbfX5s2iEsfKgmekR1HO4nBzKnZ0pNb9lrN8_KIkwaaihBOjoE__jGY3gsFMk2QgW3tAS6nD4ShzXHyvxarWpnfmw2OcogcZiHK0RRO9pfb0dBDcJwnEv29w4P7KcaGYtYMkC9OcRCUOMxuelF4VOAKiPiRGV20JKjYSVacx7p-iz2tfJDyVQ3GGhIOJgP4wJYgPvT0dIuvP2mEffQXTzhi9OPrk_E_)




##### 可视化结果展示链接：
###### 64个妖怪排成8 x 8的矩阵（使用冒泡排序）：
[![asciicast](https://asciinema.org/a/438260.svg)](https://asciinema.org/a/438260)

###### 64个妖怪排成8 x 8的矩阵（使用选择排序）：
[![asciicast](https://asciinema.org/a/438263.svg)](https://asciinema.org/a/438263)



