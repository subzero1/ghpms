package com.netsky.base.service.threadDemo;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.netsky.base.dataObjects.interfaces.ThreadServiceInterface;

@Service("testservice")
public class TestService implements ThreadServiceInterface {
	public void handle(Map<String, String> paramMap) throws Exception {
		test(paramMap.get("s"));
	}

	private void test(String s) {
		System.out.println(s);
	}
}
