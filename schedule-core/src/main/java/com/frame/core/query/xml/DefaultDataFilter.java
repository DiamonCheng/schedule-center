package com.frame.core.query.xml;

import com.frame.core.components.GsonFactory;
import com.frame.core.query.xml.definition.ColumnDefinition;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DefaultDataFilter implements DataFilter{

	@Override
	public String filt(Object v, ColumnDefinition columnDefinition) {
		if (v==null) return "";
		if (v instanceof Date) return new SimpleDateFormat(GsonFactory.getInstance().getDefaultOutputDateFomart()).format(v);
		return v.toString();
	}

}
