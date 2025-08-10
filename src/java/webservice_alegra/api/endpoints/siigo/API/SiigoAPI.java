/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.api.endpoints.siigo.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import webservice_alegra.util.SiigoTokenManager;
import webservice_alegra.util.WebServiceAlegraUtilitarioM;


/**
 * Cliente HTTP para interactuar con la API de Siigo.
 * Permite enviar solicitudes GET, POST y PUT utilizando autenticación
 * por token Bearer o credenciales básicas (modo pruebas).
 * 
 * Integra auditoría de solicitudes y manejo de errores a través de excepciones personalizadas.
 * 
 * <p><b>Uso típico:</b></p>
 * <pre>
 * JsonElement response = SiigoAPI.callSiigoAPI(
 *      "invoices",
 *      "POST",
 *      new LinkedHashMap<>(),
 *      "{ \"customer\": \"...\" }"
 * );
 * </pre>
 * 
 * @author Brayan
 * @since 10 Julio 2025
 */
public class SiigoAPI {
        /** Modo de pruebas (true) o producción (false) */
    private final static boolean TEST_MODE = false;

    private final static String SERVER_URL = "https://api.siigo.com/v1/";
    private final static String AUTH_TEST = "dGVjbm9sb2dpYTIwQGlwbGVyLmVkdS5jbzpkZTBkYTFhZmE0MjgzOTYzMGM5NyA=";// "dGVjbm9sb2dpYTIwQGlwbGVyLmVkdS5jbzpkZTBkYTFhZmE0MjgzOTYzMGM5NyA=";       // Base64 encode of: tecnologia20@ipler.edu.co:de0da1afa42839630c97      //  "dGVjMDVAaXBsZXIuZWR1LmNvOjM1ZTE4ODk3NzA4YTY1YWYxY2I0";   //Base64 encode of: tec05@ipler.edu.co:35e18897708a65af1cb4
    
   /** Constantes para definir el método HTTP */
    private final static String METHOD_GET = "GET";
    private final static String METHOD_POST = "POST";
    private final static String METHOD_PUT = "PUT";

