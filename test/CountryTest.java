/**
 * Created by massimo on 19/03/17.
 */
public class CountryTest {
    public static void main(String[] args) {
        countryFormISO_Test();
        System.out.println();
    }

    private static void countryFormISO_Test() {
        System.out.println("[Contry From ISO Test]");

        String iso = null;
        //System.out.printf("Test %s : %s\n", iso,Country.getCountryFromISO(iso).getValue());

        iso = "IT";
        System.out.printf("Test %s : %s\n", iso,Country.getCountryFromISO(iso));

        iso = "ES";
        System.out.printf("Test %s : %s\n", iso,Country.getCountryFromISO(iso));

        iso = "";
        System.out.printf("Test %s : %s\n", iso,Country.getCountryFromISO(iso));

        iso = "XX";
        System.out.printf("Test %s : %s\n", iso,Country.getCountryFromISO(iso));
    }
}
