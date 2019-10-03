package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

import com.sateraito.mitap.utils.EStateDirectTravel;

public class StateDirectTravelConvertor implements AttributeConverter<EStateDirectTravel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(EStateDirectTravel attribute) {
		if(attribute == EStateDirectTravel.USER_DIRECT_PERFORM) {
			return 1;
		} else if(attribute == EStateDirectTravel.USER_TOURIST_CONFIRM) {
			return 2;
		} else if(attribute == EStateDirectTravel.USER_TOURIST_CANCEL) {
			return 3;
		} else if(attribute == EStateDirectTravel.REGISTER_SUCCESS) {
			return 4;
		} else if(attribute == EStateDirectTravel.ADMIN_CANCEL) {
			return 5;
		} 
		return 1;
	}
	
	@Override
	public EStateDirectTravel convertToEntityAttribute(Integer dbData) {
		if(dbData == 1) return EStateDirectTravel.USER_DIRECT_PERFORM;
		else if(dbData == 2) return EStateDirectTravel.USER_TOURIST_CONFIRM;
		else if(dbData == 3) return EStateDirectTravel.USER_TOURIST_CANCEL;
		else if(dbData == 4) return EStateDirectTravel.REGISTER_SUCCESS;
		else if(dbData == 5) return EStateDirectTravel.ADMIN_CANCEL;
		return EStateDirectTravel.USER_DIRECT_PERFORM;
	}
}
