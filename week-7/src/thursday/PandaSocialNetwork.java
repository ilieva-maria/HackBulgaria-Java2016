package thursday;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class PandaSocialNetwork {
	Graph<Panda> pandaNetwork = new Graph<>();
	
	public boolean hasPanda(Panda panda) {
		return pandaNetwork.containsVertex(panda);
	}
	
	public void addPanda(Panda panda){
		if (!hasPanda(panda)){
			pandaNetwork.addVertex(panda);
		}
		
	}
	
	public void makeFriends(Panda panda1, Panda panda2) {
		addPanda(panda1);
		addPanda(panda2);
		pandaNetwork.addEdge(panda1, panda2);
		pandaNetwork.addEdge(panda2, panda1);
		
	}
	
	public boolean areFriends(Panda panda1, Panda panda2) {
		return pandaNetwork.containsEdge(panda1, panda2);
	}
	
	public List<Panda> friendsOf(Panda panda) {
		List<Panda> friends = new ArrayList<>();
		if (!hasPanda(panda)) {
			throw new RuntimeException("The Panda is not in the network! " + panda.toString());
		} 
		pandaNetwork.getNode(panda).edges().forEach(edge -> {
            Panda friend = edge.toNode().vertex();
            friends.add(friend);
            });
		return friends;
	}
	
	public int connectionLevel(Panda panda1, Panda panda2) {
		if (!hasPanda(panda1) || ! hasPanda(panda2)){
			throw new RuntimeException("The pandas must in the network! " + panda1.toString() + " " + panda2.toString());
		}
		List<Panda> path = pandaNetwork.shortestPath(panda1, panda2);
		if (path.isEmpty()){
			return -1;
		}
		return path.size();
	}
	
	public boolean areConnected(Panda panda1, Panda panda2) {
		List<Panda> path = pandaNetwork.shortestPath(panda1, panda2);
		return !path.isEmpty();
	}
	
	public int howManyGenderInNetwork(int level, Panda panda, String gender) {
		int pandasCount = 0;
		List<Panda> friends = friendsOf(panda);
		for (Panda friend : friends) {
			if (connectionLevel(panda, friend) == level){
				if (friend.getGender() == gender) {
					pandasCount++;
				}
			}
		}
		return pandasCount;
	}
	
}
