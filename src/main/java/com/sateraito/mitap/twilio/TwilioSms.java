package com.sateraito.mitap.twilio;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioSms {
	//account register: hoangkien07051@gmail.com/Matkhaula@1234
	public static final String ACCOUNT_SID = "AC758dd71e80be9884992ce0600a78b909";
//	public static final String ACCOUNT_SID = "AC2eefb9f7c019e00afee3056b9b9ca59b"; //test
    public static final String AUTH_TOKEN  = "1d456166c7bf80d7565fb8c48d5aaee0";
//    public static final String AUTH_TOKEN  = "f398912741d7e4d835322c4427fd7540"; //test
    
    //Số điện thoại mà twilio tự động cấu hình đóng vai trò là số điện thoại gửi
    public static final String TWILIO_NUMBER = "+12156470696";
    
    public static void main(String[] args) { 
    	/**
    	 * Thêm số điện thoại để test thì vào đây: https://www.twilio.com/console/phone-numbers/verified
    	 */
    	Twilio.init(TwilioSms.ACCOUNT_SID, TwilioSms.AUTH_TOKEN);
        PhoneNumber receivePhoneNumber = new PhoneNumber("+818033349588");
        Message.creator(receivePhoneNumber, new PhoneNumber(TwilioSms.TWILIO_NUMBER), "Sample Twilio SMS using Java").create();
        
		ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
        Futures.addCallback(future, new FutureCallback<ResourceSet<Message>>() {
            public void onSuccess(ResourceSet<Message> messages) {
                for (Message message : messages) {
                    System.out.println(message.getSid() + " : " + message.getStatus());
                }
            }

            public void onFailure(Throwable t) {
                System.out.println("Failed to get message status: " + t.getMessage());
            }
        }, MoreExecutors.directExecutor());
    }
}
