package com.drighetto.lombok;

import lombok.Data;
import lombok.NonNull;

/**
 * Sample POJO in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@Data</li>
 * <li>@NonNull</li>
 * </ul>
 * <br>
 * Extracted from the documentation page<: <br>
 * <i> Data is a convenient shortcut annotation that bundles the features of
 * ToString, EqualsAndHashCode and Getter / Setter together: In other words,
 * Data generates all the boilerplate that is normally associated with simple
 * POJOs (Plain Old Java Objects) and beans: getters for all fields, setters for
 * all non-final fields, and appropriate toString, equals and hashCode
 * implementations that involve the fields of the class. In addition, Data
 * generates a constructor that initializes all final fields, as well as all
 * non-final fields with no initializer that have been marked with NonNull or
 * NotNull, in order to ensure the field is never null.</i><br>
 * 
 * <a href="http://projectlombok.org/features/Data.html">Data documentation
 * page</a> <br>
 * <br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@Data
public class SampleData {
	/*
	 * Add the @NonNull annotation will cause this field will be included in the
	 * class single generated constructor
	 */
	@NonNull
	private int ssId;

	private String name;
}
