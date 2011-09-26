package com.sfeir.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PageContainer extends Composite {

	@UiField
	HTMLPanel pageContainer;
	@UiField
	SimplePanel wallItem1, wallItem2, wallItem3, wallItem4;
	
	interface PageContainerUiBinder extends UiBinder<Widget, PageContainer> {}
	private static PageContainerUiBinder uiBinder = GWT.create(PageContainerUiBinder.class);

	public PageContainer() {
		this.initWidget(uiBinder.createAndBindUi(this));
		
		this.initWidgetCss();
	}

	private void initWidgetCss() {
		pageContainer.addStyleName("pageContainer");
		
		wallItem1.addStyleName("wallItem");
		wallItem2.addStyleName("wallItem");
		wallItem3.addStyleName("wallItem");
		wallItem4.addStyleName("wallItem");
	}
	
}
