package com.sfeir.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sfeir.client.ui.PageContainer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DWall implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(new PageContainer());
	}
}
