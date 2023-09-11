package cmsc256;

import bridges.base.*;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.EarthquakeUSGS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GraphEarthquakeData {

    public static double calcDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        final int radius = 6371; // Radius of the earth in km

        // Haversine formula to calculate a value between 0 and 1 between 2 points on a sphere,
        //  1 being the opposite side of the sphere
        double laDistance = Math.toRadians(latitude2 - latitude1);
        double loDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(laDistance / 2) * Math.sin(laDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(loDistance / 2) * Math.sin(loDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = radius * c;    //convert to km
        return distance;
    }


    public static void main(String[] args) throws Exception {
        // Create a Bridges object
        Bridges bridges = new Bridges(6, "cesarcolato", "41388393710");
        // Get a DataSource object from Bridges
        DataSource ds = bridges.getDataSource();

        // Set an assignment title
        bridges.setTitle("Earthquake Data Graph");

        // Initialize a Graph
        GraphAdjListSimple<String> graph = new GraphAdjListSimple<>();


        /*
         * Grab Earthquake data and store it in a List
         * Sort the list by magnitude
         * Retain only 100 earthquakes of highest magnitude
         */
        List<EarthquakeUSGS> data = ds.getEarthquakeUSGSData(5000);
        List<EarthquakeUSGS> magList = new ArrayList<>();

        data.sort(new Comparator<EarthquakeUSGS>() {
            @Override
            public int compare(EarthquakeUSGS o1, EarthquakeUSGS o2) {
                Double mag1 = o1.getMagnitude();
                Double mag2 = o2.getMagnitude();
                return mag1.compareTo(mag2);
            }
        });
        for (int i = 4900; i < data.size(); i++) {
            magList.add(data.get(i));
        }


        /*
         * Add the Earthquakes to the graph
         * Set each earthquake's location based on its latitude and longitude
         * ex: graph.getVisualizer(key).setLocation(earthquake.getLongit(), earthquake.getLatit());
         * Tweak the colors or other visual elements if you wish; For example, if the magnitude is higher than 6, set the color to red
         */

		for(int i = 0; i < 100; i++){
			graph.addVertex(magList.get(i).getTitle(), String.valueOf(magList.get(i).getMagnitude()));
			graph.getVisualizer(magList.get(i).getTitle()).setLocation(magList.get(i).getLongit(), magList.get(i).getLatit());
            graph.getVertex(magList.get(i).getTitle()).setLabel(magList.get(i).getLocation());

            if(magList.get(i).getMagnitude() > 6){
                graph.getVertex(magList.get(i).getTitle()).setColor("Red");
            }
		}

        bridges.setCoordSystemType("equirectangular");
        bridges.setDataStructure(graph);
        bridges.setMapOverlay(true);
        bridges.setTitle("Earthquake Map");
        bridges.visualize();


        /*
         * Compare the distances between all vertexes in the graph, drawing an edge
         * if they are within 500km. A method is provided to give a rough
         * estimate between 2 lat,long points.
         *
         * example usage: calcDistance(eq1.getLatit(), eq1.getLongit(),
         *                eq2.getLatit(), eq2.getLongit());
         * which returns a double representing the distance of two points in km
         */

        for (int i = 0; i < magList.size(); i++) {
            for (int j = 1; j < magList.size(); j++) {
                if(calcDistance(magList.get(i).getLatit(), magList.get(i).getLongit(), magList.get(j).getLatit(), magList.get(j).getLongit()) <= 500 && !magList.get(i).equals(magList.get(j))){
                    graph.addEdge(magList.get(i).getTitle(), magList.get(j).getTitle());
                }
            }
        }


        bridges.visualize();

        /*
         * Reset the locations of the vertices by setting their location to
         * Double.POSITIVE_INFINITY
         *
         * ex: graph.getVisualizer(key).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
         */

		for(int i = 0; i < magList.size(); i++){
			graph.getVisualizer(magList.get(i).getTitle()).setLocation(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		}

        bridges.setMapOverlay(false);
        bridges.visualize();
    }
}
