package com.codegeeks.jpa.util;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, Integer> {

	public Integer convertToDatabaseColumn(Boolean aBoolean) {
		if(Boolean.TRUE.equals(aBoolean)) {
			return 1;
		}else {
			return -1;
		}
	}

	public Boolean convertToEntityAttribute(Integer value) {
		if(value == null ) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}

	
}
