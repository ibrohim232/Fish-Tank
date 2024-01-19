package org.example;

import org.example.entity.Fish;
import org.example.entity.FishTank;
import org.example.entity.Location;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        FishTank fishTank = new FishTank();
        fishTank.setCapacity(30);
        fishTank.setX(10);
        fishTank.setY(10);
        fishTank.setZ(10);
        fishTank.setFishList(new CopyOnWriteArrayList<>());
        Random random = new Random();
        int male = random.nextInt(6, 12);
        int female = random.nextInt(6, 12);
        System.out.println("There were " + male + " male fishes and " + female + " female fishes.");
        for (int i = 0; i < male; i++) {
            fishTank.getFishList().add(
                    new Fish(UUID.randomUUID(), true, random.nextInt(2, 20)
                            , new Location(random.nextInt(0, fishTank.getX()), random.nextInt(0, fishTank.getY()), random.nextInt(0, fishTank.getZ())),
                            fishTank)
            );
        }
        for (int i = 0; i < female; i++) {
            fishTank.getFishList().add(
                    new Fish(UUID.randomUUID(), false, random.nextInt(2, 20)
                            , new Location(random.nextInt(0, fishTank.getX()), random.nextInt(0, fishTank.getY()), random.nextInt(0, fishTank.getZ())),
                            fishTank)
            );
        }
        fishTank.startFish();
    }
}