package com.simple.training.domain.treino;

import java.beans.PropertyEditorSupport;

public class LocalTreinoPropertyEditor extends PropertyEditorSupport{
	public void setAsText(String text) {
		if (text != null) {
			this.setValue(LocalTreino.valueOf(text));
		}
	}
}
