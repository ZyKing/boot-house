package com.etoak.util;


import com.etoak.exception.ParamException;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.*;
import java.util.Iterator;
import java.util.Set;

public class ValidationUtil {
    private static Validator validator;
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    public static void validate(Object object){
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if(CollectionUtils.isNotEmpty(violations)){
            Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
            StringBuffer messageBuf = new StringBuffer();
            while (iterator.hasNext()){
                ConstraintViolation<Object> violation = iterator.next();
                String message = violation.getMessage();
                messageBuf.append(message).append(";");
            }
            throw new ParamException("参数错误："+messageBuf.toString());
        }
        String message = violations.iterator().next().getMessage();
        System.out.println(message);

    }
}
