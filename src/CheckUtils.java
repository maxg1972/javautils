import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigInteger;

public final class CheckUtils {
    //Pattern for email check
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //IBAN magic number
    private static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");

    /**
     * Check if email address is valid
     * @param address email addresses to be checked
     * @return        true if email address is valid
     */
    public static boolean isValidEMail(String address) {
        boolean valid = false;

        //If address is not null, check validity by regex
        if (address != null) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(address.toLowerCase());
            valid = matcher.find();
        }

        return valid;
    }

    /**
     * Check tax (fiscal) code
     * @param code    tax code to check
     * @param country tax code country
     * @return        true if tax code is valid
     */
    public static boolean isValidTaxCode(String code, Country.CountryCode country) {
        boolean valid = false;

        if (code != null) {
            //If country is null, set default country to ITA
            if (country == null) {
                country = Country.CountryCode.ITA;
            }

            //Check validity by country
            switch (country) {
                case ITA:
                    //Check cod length (16 characters length)
                    if (code.length() == 16) {
                        //Convert code to upper case
                        code = code.toUpperCase();

                        //Valid charset
                        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                        String odd_num = "0123456789";
                        String even_num = "10...2.3.4...5.6.7.8.9";
                        String odd_char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        String even_char = "BAKPLCQDREVOSFTGUHMINJWZYX";
                        String check = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

                        //Check if all first 15 charaters are in charset
                        valid = true;
                        for (int i = 0; i < 15; i++)
                            if (charset.indexOf(code.charAt(i)) <= 0)
                                valid = false;

                        if (valid) {
                            //Varify check digit
                            int num = 0;
                            for (int i = 1; i < 15; i += 2) { //odd chars
                                if ((code.charAt(i) >= '0') && (code.charAt(i) <= '9'))
                                    num += odd_num.indexOf(code.charAt(i));
                                else
                                    num += odd_char.indexOf(code.charAt(i));
                            }
                            for (int i = 0; i < 15; i += 2) { //even chars
                                if ((code.charAt(i) >= '0') && (code.charAt(i) <= '9'))
                                    num += even_num.indexOf(code.charAt(i));
                                else
                                    num += even_char.indexOf(code.charAt(i));
                            }
                            num = (num % 26);
                            valid = (check.charAt(num) == code.charAt(15));
                        }
                    }
                    break;
                default:
                    valid = true;
            }
        }
        return valid;
    }

    /**
     * Check italian tax (fiscal) code
     * @param code    tax code to check
     * @return        true if tax code is valid
     */
    public static boolean isValidTaxCode(String code) {
        return isValidTaxCode(code, Country.CountryCode.ITA);
    }

    /**
     * Check vat identification number
     * @param code  vat id with country code (first 2 caracters)
     * @return      true if vat code is valid
     */
    public static boolean isValidVatCode(String code) {
        boolean valid = false;

        if (code != null && code.length() > 2) {
            code = code.toUpperCase();

            //get country code from initial characters of code
            Country.CountryCode state = Country.getCountryFromISO(code.substring(2));

            //get regex patter from country
            String pattern_string = "";
            switch (state) {
                case ITA:
                    pattern_string = "^IT\\d{11}$";
                    break;
                case AUT:
                    pattern_string = "^ATU[A-Z0-9]{8}$";
                    break;
                case BUL:
                    pattern_string = "^BG\\d{9,10}$";
                    break;
                case CRO:
                    pattern_string = "^HR\\d{11}$";
                    break;
                case CYP:
                    pattern_string = "^CY[A-Z0-9]{9}$";
                    break;
                case CZE:
                    pattern_string = "^CZ\\d{8,10}$";
                    break;
                case DEN:
                    pattern_string = "^DK\\d{8}$";
                    break;
                case EST:
                    pattern_string = "^EE\\d{9}$";
                    break;
                case FIN:
                    pattern_string = "^FI\\d{8}$";
                    break;
                case FRA:
                    pattern_string = "^FR[A-Z0-9]{2}\\d{9}$";
                    break;
                case GER:
                    pattern_string = "^GE\\d{9}$";
                    break;
                case GRE:
                    pattern_string = "^EL\\d{9}$";
                    break;
                case HUN:
                    pattern_string = "^HU\\d{8}$";
                    break;
                case IRL:
                    pattern_string = "^IE\\d{7}[A-Z]{1,2}$";
                    break;
                case LAT:
                    pattern_string = "^LV\\d{11}$";
                    break;
                case LTU:
                    pattern_string = "^LT(?=[0-9]*$)(?:.{9}|.{12})";
                    break;
                case LUX:
                    pattern_string = "^LU\\d{8}$";
                    break;
                case MLT:
                    pattern_string = "^MT\\d{8}$";
                    break;
                case NED:
                    pattern_string = "^NL\\d{9}B\\d{2}$";
                    break;
                case POL:
                    pattern_string = "^PL\\d{10}$";
                    break;
                case POR:
                    pattern_string = "^PT\\d{9}$";
                    break;
                case ROU:
                    pattern_string = "^RO\\d{2,10}$";
                    break;
                case SVK:
                    pattern_string = "^SK\\d{10}$";
                    break;
                case SLO:
                    pattern_string = "^SI\\d{8}$";
                    break;
                case ESP:
                    pattern_string = "^ES[A-Z0-9]\\d{7}[A-Z0-9]$";
                    break;
                case SWE:
                    pattern_string = "^SE\\\\d{10}01$";
                    break;
                case GBR:
                    pattern_string = "^GB(\\d{9}|\\d{12}|GD\\d{3}|HA\\d{3})$";
                    break;
            }

            //Check validity
            Pattern pattern = Pattern.compile(pattern_string, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(code);
            valid = matcher.find();
        }

        return valid;
    }

    /**
     * Check IBAN code
     * @param code  IBAN code
     * @return      tru if IBAN code is valid
     */
    public static boolean isValidIBAN(String code) {
        boolean valid = false;

        if (code != null && code.length() > 2) {
            code = code.toUpperCase();

            //get country code from initial characters of code
            Country.CountryCode state = Country.getCountryFromISO(code.substring(2));

            //Check IBAN code by Country
            Pattern pattern;
            Matcher matcher;
            switch (state) {
                case ITA:
                    //Check syntax
                    pattern = Pattern.compile("^IT\\d{2}[A-Z]\\d{5}\\d{5}[A-Z0-9]{12}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    
                    //Check code
                    if (valid) {
                        //Move 4 first caracter to the end of code
                        String iban = code.substring(4) + code.substring(0,4);

                        //Replace each letter in the string with two digits (A = 10, B = 11, ..., Z = 35).
                        String app = "";
                        for (int i=0;i<=iban.length()-1;i++) {
                            app += Character.getNumericValue(iban.charAt(i));
                        }
                        iban = app;

                        //Convert string as a decimal integer and compute the remainder of that number on division by 97.
                        BigInteger ibanNumber = new java.math.BigInteger(iban.toString());

                        //If remainder is 1, code is valid
                        valid = ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;
                    }
                    break;
                case AUT:
                    //Check syntax
                    pattern = Pattern.compile("^AT\\d{2}\\d{5}\\d{20}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case BUL:
                    //Check syntax
                    pattern = Pattern.compile("^BG\\d{2}\\d{3}\\d{7}\\d{2}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case CRO:
                    //Check syntax
                    pattern = Pattern.compile("^HR\\d{2}\\d{7}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case CYP:
                    //Check syntax
                    pattern = Pattern.compile("^CY\\d{2}[A-Z]\\d{3}\\d{16}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case CZE:
                    //Check syntax
                    pattern = Pattern.compile("^CZ\\d{2}\\d{4}\\d{6}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case DEN:
                    //Check syntax
                    pattern = Pattern.compile("^DK\\d{2}\\d{4}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case EST:
                    //Check syntax
                    pattern = Pattern.compile("^EE\\d{2}\\d{2}\\d{2}\\d{11}\\d{1}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case FIN:
                    //Check syntax
                    pattern = Pattern.compile("^FI\\d{2}\\d6}\\d{7}\\d{1}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case FRA:
                    //Check syntax
                    pattern = Pattern.compile("^FR\\d{2}\\d{5}\\d{5}\\d{11}\\d{2}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case GER:
                    //Check syntax
                    pattern = Pattern.compile("^GE\\d{2}\\d{8}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case GRE:
                    //Check syntax
                    pattern = Pattern.compile("^EL\\d{2}\\d{3}\\d{4}\\d{16}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case HUN:
                    //Check syntax
                    pattern = Pattern.compile("^HU\\d{2}\\d{3}\\d{4}\\d{1}\\d{15}\\d{1}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case IRL:
                    //Check syntax
                    pattern = Pattern.compile("^IE\\d{2}[A-Z]{4}\\d{6}\\d{8}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case LAT:
                    //Check syntax
                    pattern = Pattern.compile("^LV\\d{2}[A-Z]{4}\\d{13}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case LTU:
                    //Check syntax
                    pattern = Pattern.compile("^LT\\d{2}\\d{5}\\d{13}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case LUX:
                    //Check syntax
                    pattern = Pattern.compile("^LU\\d{2}\\d{3}\\d{13}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case MLT:
                    //Check syntax
                    pattern = Pattern.compile("^MT\\d{2}[A-Z]{4}\\d{5}\\d{18}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case NED:
                    //Check syntax
                    pattern = Pattern.compile("^NL\\d{2}[A-Z]{4}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case POL:
                    //Check syntax
                    pattern = Pattern.compile("^PL\\d{2}\\d{3}\\d{4}\\d{1}\\d{16}}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case POR:
                    //Check syntax
                    pattern = Pattern.compile("^PT\\d{2}\\d{8}\\d{11}\\d{2}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case ROU:
                    //Check syntax
                    pattern = Pattern.compile("^RO\\d{2}[A-Z]{4}\\d{16}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case SVK:
                    //Check syntax
                    pattern = Pattern.compile("^SK\\d{2}\\d{4}\\d{6}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case SLO:
                    //Check syntax
                    pattern = Pattern.compile("^SI\\d{2}\\d{5}\\d{8}\\d{2}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case ESP:
                    //Check syntax
                    pattern = Pattern.compile("^ES\\d{2}\\d{4}\\d{4}\\d{2}\\d{10}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case SWE:
                    //Check syntax
                    pattern = Pattern.compile("^SE\\d{2}\\d{3}\\d{16}\\d{1}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                case GBR:
                    //Check syntax
                    pattern = Pattern.compile("^GB\\d{2}[A-Z]{4}\\d{6}\\d{8}$", Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(code);
                    valid = matcher.find();
                    break;
                default:
                    valid = true;
            }
        }

        return valid;
    }

    /**
     * Check if string is a number
     * @param string    string to be checked
     * @return          true if string is a number
     */
    public static boolean isNumeric(String string) {
        if (string != null) {
            Pattern pattern = Pattern.compile("^[0-9,.E\\+\\-]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(string);

            return matcher.find();
        }
        else {
            return false;
        }
    }
}
