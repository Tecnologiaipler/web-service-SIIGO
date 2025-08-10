package webservice_alegra.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Clase de utilidades generales para manejo de fechas, conversiones de JSON
 * y formateo de números. 
 * 
 * Contiene métodos estáticos que permiten:
 * <ul>
 *   <li>Convertir un {@link JsonArray} en un arreglo de Strings.</li>
 *   <li>Convertir un objeto Java a una cadena JSON con formato legible.</li>
 *   <li>Convertir un {@link JsonObject} a una cadena JSON formateada.</li>
 *   <li>Formatear números como valores monetarios con o sin símbolo de moneda.</li>
 * </ul>
 * 
 * @author Brayan Bernal
 */
public class Utils {

    private static final Locale LOCALE = new Locale("ES");

    /**
     * Convierte un {@link JsonArray} en un arreglo de {@link String}.
     * 
     * @param jsonArray El arreglo JSON a convertir.
     * @return Un arreglo de Strings con los valores del {@code jsonArray}.
     *         Si el arreglo está vacío, retorna {@code null}.
     */
    public static String[] convertJsonArrayToStringArray(JsonArray jsonArray) {
        String[] stringArray = null;

        int size = jsonArray.size();
        if (size > 0) {
            stringArray = new String[size];
            for (int i = 0; i < size; i++) {
                stringArray[i] = jsonArray.get(i).getAsString();
            }
        }

        return stringArray;
    }
    
    /**
     * Convierte un objeto Java a una representación JSON en formato legible
     * (pretty print).
     * 
     * @param object El objeto a convertir.
     * @return Una cadena JSON formateada representando el objeto.
     */
    public static String convertObjectToJSON(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        return json;
    }
    
    /**
     * Convierte un {@link JsonObject} en una cadena JSON formateada (pretty print).
     * 
     * @param json El objeto JSON a formatear.
     * @return Una cadena JSON con sangría y saltos de línea para mejor lectura.
     */
    public static String jsonObjectToPrettyString(JsonObject json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonFormated = gson.toJson(json);
        
        return jsonFormated;
    }
    
    
    /**
     * Formatea un número como un valor monetario usando separadores de miles y decimales
     * configurados para el idioma español (punto como separador de miles y coma como separador decimal).
     * 
     * @param number El número a formatear.
     * @param includeSymbol Si es {@code true}, incluye el símbolo de dólar "$" al inicio.
     * @return Una cadena con el valor formateado como dinero.
     *         Ejemplo: {@code 12345.67} con {@code includeSymbol=true} retorna {@code "$12.346"}.
     */
    
    public static String convertNumberToMoney(double number, boolean includeSymbol) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(LOCALE);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat accountFormat = new DecimalFormat("#,###", symbols);

        return (includeSymbol ? "$" : "") + accountFormat.format(number);
    }
    

}
