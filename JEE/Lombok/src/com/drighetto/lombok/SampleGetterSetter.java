package com.drighetto.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;




/**
 * Sample POJO in order to show the use of the LOMBOK annotations :
 * <ul>
 * <li>@Getter</li>
 * <li>@Setter</li>
 * <li>@NonNull</li>
 * </ul>
 * <a href="http://projectlombok.org/features/GetterSetter.html">Getter/Setter documentation page</a>
 * <br><br>
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SampleGetterSetter {

	/**
	 * Expose a property with public getter and setter. The @Null annotation is
	 * used to indicate that a null value cannot be passed to the setter methods
	 * and thus Lombok will add a null check in the setter methods
	 */
	@Getter
	@Setter
	@NonNull
	private String property01;

	/**
	 * Expose a property with protected getter and setter, in this case the
	 * getter method will be named "isProperty02()" according to JavaBeans
	 * naming conventions
	 */
	@Getter(AccessLevel.PROTECTED)
	@Setter(AccessLevel.PROTECTED)
	private boolean property02;

	/** Expose a property with default (package visibility) getter and setter*/
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private String property03;

}
