package com.raven.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
    
import javax.swing.JTextField;

public class TextFieldAnimation extends JTextField {

    public Color getAnimationColor() {
        return animationColor;
    }

    public String getHintText() {
        return hintText;
    }

    public void setAnimationColor(Color animationColor) {
        this.animationColor = animationColor;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }
    
    private Color backgroundColor = Color.WHITE;
    private Color animationColor = new Color(3, 175, 255);
    private final Icon iconSearch;
    private final Icon iconClose;
    private final Icon iconLoading;
    private String hintText="Rechercher ...";
    private Timer timer;
    private boolean show;
    private float speed = 1f;
    private float location = -1;
    
    public TextFieldAnimation() {
        super.setBackground(new Color(255, 255, 255, 0)); //  Remove background
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 50)); //  Set Right border 50
        setFont(new java.awt.Font("sansserif", 0, 14));
        setSelectionColor(new Color(80, 199, 255));
        iconSearch = new ImageIcon(getClass().getResource("/com/raven/icon/search_1.png"));
        iconClose = new ImageIcon(getClass().getResource("/com/raven/icon/close.png"));
        iconLoading = new ImageIcon(getClass().getResource("/com/raven/icon/loading.gif"));
        
        // Create and check if mouse over button
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (checkMouseOver(me.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.TEXT_CURSOR));
                }
            }
        });
        
        // Create mouse click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (checkMouseOver(me.getPoint())) {
                        if(!timer.isRunning()){
                            if (show) {
                                setEditable(true);
                                show=false;
                                location= -1;
                                timer.start();
                            } else {
                                setEditable(false);
                                show=true;
                                location=getWidth();
                                timer.start();
                            }
                        }
                    }
                } 
            }
            
        });
        
        timer=new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent me) {
                if (show) {
                    if (location>0){
                        location -= speed;
                        repaint();
                    } else {
                        timer.stop();
                    }
                } else {
                    if (location<getWidth()){
                        location += speed;
                        repaint();
                    } else {
                        timer.stop();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);    //  For smooth line
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //  For smooth image
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(grphcs);
        // Create Button
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255), width, 0, animationColor);
        g2.setPaint(gra);
        g2.fillOval(width - height + 3, marginButton, buttonSize, buttonSize);
        
        // Create Animation when click button
        if (location != -1) {
            g2.fillRoundRect((int) location, 0, (int) (width - location), height, height, height);
            // Create Loading icon
            int iconSize=iconLoading.getIconHeight();
            // Create Alpha
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
            g2.drawImage(((ImageIcon) iconLoading).getImage(), (int) (location + 5), (height - iconSize)/2, this);
        }
        
        // Create Button Icon
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        int marginImage = 5;
        int imageSize = buttonSize - marginImage * 2;
        Image image;
        if(show){
            image = ((ImageIcon) iconClose).getImage();
        } else {
            image = ((ImageIcon) iconSearch).getImage();
        }
        g2.drawImage(image, width - height + marginImage, marginButton + marginImage, imageSize, imageSize, null);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (show == false && getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(hintText, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private float getAlpha() {
        float width = getWidth() / 2;
        float alpha = (location) / (-width);
        alpha += 1;
        if(alpha < 0){
            alpha = 0;
        }
        if (alpha > 1){
            alpha = 1;
        }
        return alpha;
    }
    
    private boolean checkMouseOver(Point mouse) {
        int width = getWidth();
        int height = getHeight();
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        Point point = new Point(width - height + 3, marginButton);
        Ellipse2D.Double circle = new Ellipse2D.Double(point.x, point.y, buttonSize, buttonSize);
        return circle.contains(mouse);
    }

    @Override
    public void setBackground(Color color) {
        this.backgroundColor= color;
    }
    
    
}