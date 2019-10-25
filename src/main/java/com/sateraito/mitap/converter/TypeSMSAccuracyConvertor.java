package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

import com.sateraito.mitap.utils.ESMSTypeAccuracyPhone;

public class TypeSMSAccuracyConvertor implements AttributeConverter<ESMSTypeAccuracyPhone, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ESMSTypeAccuracyPhone attribute) {
		if(attribute == ESMSTypeAccuracyPhone.REGISTER_VERIFY_PHONENUMBER) {
			return 1;
		} else if(attribute == ESMSTypeAccuracyPhone.FORGOT_PASSWORD) {
			return 2;
		}
		return 1;
	}
	
	@Override
	public ESMSTypeAccuracyPhone convertToEntityAttribute(Integer dbData) {
		if(dbData == 1) return ESMSTypeAccuracyPhone.REGISTER_VERIFY_PHONENUMBER;
		else if(dbData == 2) return ESMSTypeAccuracyPhone.FORGOT_PASSWORD;
		return ESMSTypeAccuracyPhone.REGISTER_VERIFY_PHONENUMBER;
	}
}
