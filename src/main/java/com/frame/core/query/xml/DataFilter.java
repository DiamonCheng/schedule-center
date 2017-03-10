package com.frame.core.query.xml;

import com.frame.core.query.xml.definition.ColumnDefinition;

public interface DataFilter {
	String filt(Object v, ColumnDefinition columnDefinition);
}
