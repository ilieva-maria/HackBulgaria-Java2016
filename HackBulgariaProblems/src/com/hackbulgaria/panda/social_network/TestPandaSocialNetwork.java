package com.hackbulgaria.panda.social_network;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class TestPandaSocialNetwork {
	private PandaSocialNetwork pandaNetwork;
	 @Before
	    public void setUp() {
	        pandaNetwork = new PandaSocialNetwork();
	    }
	@Test
	public void testAddPanda() {
		
		Panda panda = new Panda("Ivo", "ivo@pandamail.com", "male");
		pandaNetwork.addPanda(panda);
		assertTrue(pandaNetwork.hasPanda(panda));
	}
	
	public void testMakeFriends() {
		Panda panda = new Panda("Ivo", "ivo@pandamail.com", "male");
		Panda rado = new Panda("Rado", "rado@pandamail.com", "male");
		Panda tony = new Panda("Tony", "tony@pandamail.com", "female");
		assertFalse(pandaNetwork.areFriends(panda, tony));
		pandaNetwork.addPanda(panda);
		pandaNetwork.makeFriends(rado, tony);
		assertTrue(pandaNetwork.hasPanda(rado));
		assertTrue(pandaNetwork.areFriends(tony, rado));
		assertFalse(pandaNetwork.areFriends(rado, panda));
		
	}
	
	
	
}
