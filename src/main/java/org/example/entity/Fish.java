package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fish extends Thread {
    private UUID fishId;
    private Boolean gender;
    private int lifeSpan;
    private Location location;
    private FishTank fishTank;

    @Override
    public void run() {
        int timeElapsed = 0;
        Random random = new Random();
        while (timeElapsed < lifeSpan) {
                int newX = location.getX() + random.nextInt(3) - 1;
                int newY = location.getY() + random.nextInt(3) - 1;
                int newZ = location.getZ() + random.nextInt(3) - 1;
                location = checkForFishTankBounds(newX, newY, newZ);


            Fish pair = fishTank.findPair(this);
            if (pair != null) {
                if (fishTank.getCapacity() <= fishTank.getFishList().size()-1) {
                    System.out.println("Fish Tank is full");

                    System.exit(0);
                    break;
                }
                Fish fish = new Fish(UUID.randomUUID(), random.nextBoolean(), random.nextInt(2, 20)
                        , this.getLocation(),
                        this.getFishTank());
                System.out.println("Male " + this.getFishId() + " fish and Female " + pair.getFishId() + " fish met themselves ");
                System.out.println("Overall fishes = " + fishTank.getFishList().size());
                fishTank.getFishList().add(fish);
                fish.start();
            }


            timeElapsed++;
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String diedFish;
        if (gender) {
            diedFish = "Male ";
        } else {
            diedFish = "Female ";
        }
        System.out.println(diedFish + "is died 💤💤💤");
        fishTank.getFishList().remove(this);
        System.out.println("Overall fishes = " + fishTank.getFishList().size());
        if (fishTank.getFishList().isEmpty()) {
            System.out.println("Life is finished in Fish Tank because 0 fish left.");
            System.exit(1);
        }
    }

    private Location checkForFishTankBounds(int newX, int newY, int newZ) {
        newX = Math.max(0, Math.min(newX, fishTank.getX()) - 1);
        newY = Math.max(0, Math.min(newY, fishTank.getY()) - 1);
        newZ = Math.max(0, Math.min(newZ, fishTank.getZ()) - 1);
        return new Location(newX, newY, newZ);
    }
}
