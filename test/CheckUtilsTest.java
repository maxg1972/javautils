public class CheckUtilsTest {
    public static void main(String[] args) {
        email_test();

        System.out.println();
        taxCode_test();

        System.out.println();
        vatCode_test();

        System.out.println();
        ibanCode_test();

        System.out.println();
        stringAsNumber_test();
    }

    private static void email_test() {
        System.out.println("[Email Test]");

        String email = null;
        System.out.printf("Test %s : %b\n", email,CheckUtils.isValidEMail(email));

        email = "piipo.it";
        System.out.printf("Test %s : %b\n", email,CheckUtils.isValidEMail(email));

        email = "info@pippo.it";
        System.out.printf("Test %s : %b\n", email,CheckUtils.isValidEMail(email));
    }

    private static void taxCode_test() {
        System.out.println("[TaxCode Test]");

        String tax_code = null;
        System.out.printf("Test %s : %b\n", tax_code,CheckUtils.isValidTaxCode(tax_code, Country.CountryCode.ITA));

        tax_code = "GDUMSM72L28E715x";
        System.out.printf("Test %s : %b\n", tax_code,CheckUtils.isValidTaxCode(tax_code, Country.CountryCode.ITA));

        tax_code = "GDUMSM72L28E715T";
        System.out.printf("Test %s : %b\n", tax_code,CheckUtils.isValidTaxCode(tax_code));
    }

    private static void vatCode_test() {
        System.out.println("[VatCode Test]");

        String vat_code = null;
        System.out.printf("Test %s : %b\n", vat_code,CheckUtils.isValidVatCode(vat_code));

        vat_code = "IT12345678901";
        System.out.printf("Test %s : %b\n", vat_code,CheckUtils.isValidVatCode(vat_code));

        vat_code = "IT1234567890";
        System.out.printf("Test %s : %b\n", vat_code,CheckUtils.isValidVatCode(vat_code));
    }

    private static void ibanCode_test() {
        System.out.println("[IBAN Test]");

        String iban = null;
        System.out.printf("Test %s : %b\n", iban,CheckUtils.isValidIBAN(iban));

        iban = "IT83S0572870080474570274305";
        System.out.printf("Test %s : %b\n", iban,CheckUtils.isValidIBAN(iban));

        iban = "IT38S0572870080474570274305";
        System.out.printf("Test %s : %b\n", iban,CheckUtils.isValidIBAN(iban));

        iban = "IT1234567890";
        System.out.printf("Test %s : %b\n", iban,CheckUtils.isValidIBAN(iban));
    }

    private static void stringAsNumber_test() {
        System.out.println("[String as Number Test]");

        String number = null;
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "134";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "115.567";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "238,11";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "-45.90863";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "+11";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "5E+3";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "10E-5";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));

        number = "IT1234567890";
        System.out.printf("Test %s : %b\n", number,CheckUtils.isNumeric(number));
    }
}
