package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

import com.sateraito.mitap.utils.ESex;

public class SexConvertor implements AttributeConverter<ESex, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ESex attribute) {
		if(attribute == ESex.BOY) {
			return 1;
		} else if(attribute == ESex.GIRL) {
			return 2;
		} else if(attribute == ESex.GAY) {
			return 3;
		} else {
			return 1;
		}
	}

	@Override
	public ESex convertToEntityAttribute(Integer dbData) {
		if(dbData == 1) return ESex.BOY;
		else if(dbData == 2) return ESex.GIRL;
		else if(dbData == 3) return ESex.GAY;
		else return ESex.BOY;
	}

}
