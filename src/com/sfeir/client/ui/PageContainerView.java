package com.sfeir.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.client.animation.ChocAnimation;

/**
 * This class defines page container view.
 */
public class PageContainerView extends Composite {

	@UiField
	AbsolutePanel wallContainer;
	@UiField
	SimplePanel gridContainer;
	@UiField
	Grid grid;
	@UiField
	SimplePanel imageContainer1, imageContainer2, imageContainer3, imageContainer4,
	imageContainer5, imageContainer6, imageContainer7, imageContainer8, imageContainer9;
	@UiField
	Image image1, image2, image3, image4, image5, image6, image7, image8, image9;
	
	interface PageContainerUiBinder extends UiBinder<Widget, PageContainerView> {}
	private static PageContainerUiBinder uiBinder = GWT.create(PageContainerUiBinder.class);

	/**
	 * List of image containers.
	 */
	private List<SimplePanel> imageContainers;
	
	/**
	 * List of image.
	 */
	private List<GridItem> gridItems;

	/**
	 * Full screen state.
	 */
	private boolean fullScreen;
	
	/**
	 * Image animation timer.
	 */
	private Timer timer;
	
	/**
	 * Content panel after click on image.
	 */
	private FlowPanel contentAfterClick;
	
	/**
	 * Minus button panel.
	 */
	private SimplePanel minusButton;
	
	/**
	 * Menu button panel.
	 */
	private SimplePanel menuButton;
	
	/**
	 * Create page container view object.
	 */
	public PageContainerView() {
		this.initWidget(uiBinder.createAndBindUi(this));
		
		this.initWidgets();
		this.initWidgetsCss();
		this.initWidgetsHandler();
		
		this.initAnimation();
	}

	private void initAnimation() {
	    
	    for(SimplePanel panel : imageContainers){
		panel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		panel.getElement().getStyle().setTop(-99, Unit.PX);
		panel.getElement().getStyle().setLeft(319, Unit.PX);
	    }

	    ChocAnimation animation1 = new ChocAnimation(imageContainer1, 319, -99, 1, -299);
	    final ChocAnimation animation2 = new ChocAnimation(imageContainer2, 319, -99, 319, -299);
	    final ChocAnimation animation3 = new ChocAnimation(imageContainer3, 319, -99, 637, -299);
	    final ChocAnimation animation4 = new ChocAnimation(imageContainer4, 319, -99, 1, -99);
	    //final ChocAnimation animation5 = new ChocAnimation(imageContainer5, 319, -99, 319, -99);
	    final ChocAnimation animation6 = new ChocAnimation(imageContainer6, 319, -99, 637, -99);
	    final ChocAnimation animation7 = new ChocAnimation(imageContainer7, 319, -99, 1, 101);
	    final ChocAnimation animation8 = new ChocAnimation(imageContainer8, 319, -99, 319, 101);
	    final ChocAnimation animation9 = new ChocAnimation(imageContainer9, 319, -99, 637, 101);
	    
	    final Timer timer7 = new Timer() {
	        @Override
	        public void run() {
	            animation9.run(1000);
	        }
	    };
	    
	    final Timer timer6 = new Timer() {
	        @Override
	        public void run() {
	            animation8.run(1000);
	            timer7.schedule(1000);
	        }
	    };
	    
	    
	    final Timer timer5 = new Timer() {
	        @Override
	        public void run() {
	            animation7.run(1000);
	            timer6.schedule(1000);
	        }
	    };
	    
	    final Timer timer4 = new Timer() {
	        @Override
	        public void run() {
	            animation6.run(1000);
	            timer5.schedule(1000);
	        }
	    };
	    
	    final Timer timer3 = new Timer() {
	        @Override
	        public void run() {
	            animation4.run(1000);
	            timer4.schedule(1000);
	        }
	    };
	    
	    final Timer timer2 = new Timer() {
	        @Override
	        public void run() {
	            animation3.run(1000);
	            timer3.schedule(1000);
	        }
	    };
	    Timer timer = new Timer() {
	        @Override
	        public void run() {
	            animation2.run(1000);
	            timer2.schedule(1000);
	        }
	    };
	    
	    
	    animation1.run(1000);
	    
	    timer.schedule(1000);
//	    animation3.run(2000);
//	    animation4.run(2000);
//	    animation5.run(2000);
//	    animation6.run(2000);
//	    animation7.run(2000);
//	    animation8.run(2000);
//	    animation9.run(2000);
	}

