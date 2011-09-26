package com.sfeir.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PageContainer extends Composite {

	@UiField
	HTMLPanel pageContainer;
	@UiField
	SimplePanel gridContainer;
	@UiField
	Grid grid;
	@UiField
	Image image1, image2, image3, image4;
	
	interface PageContainerUiBinder extends UiBinder<Widget, PageContainer> {}
	private static PageContainerUiBinder uiBinder = GWT.create(PageContainerUiBinder.class);

	public PageContainer() {
		this.initWidget(uiBinder.createAndBindUi(this));
		
		this.initWidgetCss();
	}

	private void initWidgetCss() {
		pageContainer.addStyleName("pageContainer");
		
		gridContainer.addStyleName("gridContainer");
		grid.addStyleName("grid");
		
		image1.addStyleName("image");
		image2.addStyleName("image");
		image3.addStyleName("image");
		image4.addStyleName("image");
	}
	
}
