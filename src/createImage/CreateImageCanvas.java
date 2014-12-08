/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createImage;

import applicationLogic.Camera;
import applicationLogic.Color;
import applicationLogic.Hit;
import applicationLogic.Ray;
import applicationLogic.World;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author Dominik
 */
public class CreateImageCanvas extends Canvas{
    
    public final static BufferedImage image = new BufferedImage(CreateImageMain.frame.getWidth(), CreateImageMain.frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
    
    private final World world;
    private Camera camera;
    
    public CreateImageCanvas(final World world, final Camera camera){
        this.world = world;
        this.camera = camera;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        WritableRaster raster = image.getRaster();
        ColorModel cm = image.getColorModel();
        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Ray ray = camera.rayFor(image.getWidth(), image.getHeight(), i, j);
                Hit hit = world.hit(ray);
                if(hit == null){
                    float[] color = {(float)world.backgroundColor.r, (float)world.backgroundColor.g, (float)world.backgroundColor.b, 1.0f};
                    raster.setDataElements(j, i, cm.getDataElements(color, 0, null));
                }else{
                    float[] color = {(float)hit.geo.color.r, (float) hit.geo.color.g, (float)hit.geo.color.b, 1.0f};
                    raster.setDataElements(j, i, cm.getDataElements(color, 0, null));
                }
            }
        }
        g.drawImage(image, 0, 0, null);
    }
    
    public void setCamera(final Camera camera){
        this.camera = camera;
    }
    
}
