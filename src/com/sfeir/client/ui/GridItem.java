package com.sfeir.client.ui;

import com.google.gwt.user.client.ui.Image;

/**
 * This class defines grid item object.
 */
public class GridItem {

	/**
	 * Grid item image.
	 */
	private final Image image;
	
	/**
	 * Grid item row number.
	 */
	private final int rowNumber;
	
	/**
	 * Grid item column number.
	 */
	private final int columnNumber;
	
	/**
	 * Create grid item object.
	 * 
	 * @param rowNumber grid item row number.
	 * @param columnNumber grid item column number.
	 */
	public GridItem(Image image, int rowNumber, int columnNumber) {
		super();
		this.image = image;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}
	
	/**
	 * Gets grid item image.
	 * 
	 * @return image.
	 */
	public Image getImage() {
		return this.image;
	}
	
	/**
	 * Gets grid item rowNumber.
	 * 
	 * @return row number.
	 */
	public int getRowNumber() {
		return this.rowNumber;
	}
	
	/**
	 * Gets grid item column number.
	 * 
	 * @return column number.
	 */
	public int getColumnNumber() {
		return this.columnNumber;
	}
}
