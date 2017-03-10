package com.frame.core.query.xml;

import java.util.Date;

import com.frame.core.components.GsonFactory;
import com.frame.core.query.xml.definition.ColumnDefinition;

public class DefaultDataFilter implements DataFilter{

	@Override
	public String filt(Object v, ColumnDefinition columnDefinition) {
		if (v==null) return "";
		if (v instanceof Date) return GsonFactory.getDateFormart2().format(v);
		return v.toString();
	}

}
