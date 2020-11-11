package com.springboot.utils;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * sftp工具类 实现文件上传、下载、删除操作
 * 
 * @author
 * @date
 */
public class SftpUtil {
	private transient Logger log = LoggerFactory.getLogger(this.getClass());

	private ChannelSftp sftp;

	private Session session;
	/** SFTP 登录用户名 */
	private String username;
	/** SFTP 登录密码 */
	private String password;
	/** 私钥 */
	private String privateKey;
	/** SFTP 服务器地址IP地址 */
	private String host;
	/** SFTP 端口 */
	private int port;

	/**
	 * 构造基于密码认证的sftp对象
	 */
	public SftpUtil(String username, String password, String host, int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造基于秘钥认证的sftp对象
	 */
	public SftpUtil(String username, String host, int port, String privateKey) {
		this.username = username;
		this.host = host;
		this.port = port;
		this.privateKey = privateKey;
	}

	public SftpUtil() {
	}

	/**
	 * 连接sftp服务器
	 */
	public void login() {
		try {
			JSch jsch = new JSch();
			if (privateKey != null) {
				// 设置私钥
				jsch.addIdentity(privateKey);
			}
			session = jsch.getSession(username, host, port);
			if (password != null) {
				session.setPassword(password);
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");

			session.setConfig(config);
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接 server
	 */
	public void logout() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	/**
	 * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	 * 
	 * @param basePath 服务器的基础路径
	 * @param directory 上传到该目录
	 * @param sftpFileName sftp端文件名
	 * @param input 输入流
	 */
	public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
		try {
			log.info("basePath：" + basePath);
			sftp.cd(basePath);
			sftp.cd(directory);
		} catch (SftpException e) {
			// 目录不存在，则创建文件夹
			String[] dirs = directory.split("/");
			String tempPath = basePath;
			for (String dir : dirs) {
				if (null == dir || "".equals(dir)) {
					continue;
				}
				tempPath += "/" + dir;
				try {
					sftp.cd(tempPath);
				} catch (SftpException ex) {
					sftp.mkdir(tempPath);
					sftp.cd(tempPath);
				}
			}
		}
		// 上传文件
		sftp.put(input, sftpFileName);
	}

	/**
	 * 下载文件。
	 * 
	 * @param directory 下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile 存在本地的路径
	 */
	public void download(String directory, String downloadFile, String saveFile)
			throws SftpException, FileNotFoundException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	 * 判断远程SFTP服务器上是否存在某个文件
	 * 
	 * @param directory 目录
	 * @param fileName 文件名
	 * @return 是否存在
	 */
	public boolean isExists(String directory, String fileName) {
		boolean isHave = false;
		try {
			sftp.cd(directory);
			SftpATTRS attrs = sftp.stat(fileName);
			if (attrs != null) {
				isHave = true;
			}
		} catch (Exception e) {
		}
		return isHave;
	}

	/**
	 * 下载文件
	 * 
	 * @param directory 下载目录
	 * @param downloadFile 下载的文件名
	 * @return 字节数组
	 */
	public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
		if (directory != null && !"".equals(directory)) {
			sftp.cd(directory);
		}
		InputStream is = sftp.get(downloadFile);
		byte[] fileData = IOUtils.toByteArray(is);
		return fileData;
	}

	/**
	 * 删除文件
	 * 
	 * @param directory 要删除文件所在目录
	 * @param deleteFile 要删除的文件
	 */
	public void delete(String directory, String deleteFile) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory 要列出的目录
	 */
	public Vector<?> listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	/**
	 * 测试Main方法
	 * 
	 * @param args
	 * @throws SftpException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SftpException, IOException {
		SftpUtil sftp = new SftpUtil("zhnx", "Zhnx$p=!@#z@n$h&x", "139.224.145.186", 22022);
		sftp.login();
		/*
		 * File file = new File("F:\\img\\timg.jpg"); InputStream is = new
		 * FileInputStream(file);
		 * 
		 * sftp.upload("/IN","", "timg.jpg", is);
		 */
		byte[] bytes = sftp.download("/IN", "timg.jpg");
		ByteUtil.saveFile(bytes, "F:\\img2", "timg2.jpg");
		sftp.logout();
	}
}
