/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationLogic.geometry;

import applicationLogic.Hit;
import applicationLogic.Ray;
import applicationLogic.material.Material;
import matrizen.Normal3;
import matrizen.Point3;

/**
 *
 * @author Dominik
 */
public class Plane extends Geometry{
    
    /**
     * Ein Punkt auf der Ebene.
     */
    public final Point3 a;
    /**
     * Normalenvektor der Ebene.
     */
    public final Normal3 n;
    
    /**
     * Erzeugt eine neue Ebene.
     * @param a Ein Punkt auf der Ebene.
     * @param n Der Normalenvektor der Ebene.
     * @param material Material der Ebene.
     */
    public Plane(final Point3 a, final Normal3 n, final Material material){
        super(material);
        this.a = a;
        this.n = n;
    }
    
    /**
     * Prüft, ob der übergebene Strahl diese Ebene schneidet.
     * @param ray Strahl, für den überprüft werden soll, ob er die Ebene schneidet.
     * @return Ein Hitobjekt, welches Daten für den Schnittpunkt enthält.
     */
    @Override
    public Hit hit(Ray ray) {
        final double zaehler = this.a.sub(ray.o).dot(this.n);
        final double nenner = ray.d.dot(this.n);
        if(nenner != 0){
            final double resultT = zaehler / nenner;
            if(resultT >= 0){
                final Hit resultHit = new Hit(resultT, ray, this);
                return resultHit;
            }else{
                return null;
            }
        }else{
            return null;
        }
    } 
    
}