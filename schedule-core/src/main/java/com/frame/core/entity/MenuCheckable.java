package com.frame.core.entity;

import java.util.List;

public class MenuCheckable extends MenuEntity {
	private static final long serialVersionUID = -3960823525286808635L;
	private boolean checked=false;
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<MenuCheckable> getChildren2() {
		return children2;
	}
	public void setChildren2(List<MenuCheckable> children2) {
		this.children2 = children2;
	}
	private List<MenuCheckable> children2;
		
}
