package com.sateraito.mitap.model.response;


public class ReponseMdl {
	public static int SUCCESS = 0;
	private static int ERR_DEFAULT = 1;

	private String message;
	private int errorCode;
	private Object data;
	
	private static ReponseMdl instance;
	private static ReponseMdl instanceErrorDefault;
	private static ReponseMdl instanceErrorMessage;
	public static ReponseMdl getInsSuccess() {
		instance = new ReponseMdl(SUCCESS, MessageReponse.MSG_SUCCESS);
		return instance;
	}
	
	public static ReponseMdl getInsErrorDefault() {
		instanceErrorDefault = new ReponseMdl(ERR_DEFAULT, MessageReponse.MSG_ERR_DEFAULT);
		return instanceErrorDefault;
	}
	
	public static ReponseMdl getInsErrorMessage(int errorCode, String message) {
		instanceErrorMessage = new ReponseMdl();
		instanceErrorMessage.setMessage(message);
		instanceErrorMessage.setErrorCode(errorCode);
		return instanceErrorMessage;
	}

	public ReponseMdl() {
		super();
	}

	public ReponseMdl(int errorCode, String message) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	

	public ReponseMdl(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
