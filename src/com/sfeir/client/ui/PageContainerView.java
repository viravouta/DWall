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
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.sfeir.client.animation.ImageAnimation;

/**
 * This class defines page container view.
 */
public class PageContainerView extends Composite {
	
	/**
	 * Horizontal move.
	 */
	public static final int HORIZONTAL_MOVE = 319;
	
	/**
	 * Vertical move.
	 */
	public static final int VERTICAL_MOVE = 200;

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

	@UiField
	FlowPanel movePanel;
	@UiField
	SimplePanel topLeftBlock, topCenterBlock, topRightBlock, centerLeftBlock,
	centerRightBlock, bottomLeftBlock, bottomCenterBlock, bottomRightBlock;
	
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
	 * Flag to lock move panel.
	 */
	private boolean isMovePanelLocked;
	
	/**
	 * Full screen state.
	 */
	private boolean fullScreen;
	
	/**
	 * Image clicked.
	 */
	private Image imageClicked; 
	
	/**
	 * Image animation timer.
	 */
	private Timer timer;
	
	/**
	 * Content panel after click on image.
	 */
	private FlowPanel contentAfterClick;
	
	/**
	 * Content panel after click image.
	 */
	private Image contentAfterClickImage;
	
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
		
		this.imagesAnimationOnEntry();
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
		
		this.isMovePanelLocked = false;
		this.fullScreen = false;

		this.contentAfterClickImage = new Image("image/42HD.png");
		this.minusButton = new SimplePanel();
		this.menuButton = new SimplePanel();
		