    /**
     * Envía una solicitud HTTP a la API de Siigo.
     *
     * @param function   Ruta o recurso de la API (por ejemplo: "invoices")
     * @param method     Método HTTP a utilizar ("GET", "POST" o "PUT")
     * @param getData    Parámetros de consulta (solo para GET)
     * @param postData   Cuerpo de la solicitud en formato JSON (solo para POST o PUT)
     * @return           Objeto JSON resultante de la respuesta de la API
     * @throws IOException En caso de error de conexión o lectura de datos
     */
    public static JsonElement callSiigoAPI(String function, String method, LinkedHashMap<String, String> getData, String postData) throws IOException {
        String functionUrl = SERVER_URL + function;
        WebServiceAlegraUtilitarioM utilitario = new WebServiceAlegraUtilitarioM();
        String token = SiigoTokenManager.getToken();
        utilitario.generarRegistroAuditoriaInformacionAlegra("SigoAPI->callSigoAPI postData a ser enviada a SIIGO->",postData);
        
        // PREPARE GET REQUEST DATA
        if (method.equals(METHOD_GET)) {
            functionUrl += "?";
            int i = 0;
            for (Map.Entry<String, String> entry : getData.entrySet()) {
                if (i > 0) {
                    functionUrl += "&";
                }
                functionUrl += entry.getKey() + "=" + entry.getValue();
                i++;
            }
        }
        
        utilitario.generarRegistroAuditoriaInformacionAlegra("SiigoAPI->callSigoAPI functionUrl SOLICITUD ENVIADA A SIIGO->",functionUrl);
        
        // PREPARE REQUEST
        HttpsURLConnection httpsConnetion = null;
       
        
                // Preparar conexión HTTPS

        try {
            httpsConnetion = (HttpsURLConnection) new URL(functionUrl).openConnection();
        } catch (MalformedURLException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [1]", ex.toString() );
        } catch (IOException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [2]", ex.toString() );
        }
        if (TEST_MODE) {
            httpsConnetion.setRequestProperty("Authorization", "Basic " + AUTH_TEST);
        } else {
            httpsConnetion.setRequestProperty("Content-Type", "application/json");
            httpsConnetion.setRequestProperty("Authorization", "Bearer " + token ); // SIIGO
            httpsConnetion.setRequestProperty("Content-Type", "application/json");
            httpsConnetion.setRequestProperty("Partner-Id", "SandboxSiigoApi");    
        }
        try {
            httpsConnetion.setRequestMethod(method);
        } catch (ProtocolException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [3]", ex.toString() );
        }
        httpsConnetion.setConnectTimeout(60000);
        httpsConnetion.setReadTimeout(60000);
        httpsConnetion.setDoInput(true);
        httpsConnetion.setDoOutput(true);
        httpsConnetion.setUseCaches(false);

        // SEND POST OR PUT DATA
        if (method.equals(METHOD_POST) || method.equals(METHOD_PUT)) {
            OutputStream outputStream = null;
            try {
                outputStream = httpsConnetion.getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [4]", ex.toString() );
            }
            try {
                outputStream.write(postData.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [5]", ex.toString() );
            } catch (IOException ex) {
                Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [6]", ex.toString() );
            }
            try {
                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [7]", ex.toString() );
            }
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [8]", ex.toString() );
            }
        }

        // GET RESPONSE
        int responseCode = 0;
        try {
            responseCode = httpsConnetion.getResponseCode();
        } catch (IOException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [9]", ex.toString() );
        }

        JsonElement json = null;

        try {
            InputStream inputStream;
            // Check if the response is an error (response code >= 400)
            if (httpsConnetion.getResponseCode() >= 400) {
                inputStream = httpsConnetion.getErrorStream();  // Use ErrorStream for error responses
            } else {
                inputStream = httpsConnetion.getInputStream();  // Use InputStream for success responses
            }

            // Read the input stream (either success or error stream)
            String jsonResponse = getResponse(inputStream);

            // Parse the JSON response
            JsonParser parser = new JsonParser();
            json = parser.parse(jsonResponse);

            // Handle the parsed JSON as needed
            System.out.println("JSON Response: " + json);
            utilitario.generarRegistroAuditoriaInformacionAlegra("Respuesta JSON proveniente de SIIGO", json.toString());
                      

        } catch (Exception ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [11]", ex.toString());
        }
        
        //utilitario.generarRegistroAuditoriaInformacionAlegra("SigoAPI->callSigoAPI RESPUESTA Sigo",json.toString());
        if (responseCode >= 200 && responseCode < 400) {
           //utilitario.generarRegistroAuditoriaInformacionAlegra("SigoAPI->callSigoAPI EXITO 200",json.toString());
            return json;
        } else {
            try{
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [12] SigoAPI->callSigoAPI en 400 ",json.toString());
                SiigoAPIException siigoAPIException = new SiigoAPIException(responseCode);
                try {
                    siigoAPIException.setMessage(httpsConnetion.getResponseMessage() + "-" + getResponse(httpsConnetion.getErrorStream()));
                } catch (IOException ex) {
                    Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                    utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [13]", ex.toString() );
                } catch (Exception ex) {
                    Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                    utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [14]", ex.toString() );
                }
                try {
                    utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR SigoAPI->callSigoAPI en 400 getResponseMessage ",httpsConnetion.getResponseMessage() + "-" + getResponse(httpsConnetion.getErrorStream()));
                } catch (IOException ex) {
                    Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                    utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [15]", ex.toString() );
                } catch (Exception ex) {
                    Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
                    utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [16]", ex.toString() );
                }
                throw siigoAPIException;                
            }catch(SiigoAPIException e){
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR SigoAPI-> 400 "+e.toString(),json.toString());
                utilitario.generarRegistroAuditoriaInformacionAlegra("ERROR [17]", e.toString() );
            }
        }
        return json;
    }
    
    
    /**
     * Lee el contenido de un flujo de entrada y lo devuelve como String.
     *
     * @param inputStream Flujo de entrada desde la conexión HTTP
     * @return Contenido leído como cadena
     */
    private static String getResponse(InputStream inputStream)  {
        StringBuilder response = new StringBuilder(); 
        WebServiceAlegraUtilitarioM util = new WebServiceAlegraUtilitarioM();
        //util.generarRegistroAuditoriaInformacionAlegra("DEBUG", "SigoAPI->getResponse[1]");

        Charset chartSet = Charset.forName("ISO-8859-1");
        //util.generarRegistroAuditoriaInformacionAlegra("DEBUG", "SigoAPI->getResponse[2]");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, chartSet));
        //util.generarRegistroAuditoriaInformacionAlegra("DEBUG", "SigoAPI->getResponse[3]");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                
                response.append(line);
               //util.generarRegistroAuditoriaInformacionAlegra("line in getRespone SigoAPI", line);
            }
        } catch (IOException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
            util.generarRegistroAuditoriaInformacionAlegra("ERROR en getRespone SigoAPI 1 ", ex.toString());
        }
        try {
            //util.generarRegistroAuditoriaInformacionAlegra("DEBUG", "SigoAPI->getResponse[4]");
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(SiigoAPI.class.getName()).log(Level.SEVERE, null, ex);
           // util.generarRegistroAuditoriaInformacionAlegra("ERROR en getRespone SigoAPI 2 ", ex.toString());
        }
        //util.generarRegistroAuditoriaInformacionAlegra("DEBUG", "SigoAPI->getResponse[5]");
        return response.toString();
    }    
}
