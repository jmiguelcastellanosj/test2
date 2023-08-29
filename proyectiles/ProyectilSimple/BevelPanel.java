
/*
 * BevelPanel.java  1.0  97/03/05  Jean-Guy Speton (speton@cs.orst.edu)
 *
 * Copyright (c) 1997 Jean-Guy Speton.  All rights reserved.
 *
 * Permission for NON-COMMERCIAL use is hereby granted.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * Drop-in java.awt.Panel replacement with a raised or lowered beveled
 * border (3D look) with an optional label and adjustable internal
 * padding.  Use to group related graphical components.
 * @version 1.0, 05 Mar 97
 * @author Jean-Guy Speton
 */
public class BevelPanel extends java.awt.Panel
{
  /** No bevel. */
  public static final int BEVEL_NONE    = 0;
  /** Lowered bevel. */
  public static final int BEVEL_LOWERED = 1;
  /** Raised bevel. */
  public static final int BEVEL_RAISED  = 2;

  private String label;
  private int bevel;

  /**
   * The number of pixels inside and outside of the bevel used as
   * whitespace (for aesthetics).
   */
  private int padding = 4;

  private int topInset;
  private int leftInset;
  private int bottomInset;
  private int rightInset;

  /**
   * Create a BevelPanel with no label and no bevel.
   */
  public BevelPanel()
  {
    this(null, BEVEL_NONE);
  }

  /**
   * Create a BevelPanel with a label and (default) lowered bevel.
   */
  public BevelPanel(String label)
  {
    this(label, BEVEL_LOWERED);
  }

  /**
   * Create a BevelPanel with no label and the specified bevel.
   */
  public BevelPanel(int bevel)
  {
    this(null, bevel);
  }

  /**
   * Create a BevelPanel with specified label and bevel.
   */
  public BevelPanel(String label, int bevel)
  {
    this.label = label;
    this.bevel = bevel;
  }

  /**
   * Calculate padding amounts as soon as the FontMetrics are available.
   */
  public void addNotify()
  {
    super.addNotify();
    calcPadding();
  }

  private void calcPadding()
  {
    if (bevel == BEVEL_NONE) {
      topInset = leftInset = bottomInset = rightInset = padding;
    }
    else {
      if (label != null) {
	topInset = getFontMetrics(getFont()).getHeight() + 2*padding + 2;
      }
      else {
	topInset = 2*padding + 2;
      }
      leftInset = bottomInset = rightInset = 2*padding + 2;
    }
  }

  /**
   * Return insets sufficient for bevel and label drawing space.
   */
  public Insets getInsets()
  {
    return new Insets(topInset, leftInset, bottomInset, rightInset);
  }

  /**
   * Return the current label, or null if none.
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Return the current padding, in pixels.
   */
  public int getPadding()
  {
    return padding;
  }

  /**
   * Draw bevel and label.
   */
  public void paint(Graphics g)
  {
    super.paint(g);

    Dimension d = getSize();
    FontMetrics fm = getFontMetrics(getFont());
    Color light = getBackground().brighter();
    Color dark  = getBackground().darker();

    // Bevel border coordinates.
    int bevelLeft = padding;
    int bevelTop = (label == null ? padding : fm.getHeight() / 2) + padding;
    int bevelWidth = d.width-1-2*padding;
    int bevelHeight = d.height-1-padding-bevelTop;

    if (bevel != BEVEL_NONE) {
      g.setColor(bevel == BEVEL_LOWERED ? light : dark);
      g.drawRect(bevelLeft, bevelTop, bevelWidth, bevelHeight);
      g.drawRect(bevelLeft+1, bevelTop+1, bevelWidth-1, bevelHeight-1);
      g.setColor(bevel == BEVEL_LOWERED ? dark : light);
      g.drawRect(bevelLeft, bevelTop, bevelWidth-1, bevelHeight-1);
    }

    // The label will be drawn 8 pixels to the right of the top
    // bevel lines with 4 pixels of space to the left and right.
    if (label != null) {
      g.setColor(getBackground());
      g.fillRect(bevelLeft + 8, padding,
		 fm.stringWidth(label) + 8, fm.getHeight());
      g.setColor(getForeground());
      g.drawString(label,
		   bevelLeft + 12, fm.getHeight() - fm.getDescent() + padding);
    }
  }

  /**
   * Set the font to use for the panel and label.
   */
  public void setFont(Font f)
  {
    super.setFont(f);
    calcPadding();
  }

  /**
   * Set the pabel label.
   */
  public void setLabel(String s)
  {
    label = s;
    calcPadding();
  }

  /**
   * Set the number of empty pixels inside and outside of the bevel.
   */
  public void setPadding(int n)
  {
    padding = n;
    calcPadding();
  }
}
