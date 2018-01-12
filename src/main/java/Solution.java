import com.google.gson.Gson;
import domain.Customer;
import domain.Location;
import exeptions.InvalidFileException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private final Gson gson = new Gson();

    /**
     * Given data in {fileName}, finds all customers within {distance} Km from the given {location}
     * @param location location to find all customers within
     * @param distance distance of customers within location
     * @param fileName name of file container customers data
     * @return List of customers withing {distance} km from {location}
     */
    public List<Customer> findCustomersWithingRange(Location location, int distance, String fileName) {
        return readFile(fileName).stream()
                .filter(k -> distance(location.getLat(),
                        k.getLatitude(),
                        location.getLgt(),
                        k.getLongitude()) <= distance)
                .collect(Collectors.toList());
    }

    /**
     * Read customer data from the give file name and populate them
     * into a list
     * @param fileName name of file
     * @return List of Customer
     */
    private List<Customer> readFile(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try {
            String data = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] content = data.split("\n");
            for(String x : content) {
                customers.add(parseCustomer(x));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidFileException("File contents not valid. should be valid json data separated by new lines");
        }
        return customers;
    }

    /**
     * Calculates distance between 2 locations on the earth surface. Uses Haversine formula
     * @param lat1 latitude of location 1
     * @param lat2 latitude of location 2
     * @param lgt1 longitude of location 1
     * @param lgt2 longitude of location 2
     * @return distance between both locations in KM.
     */
    public double distance(double lat1, double lat2, double lgt1, double lgt2) {
        final int R = 6371; // earth radius
        final int PRECISION =  2;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lgt2 - lgt1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        distance = Math.pow(distance, 2);
        return BigDecimal.valueOf(Math.sqrt(distance))
                .setScale(PRECISION, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * Convert customer json string into an instance of Customer object
     * @param customerJson customer json string
     * @return Customer
     */
    private Customer parseCustomer(String customerJson) {
        return gson.fromJson(customerJson, Customer.class);
    }
}
