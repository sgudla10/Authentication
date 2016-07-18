package com.netgear.auth.service.helper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.netgear.auth.dto.AuthResponse;
import com.netgear.auth.dto.Error;
import com.netgear.auth.entity.UserDO;

@Component
public class ServiceHelper {
    private static final javax.validation.Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> boolean validate(final T inputBean, Error error) {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder builder = new StringBuilder();
        boolean isError = false;
        boolean isFirst = false;
        final Set<ConstraintViolation<T>> violations = VALIDATOR.validate(inputBean, Default.class);
        if (!violations.isEmpty()) {
            isError = true;
            for (final ConstraintViolation<T> violation : violations) {
                if (!isFirst) {
                    builder.append(", ");
                }
                builder.append(violation.getPropertyPath().toString() + " : ");
                builder.append(violation.getMessage());
                isFirst = false;
            }
            error.setCode("400.AuthService.BAD_REQUEST");
            error.setDesc(builder.toString());
        }
        return isError;
    }

    public AuthResponse convertDOtoDTO(UserDO userDo) {
        AuthResponse response = new AuthResponse();
        response.setEmail(userDo.getEmail());
        response.setFirstName(userDo.getFirstname());
        response.setLastName(userDo.getLastname());
        response.setName(userDo.getName());
        return response;
    }
}
