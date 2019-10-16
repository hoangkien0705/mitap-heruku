package com.sateraito.mitap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.sateraito.mitap.service.MitapService;
import com.sateraito.mitap.twilio.TwilioSms;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

@SpringBootApplication
public class MitapSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitapSpringbootApplication.class, args);
		MitapService.inserErrorCode();
		
	}

}
