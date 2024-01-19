package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Synchronized;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FishTank {
    private CopyOnWriteArrayList<Fish> fishList;
    private int capacity;
    private int x;
    private int y;
    private int z;

    public void startFish(){
        for (Fish fish : fishList) {
            fish.start();
        }
    }
    @Synchronized
    public Fish findPair(Fish firstFish) {
        Optional<Fish> secondFish = fishList.stream().filter(fish ->
                        (fish.getGender() != firstFish.getGender() && fish.getLocation().equals(firstFish.getLocation()))).
                findFirst();
        return secondFish.orElse(null);
    }

}
