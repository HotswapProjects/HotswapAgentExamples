package org.hotswap.agent.example.deltaspike.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public UserRole convertToEntityAttribute(String code) {
        return UserRole.getByCode(code);
    }
}
