package com.simple.training.domain.json;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDatePropertyEditor extends PropertyEditorSupport{
	public void setAsText(String text) {
		if (text != null) {
			this.setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
	}
}
