package com.righettod.security.annotation;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the validator performing processing for annotation "CheckContent".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * 
 */
public class CheckContentValidator implements ConstraintValidator<CheckContent, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckContentValidator.class);

	/** Identifier of the characters whitelist to use (identifier must be a key present into the whitelist classpath property file) */
	private String whitelistIdentifier;

	/** Json expression to define map representing collection of character for which the continuous repetition is limited to specified count */
	private String continuousRepetitionLimitationMapJsonExpr;

	/** Characters whitelist associated to specified identifier */
	private String whitelistCharacterSet = null;

	/** map representing collection of character for which the continuous repetition is limited to specified count derived from Json expression */
	private Map<String, Integer> continuousRepetitionLimitationMap = null;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(CheckContent constraintAnnotation) {
		// Direct assignement
		this.whitelistIdentifier = constraintAnnotation.whitelistIdentifier();
		this.continuousRepetitionLimitationMapJsonExpr = constraintAnnotation.continuousRepetitionLimitationMapJsonExpr();
		// Load characters whitelist associated to specified identifier
		if (StringUtils.isEmpty(this.whitelistIdentifier)) {
			this.whitelistIdentifier = "default";
		}
		try {
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream("/whitelists.properties"));
			if (!props.containsKey(this.whitelistIdentifier)) {
				if (LOGGER.isWarnEnabled()) {
					LOGGER.warn("'{}' identifier not found into whitelists definition bundle, revert to default whitelist !", this.whitelistIdentifier);
				}
				this.whitelistIdentifier = "default";
			}
			this.whitelistCharacterSet = props.getProperty(this.whitelistIdentifier);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load property file containing whitelists definition from '/whitelists.properties' !", e);
		}
		// Obtains continuous repetition map from Json expression
		if (!StringUtils.isEmpty(this.continuousRepetitionLimitationMapJsonExpr)) {
			try {
				this.continuousRepetitionLimitationMap = new ObjectMapper().readValue(this.continuousRepetitionLimitationMapJsonExpr.trim(), Map.class);
				if (this.continuousRepetitionLimitationMap.isEmpty()) {
					this.continuousRepetitionLimitationMap = null;
				}
			} catch (JsonParseException e) {
				throw new RuntimeException("Unable to parse Json expression '" + this.continuousRepetitionLimitationMapJsonExpr + "' !", e);
			} catch (JsonMappingException e) {
				String sample = "{\"(\":1,\"-\":1,\".\":3,\"'\":1}";
				throw new RuntimeException("Invalid Json expression '" + this.continuousRepetitionLimitationMapJsonExpr
						+ "', expression must be a map where key is a string and value is a number !, example : " + sample, e);
			} catch (IOException e) {
				throw new RuntimeException("Unable to parse Json expression '" + this.continuousRepetitionLimitationMapJsonExpr + "' !", e);
			}
		}

		// Debug case
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CheckContentValidator['whitelistIdentifier']='{}'", this.whitelistIdentifier);
			LOGGER.debug("CheckContentValidator['whitelistCharacterSet']='{}'", this.whitelistCharacterSet);
			LOGGER.debug("CheckContentValidator['continuousRepetitionLimitationMapJsonExpr']='{}'", this.continuousRepetitionLimitationMapJsonExpr);
			LOGGER.debug("CheckContentValidator['continuousRepetitionLimitationMap']='{}'", this.continuousRepetitionLimitationMap);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@SuppressWarnings("boxing")
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValidFlg = true;
		char previous = ' ';
		char current = ' ';
		int counter = 0;
		try {
			// Apply check only is value is not empty....
			if (!StringUtils.isEmpty(value)) {
				for (int i = 0; i < value.length(); i++) {
					current = value.charAt(i);
					// Check current character against whitelist
					if (this.whitelistCharacterSet.indexOf(current) == -1) {
						isValidFlg = false;
						break;
					}
					// Check current character against continous repetition limitation map
					if ((i > 0) && (this.continuousRepetitionLimitationMap != null) && this.continuousRepetitionLimitationMap.containsKey(String.valueOf(current))) {
						if (current == previous) {
							counter++;
						} else {
							counter = 0;
						}
						if (counter > this.continuousRepetitionLimitationMap.get(String.valueOf(current))) {
							isValidFlg = false;
							break;
						}

					}
					previous = current;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
		}

		return isValidFlg;
	}
}
