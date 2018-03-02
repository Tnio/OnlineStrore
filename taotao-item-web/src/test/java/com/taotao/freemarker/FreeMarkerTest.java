package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("all")
public class FreeMarkerTest {

	@Test
	public void testFreeMarkerFirst()throws Exception{
		//创建一个configuration对象，直接new一个，对象，构造方法的参数就是freeMarker的版本
		Configuration configuration = new Configuration(Configuration.getVersion());
		//设置模板文件所在的路径
		configuration.setDirectoryForTemplateLoading(new File("D:/workspaces-taotao/taotao-item-web/src/main/resources/ftl"));
		//设置模板文件的字符集，一般是utf8
		configuration.setDefaultEncoding("utf-8");
		//加载一个模板，创建一个模板对象
//		Template template = configuration.getTemplate("hello.ftl");
		Template template = configuration.getTemplate("first.htm");
		//创建一个模板使用的数据集，可以是pojo也可以是map，一般是map
		Map dataModel = new HashMap();
		//向数据集中添加数据
//		dataModel.put("hello", "this is my first freemarker test");
		dataModel.put("title", "this is my first freemarker test");
		
		List<Students> list = new ArrayList<>();
		list.add(new Students(1, "张三", 15, "长城科技园"));
		list.add(new Students(2, "张三2", 15, "长城科技园"));
		list.add(new Students(3, "张三3", 15, "长城科技园"));
		list.add(new Students(4, "张三4", 15, "长城科技园"));
		list.add(new Students(5, "张三5", 15, "长城科技园"));
		list.add(new Students(6, "张三6", 15, "长城科技园"));
		dataModel.put("stuList", list);
		
		dataModel.put("stu", new Students(1, "张三", 15, "长城科技园"));
		//创建一个writer对象，一般创建FileWriter对象，指定生成的文件名
		Writer out = new FileWriter(new File("D:/temp/freemarker/first.html"));
		//调用模板对象的process方法输出文件
		template.process(dataModel, out);
		//关闭流
		out.close();
	}
}
