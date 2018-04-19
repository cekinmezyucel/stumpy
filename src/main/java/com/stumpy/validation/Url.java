package com.stumpy.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.stumpy.validation.validator.UrlValidator;

/**
 * Url Validation Annotation.
 * 
 * @author Yucel Cekinmez
 */
@Documented
@Constraint(validatedBy = {UrlValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface Url {

  /**
   * URL validation error default message.
   * 
   * @return {@link String}.
   */
  String message() default "Given Url is invalid.";

  /**
   * groups.
   * 
   * @return groups.
   */
  Class<?>[] groups() default {};

  /**
   * payload.
   * 
   * @return payload.
   */
  Class<? extends Payload>[] payload() default {};

}
