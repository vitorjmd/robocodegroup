package codigodecombate;

import robocode.*;
import java.awt.Color;

public class ImbatívelRobot extends Robot {
    private double enemyHeading; // Direção do inimigo
    private double enemyDistance; // Distância do inimigo

    public void run() {
        // Definindo as cores do robô
        setColors(Color.red, Color.blue, Color.green); // corpo, canhão, radar

        while (true) {
            // Movimentação evasiva
            if (enemyDistance < 100) {
                // Se o inimigo estiver perto, mova-se para os lados
                turnRight(90);
                ahead(100);
            } else {
                // Caso contrário, mova-se para frente
                ahead(100);
            }
            // Gire o radar para escanear o campo de batalha
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        enemyHeading = e.getHeading();
        enemyDistance = e.getDistance();

        // Prever onde o inimigo estará e disparar
        double bulletPower = Math.min(3.0, getEnergy()); // Poder do tiro baseado na energia
        double fireAngle = e.getBearing() + (enemyHeading - getHeading());
        fire(bulletPower);

        // Ajustar a posição do robô para evitar ser atingido
        if (enemyDistance < 150) {
            turnRight(90 - e.getBearing()); // Mova-se para longe do inimigo
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // Mova-se para longe do impacto
        back(50);
        turnRight(90); // Gire para mudar a direção
    }

    public void onHitWall(HitWallEvent e) {
        // Mova-se para longe da parede
        back(50);
        turnRight(90); // Gire para mudar a direção
    }
}
