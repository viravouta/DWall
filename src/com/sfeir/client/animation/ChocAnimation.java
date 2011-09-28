package com.sfeir.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.SimplePanel;

public class ChocAnimation extends Animation {

    
    private SimplePanel target;
    
    private double fromPositionX, fromPositionY, toPositionX, toPositionY;
    
    
    public ChocAnimation(SimplePanel target_, double fromPositionX_, double fromPositionY_, double toPositionX_, double toPositionY_){
	this.target = target_;
	this.fromPositionX = fromPositionX_;
	this.fromPositionY = fromPositionY_;
	this.toPositionX = toPositionX_;
	this.toPositionY = toPositionY_;
    }
    
    @Override
    protected void onUpdate(double progress) {
	
	double positionX = fromPositionX + (progress * (toPositionX - fromPositionX));
	double positionY = fromPositionY + (progress * (toPositionY - fromPositionY));
	
	this.target.getElement().getStyle().setLeft(positionX, Unit.PX);
	this.target.getElement().getStyle().setTop(positionY, Unit.PX);
    }
    
    @Override
    protected void onComplete()
    {
        super.onComplete();
        this.target.getElement().getStyle().setLeft(this.toPositionX, Unit.PX);
        this.target.getElement().getStyle().setTop(this.toPositionY, Unit.PX);
        
    }

}
