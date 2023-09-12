# Earthquake Data Visualization

## Description
This Java program fetches earthquake data from the USGS (United States Geological Survey) and visualizes it on a map using the BRIDGES API. It also constructs a graph representing earthquakes as vertices and draws edges between them if they are within a certain distance of each other.

The program performs the following tasks:
1. Retrieves earthquake data from the USGS.
2. Sorts the earthquake data by magnitude and retains the top 100 earthquakes with the highest magnitude.
3. Constructs a graph with earthquakes as vertices.
4. Sets the location of each earthquake on the map based on latitude and longitude.
5. Adjusts the visual properties, such as color, for earthquakes with a magnitude greater than 6.
6. Visualizes the earthquake data on a map, including graph edges representing proximity.

## Requirements
To run this program, you need the following:

- Java Development Kit (JDK) installed on your computer.
- The BRIDGES API library, which can be obtained from [BRIDGES website](https://bridgesuncc.github.io/doc/java-api/current/bridges_base.html).

## Usage
Follow these steps to use the program:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/your-username/earthquake-data-visualization.git

2. Open the project in your preferred Java development environment (e.g., IntelliJ IDEA, Eclipse).

3. Ensure you have added the BRIDGES API library to your project's build path.

4. Open the GraphEarthquakeData.java file and review the code to understand how the program works.

5. Modify the configuration in the main method as needed. You can adjust the number of earthquakes fetched and the distance threshold for drawing edges.

6. Run the main method in the GraphEarthquakeData class. This will fetch earthquake data, create a graph, and visualize it.

7. The BRIDGES visualization window will open, showing earthquake data on a map.

8. Customize the code or add additional features as needed for your project.

9. Make sure to replace your-username in the clone URL with your actual GitHub username.

10. Enjoy visualizing earthquake data on a map with this program!

```
Please replace `your-username` in the clone URL with your actual GitHub username and adjust any other details as needed to match your project structure. This README provides a basic overview of the project, its requirements, and how to run it. You can expand it further with additional sections or details if necessary.
```

## Credits
This project was created by Cesar Colato
