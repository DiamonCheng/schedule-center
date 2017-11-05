package com.frame.core.query.xml.annoation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefinition {
	String value() default "pageDefinition.xml";
}
