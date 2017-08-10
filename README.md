## CalendarTest

在 onitemclicklistener 里不要用view的setselected属性，会重复执行背景切换不准确，还没找到efficient办法，暂用setbackground or settextcolor代替直接设置背景属性。

do not use setselect of view in onitemclicklistener, it will be launch for many times that it result in error on changing date info, i didnt find any solve methods efficently so i use setbackground and settextcolor instead of setsecect temporarily.
