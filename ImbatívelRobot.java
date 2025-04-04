package codigodecombate;

import robocode.*;
import java.awt.Color;

public class ImbatívelRobot extends Robot {
    private double enemyHeading; /
    private double enemyDistance; 

    public void run() {
       
        setColors(Color.red, Color.blue, Color.green);

        while (true) {
        
            if (enemyDistance < 100) {
                
                turnRight(90);
                ahead(100);
            } else {
              
                ahead(100);
            }
         
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        enemyHeading = e.getHeading();
        enemyDistance = e.getDistance();

      
        double bulletPower = Math.min(3.0, getEnergy()); 
        double fireAngle = e.getBearing() + (enemyHeading - getHeading());
        fire(bulletPower);

 
        if (enemyDistance < 150) {
            turnRight(90 - e.getBearing()); 
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        
        back(50);
        turnRight(90); 
    }

    public void onHitWall(HitWallEvent e) {
        
        back(50);
        turnRight(90); 
    }
}
