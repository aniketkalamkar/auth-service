package com.omnirio.assignment.authservice.domain;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {

		@Override
		public String convertToDatabaseColumn(RoleType roleType) {
			if (roleType == null) {
				return null;
			}
			return roleType.getCode();
		}

		@Override
		public RoleType convertToEntityAttribute(String code) {

			if (code == null) {
				return null;
			}

			return Stream.of(RoleType.values()).filter(c -> c.getCode().equals(code)).findFirst()
					.orElseThrow(IllegalArgumentException::new);
		}
	}
