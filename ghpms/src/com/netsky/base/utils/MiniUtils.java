package com.netsky.base.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MiniUtils {
	
	/*
	 * 获得环境变量
	 */
	public String getEnv(String name) throws Exception {

		String OS = System.getProperty("os.name").toLowerCase();
		Process p = null;
		if (OS.indexOf("windows") > -1) {
			p = Runtime.getRuntime().exec("cmd /c set " + name); // 其它的操作系统可以自行处理，
																	// 我这里是win2k
		} else {
			p = Runtime.getRuntime().exec("env |grep " + name);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(p
				.getInputStream()));
		String line;
		String content = "";
		if ((line = br.readLine()) != null) {
			content += line;
		}
		if (content.indexOf("=") != -1) {
			return (content.split("="))[1];
		} else {
			return content;
		}
	}
	
	public static void main(String[] args)throws Exception{
		System.out.println(new MiniUtils().getEnv("ECLIPSE_WORKSPACE"));
	}
}
