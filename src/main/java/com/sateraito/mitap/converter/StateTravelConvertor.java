package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

import com.sateraito.mitap.utils.EStateTravel;

public class StateTravelConvertor implements AttributeConverter<EStateTravel, Integer> {

	@Override
	public Integer convertToDatabaseColumn(EStateTravel attribute) {
		if(attribute == EStateTravel.WAITING_REGISTER) {
			return 1;
		} else if(attribute == EStateTravel.CONFIRM_DIRECTOR) {
			return 2;
		} else if(attribute == EStateTravel.FINISH) {
			return 3;
		} 
		return 1;
	}
	
	@Override
	public EStateTravel convertToEntityAttribute(Integer dbData) {
		if(dbData == 1) return EStateTravel.WAITING_REGISTER;
		else if(dbData == 2) return EStateTravel.CONFIRM_DIRECTOR;
		else if(dbData == 3) return EStateTravel.FINISH;
		return EStateTravel.WAITING_REGISTER;
	}
}
