package com.sfeir.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;

/**
 * This class defines image animation.
 */
public class ImageAnimation extends Animation {

	/**
	 * Image to animate.
	 */
    private Image image;
    
    /**
     * Initial position.
     */
    private double fromPositionX, fromPositionY;
    
    /**
     * Destination position.
     */
    private double toPositionX, toPositionY;
    
    /**
     * Create image animation object.
     * 
     * @param image image to animate.
     * @param fromPositionX initial X position.
     * @param fromPositionY initial Y position.
     * @param toPositionX destination X position.
     * @param toPositionY destination Y position.
     */
    public ImageAnimation(
    		Image image, 
    		double fromPositionX, 
    		double fromPositionY, 
    		double toPositionX, 
    		double toPositionY
    ){
		this.image = image;
		this.fromPositionX = fromPositionX;
		this.fromPositionY = fromPositionY;
		this.toPositionX = toPositionX;
		this.toPositionY = toPositionY;
    }
    
    @Override
    protected void onUpdate(double progress) {
		double positionX = fromPositionX + (progress * (toPositionX - fromPositionX));
		double positionY = fromPositionY + (progress * (toPositionY - fromPositionY));
		
		this.image.getElement().getStyle().setLeft(positionX, Unit.PX);
		this.image.getElement().getStyle().setTop(positionY, Unit.PX);
    }
    
    @Override
    protected void onComplete() {
        super.onComplete();
        this.image.getElement().getStyle().setLeft(this.toPositionX, Unit.PX);
        this.image.getElement().getStyle().setTop(this.toPositionY, Unit.PX);
    }

}
