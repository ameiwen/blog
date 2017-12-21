<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="../commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="${ctx}/css/qu.css">
    <link rel="stylesheet" href="${ctx}/css/style.css">
    <link rel="stylesheet" href="${ctx}/css/comment.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <div class="panel panel-default">
                    <article class="post post-1">
                        <header class="entry-header">
                            <h1 class="entry-title">JVM内存溢出实例-实战JVM（二）</h1>
                            <div class="entry-meta">
                                <span class="post-category"><a href="#">Web Design</a></span>

                                <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                                <span class="post-author"><a href="#">Albert Einstein</a></span>

                                <span class="comments-link"><a href="#">4 Comments</a></span>
                            </div>
                        </header>
                        <div class="entry-content clearfix">
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                JVM规范规定，除了程序计数器，虚拟机其他内存区域均会发生内存溢出的可能，OutOfMemoryError（OOM）
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">本文目的：</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 1、通过代码人为造成OOM，让大家跟了解JVM运行时各区存储的内容。<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 2、通过demo让大家实际开发过程中，能够根据异常判断是那个内存区域发生的溢出，<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 3、让大家了解到什么样的代码会产生OOM，开发中能够尽量规避。<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">前提：</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp;&nbsp; &nbsp; 先和大家介绍一下eclipse如何设置JVM参数，Xms 最小堆内存，Xmx最大堆内存，
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; &nbsp;&nbsp;HeapDumpOnOutOfMemoryError：发生内存溢出时生成堆转储快照
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255); text-align: center;">
                                <img src="http://7xjcjk.com1.z0.glb.clouddn.com/FuMl649tr2qbNrjnTK-NgdpqlL2z" width="657" height="444"/><br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">一、JAVA堆内存溢出</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp;代码实例，来自&lt;&lt;深入理解JVM虚拟机&gt;&gt;
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">public class TestOOM {
	static class OOMObject{

	}
	public static void main(String[] args) {
		List&lt;OOMObject&gt; list = new ArrayList&lt;OOMObject&gt;();
		while(true){
			list.add(new OOMObject());
		}
	}

}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                运行结果如下，发生内存溢出，在OutOfMemoryError后面会跟着 Java heap space 提示是堆内存溢出，
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                同时生成<span style="box-sizing: border-box; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; line-height: 1.42857; background-color: rgb(245, 245, 245);">&nbsp;java_pid10308.hprof 文件</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; line-height: 1.42857;"><span style="box-sizing: border-box; background-color: rgb(247, 247, 247);">该文件可以用分析工具MAT进行分析，安装链接<a href="http://www.begincode.net/blog/45" target="_blank" style="box-sizing: border-box; background-color: transparent; color: rgb(69, 69, 69); text-decoration-line: none;">http://www.begincode.net/blog/45</a>&nbsp;也可以用其他工具，</span></span>
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid10308.hprof ...
Heap dump file created [27883798 bytes in 0.497 secs]</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                通过工具可以分析是内存泄露还是内存溢出，如果不存在泄露，那就是存在着很多对象实例无法回收，可以通过分析查看是那些对象，是否必须存活，如果必须存活在考虑适当调整JVM堆参数，如Xmx Xms等在以后的文章中会在此介绍。
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">二、虚拟机栈和本地方栈溢出（栈溢出）</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">&nbsp; &nbsp; 1、虚拟机栈<br/></span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp;虚拟机栈溢出，理论上分为两种，一种是线程请求的栈深度大于虚拟机允许的最大深度，另外一种是申请的空间不足。<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp;因为虚拟机栈记录的是局部变量（方法变量）和函数调用栈针，则产生堆栈溢出一般是函数调用深度，如递归类或者
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 方法A调用方法B，方法B调用方法C这样调用深度超过了虚拟机允许的最大深度，<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; JVM参数设置为如下，Xss128k &nbsp;Xss是堆栈大小<br/>
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">-Xms20m -Xmx20m -Xss128k -XX:+HeapDumpOnOutOfMemoryError</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp;demo如下
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">    public class TestOOM {
	static int stackIndex = 0;
	public   void testStackOverFlow(){
		stackIndex++;
		testStackOverFlow();
	}

	public static void main(String[] args) throws Throwable  {
		try{
			TestOOM oom = new TestOOM();
			oom.testStackOverFlow();
		}catch(Throwable e){
			System.out.println(&quot;堆栈深度（迭代次数）：&quot;+stackIndex);
			throw e;
		}
	}
}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                执行结果，堆栈深度达到983，堆栈溢出，并会给出具体是类，方法，发生的错误
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">堆栈深度（迭代次数）：983
Exception in thread &quot;main&quot; java.lang.StackOverflowError
	at zgwx.TestOOM.testStackOverFlow(TestOOM.java:9)
	at zgwx.TestOOM.testStackOverFlow(TestOOM.java:10)</pre>
                            <p>
                                相同参数情况下如果修改递归的方法，在方法内创建几个变量大家再看下结果。
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">public   void testStackOverFlow(){
		stackIndex++;
		int str = 12;
		int str2 = 12;
		int str3 = 12;
		int str4 = 12;
		int str5 = 12;
		int str6 = 12;
		int str7 = 12;
		int str8 = 12;
		testStackOverFlow();
	}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                执行结果如下，迭代次数明显减少，说明局部变量占用了堆栈的空间
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">堆栈深度（迭代次数）：579
Exception in thread &quot;main&quot; java.lang.StackOverflowError
	at zgwx.TestOOM.testStackOverFlow(TestOOM.java:9)
	at zgwx.TestOOM.testStackOverFlow(TestOOM.java:18)</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 2、本地方法栈，本地方法栈可以通过创建线程的方式来实现溢出，因为java线程是映射到操作系统内核上，所以线程会调用本地方法，造成本地方法栈的溢出<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp;本机环境没测试出来，机器卡死了，大家可以自己试试尽量多创建线程，就会撑爆本地方法栈
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp;<span style="box-sizing: border-box; color: rgb(255, 0, 0);">下面是找来的例子，没见到效果，都死卡死机告终</span>
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">public class Test {
	private void runAlways(){
		while(true){

		}
	}
	public void createThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				public void run() {
					runAlways();
				}

			});
			thread.start();
		}
	}
	public static void main(String[] args) {
		Test test = new Test();
		test.createThread();
	}
	}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; line-height: 1.42857; font-weight: bold;">三、直接本机内存溢出</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 本机内存溢出可以无限制申请空间即可，用nio的申请缓冲区方式<br/>
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">	public static void main(String[] args) {
		List list = new ArrayList();
		while(true){
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*2048);
			list.add(byteBuffer);
		}
	}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                执行结果如下，OutOfMemoryError后面的提示，Direct buffer memory 说明了直接缓冲区，
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">Exception in thread &quot;main&quot; java.lang.OutOfMemoryError: Direct buffer memory
	at java.nio.Bits.reserveMemory(Bits.java:658)
	at java.nio.DirectByteBuffer.&lt;init&gt;(DirectByteBuffer.java:123)</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                <span style="box-sizing: border-box; font-weight: bold;">四、方法区（永久代）溢出</span>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 方法区存储的是加载的类信息，常量，我们就循环创建字符串常量，让方法区溢出<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 为了尽快溢出，jvm参数设置永久代参数5M： -XX:PermSize=5m &nbsp;-XX:MaxPermSize=5m<br/>
                            </p>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp; &nbsp; 代码如下：<br/>
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">	public static void main(String[] args) {
		List list = new ArrayList();
		int i = 0;
		while(true){
			list.add((String.valueOf(i++)).intern());
		}
	}</pre>
                            <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);">
                                &nbsp;运行结果如下：OutOfMemoryError后面提示 PermGen space ；改代码在jdk6及以下版本能够报出如下异常，jdk7高版本及以上部分jvm已经逐渐开始取消永久代的原因，只会报出堆内存异常
                            </p>
                            <pre style="box-sizing: border-box; overflow: auto; font-family: Menlo, Monaco, Consolas, &quot;Courier New&quot;, monospace; font-size: 13px; padding: 9.5px; margin-top: 0px; margin-bottom: 10px; line-height: 1.42857; color: rgb(51, 51, 51); word-break: break-all; word-wrap: break-word; background-color: rgb(245, 245, 245); border: 1px solid rgb(204, 204, 204); border-radius: 4px;">Exception in thread &quot;main&quot; java.lang.OutOfMemoryError: PermGen space
	at java.lang.String.intern(Native Method)
	at com.TestSocket.main(TestSocket.java:12)</pre>
                            <p>
                                <br/>
                            </p>
                        </div>
                        <%--<div class="entry-content clearfix">--%>
                            <%--<div class="form-group">--%>
                                <%--<input type="hidden" name="blogId" id="blogId" value="28">--%>
                                <%--<textarea class="form-control required" id="blogCommentContent" style="resize: none" rows="5" placeholder="一句话评论"></textarea>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<button type="button" id="pubBlogCommon" class="btn pub-btn btn-default r">评论</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </article>
                    <article class="post post-2">
                        分享：
                        <div class="bdsharebuttonbox">
                            <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                            <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                            <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                            <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                            <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
                            <a href="#" class="bds_more" data-cmd="more"></a>
                        </div>
                        <div class="widget widget-recent-posts">
                            <h5 class="widget-title">类似文章</h5>
                            <ul>
                                <li>
                                    <a href="#">自定义通信协议之原码，反码，补码</a>
                                </li>
                                <li>
                                    <a href="#">maven环境搭建</a>
                                </li>
                                <li>
                                    <a href="#">JDK环境变量配置</a>
                                </li>
                            </ul>
                        </div>
                    </article>
                </div>
                <div class="panel panel-default">
                    <article class="post post-1">
                        <div class="commentAll">
                            <!--评论区域 begin-->
                            <div class="reviewArea clearfix">
                                <script type="text/plain" id="container">
                                    <p>留点什么吧！</p>
                                </script>
                                <a href="javascript:;" class="plBtn">留言</a>
                            </div>
                            <!--评论区域 end-->
                            <!--回复区域 begin-->
                            <div class="comment-show">
                                <div class="comment-show-con clearfix">
                                    <div class="comment-show-con-img pull-left"><img src="${ctx}/images/header-img-comment_03.png" alt=""></div>
                                    <div class="comment-show-con-list pull-left clearfix">
                                        <div class="pl-text clearfix">
                                            <a href="#" class="comment-size-name">张三 : </a>
                                            <span class="my-pl-con">&nbsp;来啊 造作啊!</span>
                                        </div>
                                        <div class="date-dz">
                                            <span class="date-dz-left pull-left comment-time">2017-5-2 11:11:39</span>
                                            <div class="date-dz-right pull-right comment-pl-block">
                                                <a href="javascript:;" class="removeBlock">删除</a>
                                                <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
                                                <span class="pull-left date-dz-line">|</span>
                                                <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a>
                                            </div>
                                        </div>
                                        <div class="hf-list-con"></div>
                                    </div>
                                </div>
                            </div>
                            <!--回复区域 end-->
                        </div>
                    </article>
                </div>
            </main>
            <aside class="col-md-3">
                <div class="widget widget-recent-posts">
                    <h5 class="widget-title">点击排行</h5>
                    <ul>
                        <li>
                            <a href="#">自定义通信协议之原码，反码，补码</a>
                        </li>
                        <li>
                            <a href="#">maven环境搭建</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                    </ul>
                </div>
                <div class="widget widget-recent-posts">
                    <h5 class="widget-title">最新文章</h5>
                    <ul>
                        <li>
                            <a href="#">自定义通信协议之原码，反码，补码</a>
                        </li>
                        <li>
                            <a href="#">maven环境搭建</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                    </ul>
                </div>
                <div class="widget widget-tag">
                    <h3 class="widget-title">标签云</h3>
                    <div class="panel panel-default">
                        <div class="panel-body">
				        <span class="tag-list">
                            <a href="/resource/fortype/1 " class="hot-label " target="_self">JAVA</a>
                            <a href="/resource/fortype/2 " class="hot-label " target="_self">开发工具</a>
                            <a href="/resource/fortype/3 " class="hot-label " target="_self">使用手册</a>
                            <a href="/resource/fortype/4 " class="hot-label " target="_self">算法</a>
                            <a href="/resource/fortype/5 " class="hot-label " target="_self">架构</a>
                            <a href="/resource/fortype/6 " class="hot-label " target="_self">数据库</a>
                            <a href="/resource/fortype/7 " class="hot-label " target="_self">框架技术</a>
                            <a href="/resource/fortype/8 " class="hot-label " target="_self">网页模板</a>
					    </span>
                        </div>
                    </div>
                </div>
                <div class="widget widget-archives">
                    <h5 class="widget-title">档案记录</h5>
                    <ul>
                        <li>
                            <a href="#">2017-5-8</a>
                        </li>
                        <li>
                            <a href="#">2017-5-8</a>
                        </li>
                        <li>
                            <a href="#">2017-12-17</a>
                        </li>
                    </ul>
                </div>
            </aside>
        </div>
    </div>