	/**
	 * Initialization of widgets.
	 */
	private void initWidgets() {
		this.imageContainers = new ArrayList<SimplePanel>();
		this.imageContainers.add(imageContainer1);
		this.imageContainers.add(imageContainer2);
		this.imageContainers.add(imageContainer3);
		this.imageContainers.add(imageContainer4);
		this.imageContainers.add(imageContainer5);
		this.imageContainers.add(imageContainer6);
		this.imageContainers.add(imageContainer7);
		this.imageContainers.add(imageContainer8);
		this.imageContainers.add(imageContainer9);
		
		this.gridItems = new ArrayList<GridItem>();
		this.gridItems.add(new GridItem(image1, 0, 0));
		this.gridItems.add(new GridItem(image2, 0, 1));
		this.gridItems.add(new GridItem(image3, 0, 2));
		this.gridItems.add(new GridItem(image4, 1, 0));
		this.gridItems.add(new GridItem(image5, 1, 1));
		this.gridItems.add(new GridItem(image6, 1, 2));
		this.gridItems.add(new GridItem(image7, 2, 0));
		this.gridItems.add(new GridItem(image8, 2, 1));
		this.gridItems.add(new GridItem(image9, 2, 2));
		
		this.fullScreen = false;

		this.minusButton = new SimplePanel();
		this.menuButton = new SimplePanel();
		
		this.contentAfterClick = new FlowPanel();
		this.contentAfterClick.add(minusButton);
		this.contentAfterClick.add(menuButton);
	}
	
	/**
	 * Initialization of widgets CSS.
	 */
	private void initWidgetsCss() {
		wallContainer.addStyleName("wallContainer");
		gridContainer.addStyleName("gridContainer");
		grid.addStyleName("grid");
		
		for(SimplePanel imageContainer : this.imageContainers) {
			imageContainer.addStyleName("imageContainer");
		}
		
		for(GridItem gridItem : this.gridItems) {			
			gridItem.getImage().addStyleName("image");
		}
		
		this.minusButton.addStyleName("minus");
		this.menuButton.addStyleName("menu");
	}
	
	/**
	 * Initialization of widgets handler.
	 */
	private void initWidgetsHandler() {
		ClickHandler onClickImage = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final Image imageClicked = (Image)event.getSource();
				
				int row = 0;
				int column = 0;
				for(GridItem gridItem : PageContainerView.this.gridItems) {
					if(gridItem.getImage().equals(imageClicked)) {
						row = gridItem.getRowNumber();
						column = gridItem.getColumnNumber();
					}
				}
				
				// Zoom.
				if(!PageContainerView.this.fullScreen) {
					PageContainerView.this.fullScreen = true;
					
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						gridItem.getImage().setVisible(false);
					}
					
					imageClicked.setVisible(true);
					imageClicked.removeStyleName("image");
					imageClicked.addStyleName("imageClicked");
					
					switch (row) {
					case 1:
						imageClicked.addStyleName("imageSecondRowClicked");
						break;
					case 2:
						imageClicked.addStyleName("imageThirdRowClicked");
						break;
					default:
						imageClicked.addStyleName("imageFirstRowClicked");
					}
					
					switch (column) {
					case 1:
						imageClicked.addStyleName("imageSecondColumnClicked");
						break;
					case 2:
						imageClicked.addStyleName("imageThirdColumnClicked");
						break;
					default:
						imageClicked.addStyleName("imageFirstColumnClicked");
					}
				} else {	
					PageContainerView.this.fullScreen = false;
					
					imageClicked.addStyleName("image");
					imageClicked.removeStyleName("imageClicked");
					
					switch (row) {
					case 1:
						imageClicked.removeStyleName("imageSecondRowClicked");
						break;
					case 2:
						imageClicked.removeStyleName("imageThirdRowClicked");
						break;
					default:
						imageClicked.removeStyleName("imageFirstRowClicked");
					}
					
					switch (column) {
					case 1:
						imageClicked.removeStyleName("imageSecondColumnClicked");
						break;
					case 2:
						imageClicked.removeStyleName("imageThirdColumnClicked");
						break;
					default:
						imageClicked.removeStyleName("imageFirstColumnClicked");
					}
					
					PageContainerView.this.timer = new Timer() {
						
						@Override
						public void run() {
							for(GridItem gridItem : PageContainerView.this.gridItems) {
								gridItem.getImage().setVisible(true);
							}
						}
					};
					
					PageContainerView.this.timer.schedule(1000);
				}
				
			}
		};
		
		for(GridItem gridItem : this.gridItems) {
			gridItem.getImage().addClickHandler(onClickImage);
		}
	}
	
}
