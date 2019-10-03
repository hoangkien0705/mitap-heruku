package com.sateraito.mitap.converter;

import javax.persistence.AttributeConverter;

public class BooleanConvertor implements AttributeConverter<Boolean, Integer> {

	/**
	 * @attribute: false => return 0;
	 * @attribute: true  => return 1;
	 */
	@Override
	public Integer convertToDatabaseColumn(Boolean attribute) {
		return attribute ? 1 : 0;
	}
	
	/**
	 * @dbData: 0 => return false
	 * @dbData: 1 => return true;
	 * @dbData: diff => return true;
	 */

	@Override
	public Boolean convertToEntityAttribute(Integer dbData) {
		return dbData == 0 ? false : true;
	}

}
