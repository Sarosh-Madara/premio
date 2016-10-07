package com.saroshmadara.root.premiotravelsandtours.datasource;

import com.saroshmadara.root.premiotravelsandtours.R;
import com.saroshmadara.root.premiotravelsandtours.model.CountryVisa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/18/16.
 */
public class VisaDataSource {

    public static ArrayList<CountryVisa> getData(){

        ArrayList<CountryVisa> countryVisas = new ArrayList<>();

        String[] names = {"Thailand","Singapore","South Africa","Malaysia","China","Turkey"};
        int[] flagId = {R.mipmap.thailandflag,R.mipmap.singaporeflag,R.mipmap.southafricaflag,R.mipmap.malaysiaflag,R.mipmap.chinaflag,R.mipmap.turkeyflag};

        String[] description = {"Thailand is a country on Southeast Asia’s Indochina peninsula known for tropical beaches, opulent royal palaces, ancient ruins and ornate temples displaying figures of Buddha, a revered symbol. In Bangkok, the capital, an ultramodern cityscape rises next to quiet canal and riverside communities. Commercial hubs such as Chinatown consist of labyrinthine alleys crammed with shophouses, markets and diners.",
                "Singapore, an island city-state off southern Malaysia, is a global financial centre with a tropical climate and multicultural population. In circa-1820 Chinatown stands the red-and-gold Buddha’s Tooth Relic Temple, Little India offers colorful souvenirs and Arab Street is lined with fabric shops. Singapore is also known for eclectic street fare, served in hawker centres such as Tiong Bahru and Maxwell Road.",
                "South Africa is a country on the southernmost tip of the African continent, marked by several distinct ecosystems. Inland safari destination Kruger National Park covers vast shrublands populated by big game; the Western Cape encompasses lush winelands around Stellenbosch and Paarl, wild beaches, craggy cliffs at the Cape of Good Hope, forest and lagoons along the Garden Route, and the city of Cape Town, beneath flat-topped Table Mountain.",
                "Malaysia is a Southeast Asian country occupying the Malaysian Peninsula and part of the island of Borneo. It's known for its beaches, rainforests and mix of Malay, Chinese, Indian and European influences. The sprawling capital, Kuala Lumpur, is home to colonial buildings, busy shopping districts such as Bukit Bintang and skyscrapers including the iconic, 451m-tall Petronas Twin Towers.",
                "China, officially the People's Republic of China (PRC), is a sovereign state in East Asia. It is the world's most populous country, with a population of over 1.35 billion. The PRC is a single-party state governed by the Communist Party of China. It exercises jurisdiction over 22 provinces, five autonomous regions, four direct-controlled municipalities (Beijing, Tianjin, Shanghai and Chongqing), and two mostly self-governing special administrative regions (Hong Kong and Macau).",
                "Turkey is a nation straddling eastern Europe and western Asia with cultural connections to ancient Greek, Persian, Roman, Byzantine and Ottoman empires. Cosmopolitan Istanbul, on the Bosphorus Strait, is home to the iconic Hagia Sophia, with its soaring dome and Christian mosaics, the massive 17th-century Blue Mosque and the circa-1460 Topkapı Palace, former home of sultans. Ankara is Turkey’s modern capital."
        };

        String embassyRequirement =
                "Original Passport with 08 months validity attach previous passport if any.\n" +
                        "\n" +
                        "Two Photographs Size (35 x 50) in White Back Ground (Mate finish paper).\n" +
                        "\n" +
                        "Travel Agency Undertaking On Letter Head.\n" +
                        "\n" +
                        "Valid CNIC Copy.\n" +
                        "\n" +
                        "Account Maintenance Certificate With Bank Statement Last Six Month Minimum Balance 100,000/- (For Single Person).\n" +
                        "\n" +
                        "Request Letter for Embassy on Company Letterhead with Visiting Card.\n" +
                        "\n" +
                        "Note: All visas charges are non-refundable in case of Visa reject or Delay.\n";


        String[] durationOfStay = {
                "Thailand Tourist Single Entry Visa Travel Validity 03 Months, Stay Validity 01 Month.\n" +
                        "\n" +
                        "Visa Processing Time 7 To 8 Working Days.\n",
                "SingaporeTourist Single Entry Visa Travel Validity 01 Month Stay Validity as Per Singapore Airport Immigration.\n" +
                        "\n" +
                        "Visa Processing Time 5 To 7 Working Days.\n",
                "Visa Processing Time is 20 to 22 Days.",
                "\n" +
                        "Malaysia Tourist Single Entry Visa Travel Validity 03 Months, Stay Validity 01 Month.\n" +
                        "\n" +
                        "Visa Processing Time 7 To 8 Working Days.\n",
                "\n" +
                        "China Fresh Tourist Single Entry Visa Travel Validity 03 Months, Stay Validity 01 Month.\n" +
                        "\n" +
                        "Visa Processing Time 4 To 6 Weeks.\n",
                "\n" +
                        "Turkey Tourist Single Entry Visa Travel Validity 03 Months, Stay Validity 01 Month.\n" +
                        "\n" +
                        "Visa Processing Time to 15 to 20 Working Days.\n"
        };

        String[] visaFee = {
                "Visa Charges PKR, 38,000/- Per Person.\n" +
                        "Payment is to be made in Advance.\n",
                "Visa Charges PKR, 9500/- Per Person\n" +
                        "Payment is to be made in Advance.\n",
                "South Africa Fresh Visa Charges - PKR. 8,000 Per Person\n" +
                        "Payment is to be made in Advance.\n",
                "Visa Charges PKR, 6000/- Per Person.\n" +
                        "Payment is to be made in Advance.\n",
                "Visa Charges PKR, 58,000/- Per Person\n" +
                        "Payment is to be made in Advance.\n",
                "Visa Charges PKR, 13000/- Per Person\n" +
                        "Payment is to be made in Advance.\n"
        };



        for (int i = 0; i <flagId.length && i<names.length; i++) {
            countryVisas.add(new CountryVisa(names[i],flagId[i],description[i],embassyRequirement,durationOfStay[i],visaFee[i],null,null));
        }

        return countryVisas;
    }


}
