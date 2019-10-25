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
	//account register: hoangkien07051@gmail.com
	public static final String ACCOUNT_SID = "AC758dd71e80be9884992ce0600a78b909";
    public static final String AUTH_TOKEN  = "1d456166c7bf80d7565fb8c48d5aaee0";
    
    //Số điện thoại mà twilio tự động cấu hình đóng vai trò là số điện thoại gửi
    public static final String TWILIO_NUMBER = "+12156470696";
    
    public static void main(String[] args) { 
    	Twilio.init(TwilioSms.ACCOUNT_SID, TwilioSms.AUTH_TOKEN);
        PhoneNumber receivePhoneNumber = new PhoneNumber("+84969951417");
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
