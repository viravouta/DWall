package com.sfeir.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PageContainer extends Composite {

	@UiField
	AbsolutePanel pageContainer;
	@UiField
	SimplePanel gridContainer;
	@UiField
	Grid grid;
	@UiField
	Image image1, image2, image3, image4;
	
	interface PageContainerUiBinder extends UiBinder<Widget, PageContainer> {}
	private static PageContainerUiBinder uiBinder = GWT.create(PageContainerUiBinder.class);

	/**
	 * Full screen state.
	 */
	private boolean fullScreen;
	
	private final List<Image> images;
	
	public PageContainer() {
		this.initWidget(uiBinder.createAndBindUi(this));
		
		this.images = new ArrayList<Image>();
		this.images.add(image1);
		this.images.add(image2);
		this.images.add(image3);
		this.images.add(image4);
		
		this.initWidgets();
		this.initWidgetsCss();
		this.initWidgetsHandler();
	}

	private void initWidgets() {
		this.fullScreen = false;
	}
	
	private void initWidgetsCss() {
		pageContainer.addStyleName("pageContainer");
		
		gridContainer.addStyleName("gridContainer");
		grid.addStyleName("grid");
		
		for(Image image : this.images) {			
			image.addStyleName("image");
		}
	}
	
	private void initWidgetsHandler() {
		ClickHandler onClickImage = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final Image imageClicked = (Image)event.getSource();
				
				
				if(PageContainer.this.fullScreen) {
					for(Image image : PageContainer.this.images) {
						image.setVisible(true);
					}
					
					imageClicked.addStyleName("image");
					imageClicked.removeStyleName("imageClicked");
					
					PageContainer.this.fullScreen = false;
				} else {			
					for(Image image : PageContainer.this.images) {
						image.setVisible(false);
					}
					
					imageClicked.setVisible(true);
					imageClicked.removeStyleName("image");
					imageClicked.addStyleName("imageClicked");
					
					PageContainer.this.fullScreen = true;
				}
				
			}
		};
		
		image1.addClickHandler(onClickImage);
		image2.addClickHandler(onClickImage);
		image3.addClickHandler(onClickImage);
		image4.addClickHandler(onClickImage);
	}
	
}