</div>
<jsp:include page="../core/foot.jsp"/>
<!-- Mobile Menu -->
<jsp:include page="../core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-3.2.1.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
<script src="${ctx}/ueditor/ueditor.config.js"></script>
<script src="${ctx}/ueditor/ueditor.all.js"></script>
<script src="${ctx}/js/jquery.flexText.js"></script>>
<script>
    window._bd_share_config={
        "common":{
            "bdSnsKey":{},
            "bdText":"",
            "bdMini":"2",
            "bdMiniList":[
                "qzone",
                "tsina",
                "bdysc",
                "weixin",
                "renren",
                "tqq",
                "bdxc",
                "tqf",
                "tieba",
                "douban",
                "bdhome",
                "sqq",
                "ibaidu",
                "youdao",
                "mail",
                "isohu",
                "twi"],
            "bdPic":"",
            "bdStyle":"1",
            "bdSize":"24"
        },
        "share":{}
    };
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
    //    textarea高度自适应
    $(function () {
        $('.content').flexText();
        var ue = UE.getEditor('container',{
            toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold','simpleupload','test']],
            //focus时自动清空初始化时的内容
            autoClearinitialContent:true,
            //关闭字数统计
            wordCount:false,
            //关闭elementPath
            elementPathEnabled:false,
            //更多其他参数，请参考ueditor.config.js中的配置项
            initialFrameWidth :'100%',//设置编辑器宽度
            scaleEnabled:false//设置不自动调整高度
        });
        ue.ready(function(){
            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
            UE.Editor.prototype.getActionUrl = function(action) {
                if (action == 'ueditor/uploadImg') {
                    return 'http://localhost:6060/page/Demo.jsp';
                } else if (action == 'uploadvideo') {
                    return 'http://a.b.com/video.php';
                } else {
                    return this._bkGetActionUrl.call(this, action);
                }
            }
        })
    });
    //    textarea限制字数
    function keyUP(t){
        var len = $(t).val().length;
        if(len > 139){
            $(t).val($(t).val().substring(0,140));
        }
    }
    //点击评论创建评论条
    $('.commentAll').on('click','.plBtn',function(){
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(m<10) m = '0' + m;
        var s=myDate.getSeconds();
        if(s<10) s = '0' + s;
        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        //获取输入内容
        var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
        console.log(oSize);
        //动态创建评论模块
        oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="#" class="comment-size-name">David Beckham : </a> <span class="my-pl-con">&nbsp;'+ oSize +'</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
        if(oSize.replace(/(^\s*)|(\s*$)/g, "") != ''){
            $(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
            $(this).siblings('.flex-text-wrap').find('.comment-input').prop('value','').siblings('pre').find('span').text('');
        }
    });
    //点击回复动态创建回复块
    $('.comment-show').on('click','.pl-hf',function(){
        //获取回复人的名字
        var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        //回复@
        var fhN = '回复@'+fhName;
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" class="hf-pl">回复</a></div>';
        //显示回复
        if($(this).is('.hf-con-block')){
            $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
            $(this).removeClass('hf-con-block');
            $('.content').flexText();
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding','6px 15px');
            //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
            //input框自动聚焦
            $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
        }else {
            $(this).addClass('hf-con-block');
            $(this).parents('.date-dz-right').siblings('.hf-con').remove();
        }
    });
    //评论回复块创建
    $('.comment-show').on('click','.hf-pl',function(){
        var oThis = $(this);
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(m<10) m = '0' + m;
        var s=myDate.getSeconds();
        if(s<10) s = '0' + s;
        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        //获取输入内容
        var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
        console.log(oHfVal)
        var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        var oAllVal = '回复@'+oHfName;
        if(oHfVal.replace(/^ +| +$/g,'') == '' || oHfVal == oAllVal){

        }else {
            $.getJSON("json/pl.json",function(data){
                var oAt = '';
                var oHf = '';
                $.each(data,function(n,v){
                    delete v.hfContent;
                    delete v.atName;
                    var arr;
                    var ohfNameArr;
                    if(oHfVal.indexOf("@") == -1){
                        data['atName'] = '';
                        data['hfContent'] = oHfVal;
                    }else {
                        arr = oHfVal.split(':');
                        ohfNameArr = arr[0].split('@');
                        data['hfContent'] = arr[1];
                        data['atName'] = ohfNameArr[1];
                    }

                    if(data.atName == ''){
                        oAt = data.hfContent;
                    }else {
                        oAt = '回复<a href="#" class="atName">@'+data.atName+'</a> : '+data.hfContent;
                    }
                    oHf = data.hfName;
                });

                var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="#" class="comment-size-name">我的名字 : </a><span class="my-pl-con">'+oAt+'</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div></div>';
                oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display','block').prepend(oHtml) && oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block') && oThis.parents('.hf-con').remove();
            });
        }
    });
    //删除评论块
    $('.commentAll').on('click','.removeBlock',function(){
        var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
        if(oT.siblings('.all-pl-con').length >= 1){
            oT.remove();
        }else {
            $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display','none')
            oT.remove();
        }
        $(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();

    })
    //点赞
    $('.comment-show').on('click','.date-dz-z',function(){
        var zNum = $(this).find('.z-num').html();
        if($(this).is('.date-dz-z-click')){
            zNum--;
            $(this).removeClass('date-dz-z-click red');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').removeClass('red');
        }else {
            zNum++;
            $(this).addClass('date-dz-z-click');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').addClass('red');
        }
    })
</script>
</html>