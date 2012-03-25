package com.righettod.security.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.righettod.security.annotation.validator.NoTagValidator;

/**
 * Define annotation to validate content of a string, check that the string contains no HTML/XML tags
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#table-spec-constraints"
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoTagValidator.class)
@Documented
public @interface NoTag {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.righettod.security.annotation.notag}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* No Annotation specific attributes */
}
