package com.sateraito.mitap.utils;

public class Log {
	private static boolean FLAG_DEBUG = true;
	
	public static final void e(String message) {
		if (FLAG_DEBUG) {
			StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
			int maxLogSize = 1000;
			for(int i = 0; i <= message.length() / maxLogSize; i++) {
				int start = i * maxLogSize;
				int end = (i+1) * maxLogSize;
				end = end > message.length() ? message.length() : end;
				System.err.println(stackTraceElement.getFileName() + " in " + stackTraceElement
						.getMethodName() + " at line: " + stackTraceElement.getLineNumber() + " :: "+ message.substring(start, end));
			}
		}
	}
	
	public static final void d(String message){
		if (FLAG_DEBUG) {
			StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
			int maxLogSize = 1000;
			for(int i = 0; i <= message.length() / maxLogSize; i++) {
				int start = i * maxLogSize;
				int end = (i+1) * maxLogSize;
				end = end > message.length() ? message.length() : end;
				System.out.println(stackTraceElement.getFileName() + " in " + stackTraceElement
						.getMethodName() + " at line: " + stackTraceElement.getLineNumber() + " :: "+ message.substring(start, end));
			}
		}
	}
}
