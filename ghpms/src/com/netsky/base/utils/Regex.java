package com.netsky.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static String[] getStringsByRegex(String inputString, String regex) {
		List<String> _list = new ArrayList<String>();
		Pattern _pattern = Pattern.compile(regex);
		Matcher _matcher = _pattern.matcher(inputString);
		int _start = 0;
		while (_matcher.find(_start)) {
			_list.add(_matcher.group());
			_start = _matcher.end();
		}
		String[] _array = new String[_list.size()];
		for (int _index = 0; _index < _array.length; _index++) {
			_array[_index] = _list.get(_index);
		}
		return _array;
	}

	public static void main(String[] args) {
		String[] arr = Regex.getStringsByRegex("[a][b][c][d][]", "\\[[^\\]]*\\]");//Æ¥Åä[]
		for (String string : arr) {
			System.out.println(string);
		}
	}

}
