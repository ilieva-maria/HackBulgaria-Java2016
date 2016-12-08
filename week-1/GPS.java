import java.util.Vector;
import java.util.Scanner;
public class GPS {

  public static Vector<Integer> getGasStations(int tripDistance, int tankSize, Vector<Integer> gasStations) {
    Vector<Integer> visitedGasStations = new Vector<Integer>();
    int currentDistance = 0, currentStation = 0;
    while (tripDistance > currentDistance + tankSize){
      while (currentStation < gasStations.size() && gasStations.get(currentStation) - currentDistance <= tankSize){
        currentStation++;
      }
      currentStation--;
      int visitedStation = gasStations.get(currentStation);
      visitedGasStations.add(visitedStation);
      currentDistance = visitedStation;
    }
    return visitedGasStations;
    // int currentDistance = 0, currentStation = 0;
    // while (tripDistance > currentDistance + tankSize){
    //   if (gasStations.get(currentStation) - currentDistance <= tankSize)
    // }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tripDistance = scanner.nextInt();
    int tankSize = scanner.nextInt();

    int gasStationsCount = scanner.nextInt();
    Vector<Integer> gasStations = new Vector<Integer>();

    for (int i = 0; i < gasStationsCount; i++) {
      gasStations.add(scanner.nextInt());
    }

    Vector<Integer> result = getGasStations(tripDistance, tankSize, gasStations);
    System.out.println();
    for (int i = 0; i < result.size(); i++) {
        System.out.println(result.get(i));
    }

  }
}