public final class Country {
    //Allowed countries
    public enum CountryCode {
        ITA,
        AUT,
        BUL,
        CRO,
        CYP,
        CZE,
        DEN,
        EST,
        FIN,
        FRA,
        GER,
        GRE,
        HUN,
        IRL,
        LAT,
        LTU,
        LUX,
        MLT,
        NED,
        POL,
        POR,
        ROU,
        SVK,
        SLO,
        ESP,
        SWE,
        GBR,
        UNKNOW,
    }

    /**
     * Get internation country code from ISO3166 code
     * @param iso_code  ISO3166 country code (2 characters)
     * @return          internation country code
     */
    public static final CountryCode getCountryFromISO(String iso_code) {
        CountryCode code;

        if (iso_code != null) {
            switch (iso_code.toUpperCase()) {
                case "IT":
                    code = CountryCode.ITA;
                    break;
                case "AT":
                    code = CountryCode.AUT;
                    break;
                case "BG":
                    code = CountryCode.BUL;
                    break;
                case "HR":
                    code = CountryCode.CRO;
                    break;
                case "CY":
                    code = CountryCode.CYP;
                    break;
                case "CZ":
                    code = CountryCode.CZE;
                    break;
                case "DK":
                    code = CountryCode.DEN;
                    break;
                case "EE":
                    code = CountryCode.EST;
                    break;
                case "FI":
                    code = CountryCode.FIN;
                    break;
                case "FR":
                    code = CountryCode.FRA;
                    break;
                case "GE":
                    code = CountryCode.GER;
                    break;
                case "EL":
                    code = CountryCode.GRE;
                    break;
                case "HU":
                    code = CountryCode.HUN;
                    break;
                case "IE":
                    code = CountryCode.IRL;
                    break;
                case "LV":
                    code = CountryCode.LAT;
                    break;
                case "LT":
                    code = CountryCode.LTU;
                    break;
                case "LU":
                    code = CountryCode.LUX;
                    break;
                case "MT":
                    code = CountryCode.MLT;
                    break;
                case "NL":
                    code = CountryCode.NED;
                    break;
                case "PL":
                    code = CountryCode.POL;
                    break;
                case "PT":
                    code = CountryCode.POR;
                    break;
                case "RO":
                    code = CountryCode.ROU;
                    break;
                case "SK":
                    code = CountryCode.SVK;
                    break;
                case "SI":
                    code = CountryCode.SLO;
                    break;
                case "ES":
                    code = CountryCode.ESP;
                    break;
                case "SE":
                    code = CountryCode.SWE;
                    break;
                case "GB":
                    code = CountryCode.GBR;
                    break;
                default:
                    code = CountryCode.UNKNOW;
            }
        }
        else {
            code = CountryCode.UNKNOW;
        }

        return code;
    }

}
