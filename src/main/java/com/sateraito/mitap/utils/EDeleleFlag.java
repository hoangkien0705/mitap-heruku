package com.sateraito.mitap.utils;

public enum EDeleleFlag {
	NOT_DELETE(0), DELETED(1);
	
	private int value;
	
	EDeleleFlag(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
