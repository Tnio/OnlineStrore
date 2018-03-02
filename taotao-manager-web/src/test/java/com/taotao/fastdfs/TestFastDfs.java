package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;

public class TestFastDfs {

	
	@Test
	public void testUpload() throws Exception{
		//1，加载一个配置文件fast_dfs.conf,配置文件的内容就是指定TrackerServer的地址
		//2，加载配置文件
		ClientGlobal.init("D:/workspaces-taotao/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		//3，创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//4，通过TrackerClient对象获得TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//5，创建一个storageServer的引用，赋值为null就可以了
		StorageServer storageServer = null;
		//6，创建一个storageClient对象，两个参数为TrackerServer，storageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7，使用storageClient对象上传文件
		//参数1：文件名		参数2：文件扩展名，不包含"." 	参数3：文件的元数据
		String[] upload_file = storageClient.upload_file("C:/Users/yl/Desktop/2.jpg", "jpg",null );
		for (String string : upload_file) {
			System.out.println(string);
		}
	}
	
	
	@Test
	public void testFastDFSClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("D:/workspaces-taotao/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		String uploadFile = fastDFSClient.uploadFile("C:/Users/yl/Desktop/2.jpg");
		System.out.println(uploadFile);
	}
	
}
