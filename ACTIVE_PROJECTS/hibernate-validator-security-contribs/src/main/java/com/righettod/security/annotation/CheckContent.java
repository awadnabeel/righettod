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

import com.righettod.security.annotation.validator.CheckContentValidator;

/**
 * Define annotation to validate content of a string, elements below are checked:<br />
 * <ul>
 * <li>Character continuous repetition</li>
 * <li>Character list against characters whitelist</li>
 * </ul>
 * Not others check like range,max,min length,... are implemented because there others existing annotations that can do the job (see link below).
 * 
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#table-spec-constraints"
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckContentValidator.class)
@Documented
public @interface CheckContent {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.righettod.security.annotation.checkcontent}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* Annotation specific attributes */

	/** Identifier of the characters whitelist to use (identifier must be a key present into the whitelist classpath property file). */
	String whitelistIdentifier() default "default";

	/** Locale used to load bundle containing whitelist definition property file */
	String whitelistLocale() default "";

	/**
	 * Json expression to define map representing collection of characters for which the continuous repetition is limited to specified count.<br/>
	 * Key is the character and Value is the repetition limit count.<br/>
	 * Ex: {"(":1,"-":1,".":3,"'":1}
	 */
	String continuousRepetitionLimitationMapJsonExpr() default "";
}