		this.contentAfterClick = new FlowPanel();
		this.contentAfterClick.add(this.contentAfterClickImage);
		this.contentAfterClick.add(this.minusButton);
		this.contentAfterClick.add(this.menuButton);
	}
	
	/**
	 * Initialization of widgets CSS.
	 */
	private void initWidgetsCss() {
		wallContainer.addStyleName("wallContainer");

		gridContainer.addStyleName("gridContainer");
		grid.addStyleName("grid");
		
		movePanel.addStyleName("initialMovePanel");
		
		this.timer = new Timer() {
			
			@Override
			public void run() {
				movePanel.removeStyleName("initialMovePanel");
				movePanel.addStyleName("movePanel");
			}
		};
		this.timer.schedule(2000);
		
		topLeftBlock.addStyleName("topLeftBlock");
		topCenterBlock.addStyleName("topCenterBlock");
		topRightBlock.addStyleName("topRightBlock");
		centerLeftBlock.addStyleName("centerLeftBlock");
		centerRightBlock.addStyleName("centerRightBlock");
		bottomLeftBlock.addStyleName("bottomLeftBlock");
		bottomCenterBlock.addStyleName("bottomCenterBlock");
		bottomRightBlock.addStyleName("bottomRightBlock");
		
		
		for(SimplePanel imageContainer : this.imageContainers) {
			imageContainer.addStyleName("imageContainer");
		}
		
		for(GridItem gridItem : this.gridItems) {			
			gridItem.getImage().addStyleName("image");
		}
		
		this.contentAfterClickImage.addStyleName("contentAfterClickImage");
		
		this.minusButton.addStyleName("initialMinus");
		this.menuButton.addStyleName("initialMenu");
	}
	
	/**
	 * Initialization of widgets handler.
	 */
	private void initWidgetsHandler() {
		
		/** Move images panel part **/
			
		final ClickHandler onMovePanelClickTopLeft = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!PageContainerView.this.isMovePanelLocked) {
					PageContainerView.this.isMovePanelLocked = true;
					
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), true, false, true, false);
					}				
				}
			}
		};
		topLeftBlock.sinkEvents(Event.ONCLICK);
		topLeftBlock.addHandler(onMovePanelClickTopLeft, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickTop = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!PageContainerView.this.isMovePanelLocked) {
					PageContainerView.this.isMovePanelLocked = true;
					
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), true, false, false, false);
					}
				}
			}
		};
		topCenterBlock.sinkEvents(Event.ONCLICK);
		topCenterBlock.addHandler(onMovePanelClickTop, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickTopRight = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), true, false, false, true);
					}
				}
			}
		};
		topRightBlock.sinkEvents(Event.ONCLICK);
		topRightBlock.addHandler(onMovePanelClickTopRight, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickLeft = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), false, false, true, false);
					}
				}
			}
		};
		centerLeftBlock.sinkEvents(Event.ONCLICK);
		centerLeftBlock.addHandler(onMovePanelClickLeft, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickRight = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), false, false, false, true);
					}
				}
			}
		};
		centerRightBlock.sinkEvents(Event.ONCLICK);
		centerRightBlock.addHandler(onMovePanelClickRight, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickBottomLeft = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), false, true, true, false);
					}
				}
			}
		};
		bottomLeftBlock.sinkEvents(Event.ONCLICK);
		bottomLeftBlock.addHandler(onMovePanelClickBottomLeft, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickBottom = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), false, true, false, false);
					}	
				}				
			}
		};
		bottomCenterBlock.sinkEvents(Event.ONCLICK);
		bottomCenterBlock.addHandler(onMovePanelClickBottom, ClickEvent.getType());
		
		final ClickHandler onMovePanelClickBottomRight = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {if(!PageContainerView.this.isMovePanelLocked) {
				PageContainerView.this.isMovePanelLocked = true;
				
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						PageContainerView.this.movePanelImage(gridItem.getImage(), false, true, false, true);
					}	
				}
			}
		};
		bottomRightBlock.sinkEvents(Event.ONCLICK);
		bottomRightBlock.addHandler(onMovePanelClickBottomRight, ClickEvent.getType());

		/** On click image part **/
		
		final ClickHandler onClickImage = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PageContainerView.this.imageClicked = (Image)event.getSource();
				
				PageContainerView.this.imagesAnimationOnClick();
			}
		};
		
		for(GridItem gridItem : this.gridItems) {
			gridItem.getImage().addClickHandler(onClickImage);
		}

	
		/** Handler image part **/
		
		final ClickHandler onMinusButtonClick = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PageContainerView.this.timer = new Timer() {
					
					@Override
					public void run() {
						PageContainerView.this.wallContainer.remove(PageContainerView.this.contentAfterClick);
						PageContainerView.this.wallContainer.add(PageContainerView.this.gridContainer);
						
						final Timer timer = new Timer() {
							
							@Override
							public void run() {
								PageContainerView.this.imagesAnimationOnClick();
							}
						};
						
						timer.schedule(100);
					}
				};
				
				PageContainerView.this.minusButton.removeStyleName("minus");
				PageContainerView.this.menuButton.removeStyleName("menu");
				
				PageContainerView.this.minusButton.addStyleName("initialMinus");
				PageContainerView.this.menuButton.addStyleName("initialMenu");
				
				PageContainerView.this.timer.schedule(500);
			}
		};
		this.minusButton.sinkEvents(Event.ONCLICK);
		this.minusButton.addHandler(onMinusButtonClick, ClickEvent.getType());
	}

	/**
	 * Images animation on entry.
	 */
	private void imagesAnimationOnEntry() {
	    for(GridItem gridItem: this.gridItems) {
	    	gridItem.getImage().getElement().getStyle().setPosition(Position.ABSOLUTE);
	    	gridItem.getImage().getElement().getStyle().setTop(-99, Unit.PX);
	    	gridItem.getImage().getElement().getStyle().setLeft(319, Unit.PX);
	    }
	    
	    final ImageAnimation animation1 = new ImageAnimation(image1, 319, -99, 0, -299);
	    final ImageAnimation animation2 = new ImageAnimation(image2, 319, -99, 319, -299);
	    final ImageAnimation animation3 = new ImageAnimation(image3, 319, -99, 637, -299);
	    final ImageAnimation animation4 = new ImageAnimation(image4, 319, -99, 0, -99);
	    final ImageAnimation animation6 = new ImageAnimation(image6, 319, -99, 637, -99);
	    final ImageAnimation animation7 = new ImageAnimation(image7, 319, -99, 0, 101);
	    final ImageAnimation animation8 = new ImageAnimation(image8, 319, -99, 319, 101);
	    final ImageAnimation animation9 = new ImageAnimation(image9, 319, -99, 637, 101);
	    
	    final Timer timer1 = new Timer() {
	        @Override
	        public void run() {
	            animation1.run(500);
	            animation2.run(500);
	            animation3.run(500);
	            animation4.run(500);
	            animation6.run(500);
	            animation7.run(500);
	            animation8.run(500);
	            animation9.run(500);
	        }
	    };
	    
	    timer1.schedule(200);
	}
	
	/**
	 * Move panel image.
	 * 
	 * @param image image to move.
	 * @param top state of top move.
	 * @param bottom state of bottom move.
	 * @param left state of left move.
	 * @param right state of right move.
	 */
	private void movePanelImage(Image image, boolean top, boolean bottom, boolean left, boolean right) {
		if(top) {
			image.getElement().getStyle().setTop(
					image.getElement().getOffsetTop() - VERTICAL_MOVE, 
					Unit.PX
			);			
		}
		
		if(bottom) {
			image.getElement().getStyle().setTop(
					image.getElement().getOffsetTop() + VERTICAL_MOVE, 
					Unit.PX
			);	
		}
		
		if(left) {
			image.getElement().getStyle().setLeft(
					image.getElement().getOffsetLeft() - HORIZONTAL_MOVE, 
					Unit.PX
			);
		}
		
		if(right) {
			image.getElement().getStyle().setLeft(
					image.getElement().getOffsetLeft() + HORIZONTAL_MOVE, 
					Unit.PX
			);
		}
		
		this.timer = new Timer() {
			
			@Override
			public void run() {
				PageContainerView.this.isMovePanelLocked = false;
			}
		};
		this.timer.schedule(1200);
	}
	
	/**
	 * Images animation on click.
	 */
	private void imagesAnimationOnClick() {	
		int row = 0;
		int column = 0;
		for(GridItem gridItem : this.gridItems) {
			if(gridItem.getImage().equals(this.imageClicked)) {
				row = gridItem.getRowNumber();
				column = gridItem.getColumnNumber();
			}
		}
		
		// Zoom.
		if(!this.fullScreen) {
			this.fullScreen = true;
			
			movePanel.removeStyleName("movePanel");
			movePanel.addStyleName("initialMovePanel");
			
			for(GridItem gridItem : this.gridItems) {
				gridItem.getImage().setVisible(false);
			}
			
			this.imageClicked.setVisible(true);
			this.imageClicked.removeStyleName("image");
			this.imageClicked.addStyleName("imageClicked");
			
			switch (row) {
			case 1:
				this.imageClicked.addStyleName("imageSecondRowClicked");
				break;
			case 2:
				this.imageClicked.addStyleName("imageThirdRowClicked");
				break;
			default:
				this.imageClicked.addStyleName("imageFirstRowClicked");
			}
			
			switch (column) {
			case 1:
				this.imageClicked.addStyleName("imageSecondColumnClicked");
				break;
			case 2:
				this.imageClicked.addStyleName("imageThirdColumnClicked");
				break;
			default:
				this.imageClicked.addStyleName("imageFirstColumnClicked");
			}
			
			this.timer = new Timer() {
				
				@Override
				public void run() {
					wallContainer.remove(movePanel);
					
					final String imageClickedHD = PageContainerView.this.imageClicked.getUrl().substring(
							0, 
							(PageContainerView.this.imageClicked.getUrl().length() - 4)
					);
					
					PageContainerView.this.contentAfterClickImage.setUrl(
							imageClickedHD + 
							"HD" + 
							PageContainerView.this.imageClicked.getUrl().substring( 
									(PageContainerView.this.imageClicked.getUrl().length() - 4)
							)
					);
					
					PageContainerView.this.wallContainer.remove(PageContainerView.this.gridContainer);
					PageContainerView.this.wallContainer.add(PageContainerView.this.contentAfterClick);
					
					final Timer timer = new Timer() {
						
						@Override
						public void run() {
							PageContainerView.this.minusButton.removeStyleName("initialMinus");
							PageContainerView.this.menuButton.removeStyleName("initialMenu");
							
							PageContainerView.this.minusButton.addStyleName("minus");
							PageContainerView.this.menuButton.addStyleName("menu");
						}
					};
					
					timer.schedule(5);
				}
			};
			
			this.timer.schedule(1000);
			
		} else {	
			this.fullScreen = false;
			
			this.imageClicked.addStyleName("image");
			this.imageClicked.removeStyleName("imageClicked");
			
			switch (row) {
			case 1:
				this.imageClicked.removeStyleName("imageSecondRowClicked");
				break;
			case 2:
				this.imageClicked.removeStyleName("imageThirdRowClicked");
				break;
			default:
				this.imageClicked.removeStyleName("imageFirstRowClicked");
			}
			
			switch (column) {
			case 1:
				this.imageClicked.removeStyleName("imageSecondColumnClicked");
				break;
			case 2:
				this.imageClicked.removeStyleName("imageThirdColumnClicked");
				break;
			default:
				this.imageClicked.removeStyleName("imageFirstColumnClicked");
			}
			
			this.timer = new Timer() {
				
				@Override
				public void run() {					
					for(GridItem gridItem : PageContainerView.this.gridItems) {
						gridItem.getImage().setVisible(true);
					}

					movePanel.removeStyleName("initialMovePanel");
					movePanel.addStyleName("movePanel");
				}
			};
			
			wallContainer.add(movePanel);
			movePanel.addStyleName("initialMovePanel");
			
			this.timer.schedule(1000);
		}
	}
	
}
