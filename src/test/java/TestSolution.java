import com.google.gson.Gson;
import domain.Customer;
import domain.Location;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestSolution {
    Solution solution = new Solution();

    @Test
    public void testDistance() {

        // given
        double lat1 = 53.3381985;
        double lgt1 = -6.2592576;

        double lat2 = 52.986375;
        double lgt2 = -6.043701;

        // then
        assertEquals(41.68, solution.distance(lat1, lat2, lgt1, lgt2));
    }

    @Test
    public void testFindCustomersWithingRange() {
        Location location = new Location(53.3381985, -6.2592576);
        int distance = 100;
        String fileName = "customer.txt";
        String expected = "[{\"user_id\":12,\"name\":\"Christina McArdle\",\"latitude\":52.986375,\"longitude\":-6.043701},{\"user_id\":8,\"name\":\"Eoin Ahearn\",\"latitude\":54.0894797,\"longitude\":-6.18671},{\"user_id\":26,\"name\":\"Stephen McArdle\",\"latitude\":53.038056,\"longitude\":-7.653889},{\"user_id\":6,\"name\":\"Theresa Enright\",\"latitude\":53.1229599,\"longitude\":-6.2705202},{\"user_id\":4,\"name\":\"Ian Kehoe\",\"latitude\":53.2451022,\"longitude\":-6.238335},{\"user_id\":5,\"name\":\"Nora Dempsey\",\"latitude\":53.1302756,\"longitude\":-6.2397222},{\"user_id\":11,\"name\":\"Richard Finnegan\",\"latitude\":53.008769,\"longitude\":-6.1056711},{\"user_id\":31,\"name\":\"Alan Behan\",\"latitude\":53.1489345,\"longitude\":-6.8422408},{\"user_id\":13,\"name\":\"Olive Ahearn\",\"latitude\":53.0,\"longitude\":-7.0},{\"user_id\":15,\"name\":\"Michael Ahearn\",\"latitude\":52.966,\"longitude\":-6.463},{\"user_id\":17,\"name\":\"Patricia Cahill\",\"latitude\":54.180238,\"longitude\":-5.920898},{\"user_id\":39,\"name\":\"Lisa Ahearn\",\"latitude\":53.0033946,\"longitude\":-6.3877505},{\"user_id\":24,\"name\":\"Rose Enright\",\"latitude\":54.133333,\"longitude\":-6.433333},{\"user_id\":29,\"name\":\"Oliver Ahearn\",\"latitude\":53.74452,\"longitude\":-7.11167},{\"user_id\":30,\"name\":\"Nick Enright\",\"latitude\":53.761389,\"longitude\":-7.2875},{\"user_id\":23,\"name\":\"Eoin Gallagher\",\"latitude\":54.080556,\"longitude\":-6.361944}]";

        List<Customer> customerList = solution.findCustomersWithingRange(location, distance, fileName);
        assertEquals(16, customerList.size());
        assertEquals(expected, new Gson().toJson(customerList));
    }
}
