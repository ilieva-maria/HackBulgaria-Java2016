package com.hackbulgaria.zombies;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int zombiesCount = input.nextInt();
		int zombiesHealth = input.nextInt();

		int counter = 0;

		Map<String, Weapon> weapons = new HashMap<>();
		weapons.put("axe", new Axe());
		weapons.put("shotgun", new Shotgun());
		weapons.put("sword", new Sword());
		weapons.put("revolver", new Revolver());

		Weapon weapon = weapons.get(input.next().toLowerCase());

		for (int i = 0; i < zombiesCount; i++) {
			int oneZombieHealth = zombiesHealth;
			while (oneZombieHealth > 0) {
				oneZombieHealth -= weapon.hit();
				counter++;
			}
		}

		System.out.println(counter);
		input.close();
	}

}
