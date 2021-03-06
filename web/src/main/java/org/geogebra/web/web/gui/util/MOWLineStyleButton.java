package org.geogebra.web.web.gui.util;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.gui.util.SelectionTable;
import org.geogebra.common.main.Feature;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.web.euclidian.EuclidianLineStylePopup;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Line style button with preview
 * 
 * @author Laszlo
 */
public class MOWLineStyleButton extends EuclidianLineStylePopup {
	/** Size of the value canvas */
	private static final int CANVAS_WIDTH = 30;
	private static final int CANVAS_HEIGHT = 20;
	private LineStylePreview preview;
	/**
	 * Constructor
	 * 
	 * @param app
	 *            GGB app.
	 */
	public MOWLineStyleButton(AppW app) {
		super(app, -1, 5, SelectionTable.MODE_ICON, true, true);


		// Rearranging content.
		VerticalPanel panel = ((ButtonPopupMenu) getMyPopup()).getPanel();
		panel.clear();
		panel.add(getMyTable());
		panel.add(sliderPanel);

		preview = new LineStylePreview(app, CANVAS_WIDTH, CANVAS_HEIGHT);
		sliderPanel.add(preview);
		updatePreview();
		if (!app.has(Feature.MOW_COLOR_FILLING_LINE)) {
			panel.addStyleName("mowStylePopup");
			sliderPanel.addStyleName("mowLinePopup");
		}
		preview.addStyleName("preview");
		if (app.has(Feature.MOW_COLOR_FILLING_LINE)) {
			Label titleLabel = new Label(
					app.getLocalization().getMenu("Thickness"));
			titleLabel.addStyleName("thicknessLabel");
			sliderPanel.insert(titleLabel, 0);
			getMySlider().setWidth("140px");

		}
		setKeepVisible(true);
	}

	@Override
	public void update(Object[] geos) {
		updatePanel(geos);
		updatePreview();
	}

	@Override
	public void handlePopupActionEvent() {
		model.applyLineTypeFromIndex(getSelectedIndex());
		updatePreview();
	}

	@Override
	protected void onClickAction() {
		super.onClickAction();
		updatePreview();
	}

	@Override
	public void onSliderInput() {
		super.onSliderInput();
		updatePreview();
	}

	/**
	 * No text (but canvas) for slider so leave this empty.
	 */
	@Override
	protected void setSliderText(String text) {
		// intentionally left blank
	}

	private void updatePreview() {
		preview.update(getSliderValue(), getSelectedIndex(), GColor.BLACK);
	}

}
