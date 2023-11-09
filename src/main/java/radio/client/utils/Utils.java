package radio.client.utils;


import java.util.Locale;

public class Utils {
    public static String getLangNameFromLocal(Locale locale){
        switch(locale.toString()){
                case "pt_br":
                    return "Português(Br)";
                case "en_us":
                    return "English";
                case "fr_fr":
                    return "Français";
                case "es_es":
                    return "Español";
                case "it_it":
                    return "Italiano";
                case "de_de":
                    return "Deutsch";
                case "ja_jp":
                    return "日本語";
        }
        return "English";
    }

    public static String getLocalFromLangName(String langName){
        switch(langName){
                case "Português(Br)":
                    return "pt_BR";
                case "English":
                    return "en_US";
                case "Français":
                    return "fr_FR";
                case "Español":
                    return "es_ES";
                case "Italiano":
                    return "it_IT";
                case "Deutsch":
                    return "de_DE";
                case "日本語":
                    return "ja_JP";
        }
        return "en_US";
    }

}
