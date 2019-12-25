package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

import com.sateraito.mitap.utils.EDeleleFlag;

public class DeleteFlagConvertor implements AttributeConverter<EDeleleFlag, Integer>{

	@Override
	public Integer convertToDatabaseColumn(EDeleleFlag attribute) {
		if(attribute == EDeleleFlag.NOT_DELETE) {
			return EDeleleFlag.NOT_DELETE.getValue();
		} else if(attribute == EDeleleFlag.DELETED) {
			return EDeleleFlag.DELETED.getValue();
		} else {
			return EDeleleFlag.DELETED.getValue();
		}
	}

	@Override
	public EDeleleFlag convertToEntityAttribute(Integer dbData) {
		if(dbData == EDeleleFlag.NOT_DELETE.getValue()) {
			return EDeleleFlag.NOT_DELETE;
		} else if(dbData == EDeleleFlag.DELETED.getValue()) {
			return EDeleleFlag.DELETED;
		} else {
			return EDeleleFlag.DELETED;
		}
	}

}
