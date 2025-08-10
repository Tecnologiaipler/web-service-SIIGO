package webservice_alegra.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Clase responsable de gestionar la autenticación con la API de Siigo.
 * 
 * <p>Su función principal es obtener un <b>token de acceso</b> válido a partir de las credenciales
 * (usuario y clave de acceso) configuradas.</p>
 * 
 * <h3>Características:</h3>
 * <ul>
 *   <li>Realiza una solicitud POST al endpoint de autenticación de Siigo.</li>
 *   <li>Extrae el campo <code>access_token</code> de la respuesta JSON.</li>
 *   <li>Permite ser ejecutada como aplicación standalone para pruebas.</li>
 * </ul>
 * 
 * <b>Nota:</b> El token generado suele tener una validez temporal; se recomienda obtenerlo nuevamente cuando caduque.
 * 
 * @author Brayan Bernal
 * @since 10 Julio 2025
 */

public class SiigoTokenManager {
    /** URL de autenticación de la API de Siigo. */
    private static final String AUTH_URL = "https://api.siigo.com/auth";
        /** Usuario registrado en la cuenta de Siigo para autenticación API. */
    private static final String USERNAME = "contabilidad@ipler.edu.co";
        /** Clave de acceso codificada provista por Siigo para autenticación API. */
    private static final String ACCESS_KEY = "MzFjOGJhMWMtNmJjYi00ZDA4LTliZDQtYzViZGQ1YjIzZTJhOlZSczk1RCN5MVc=";

/**
     * Solicita un nuevo token de acceso a la API de Siigo.
     * 
     * <p>Este método siempre realiza una nueva conexión HTTP a la URL de autenticación
     * de Siigo, enviando en el cuerpo de la petición las credenciales necesarias.</p>
     * 
     * @return El token de acceso (cadena JWT) retornado por la API de Siigo.
     * @throws IOException Si ocurre un error en la conexión o si la respuesta HTTP
     *         no es exitosa (código diferente de 200).
     */
    public static String getToken() throws IOException {
        URL url = new URL(AUTH_URL);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        String payload = "{"
            + "\"username\": \"" + USERNAME + "\","
            + "\"access_key\": \"" + ACCESS_KEY + "\""
            + "}";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes("UTF-8"));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            String response = readStream(conn.getInputStream());
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(response).getAsJsonObject();


            String token = obj.get("access_token").getAsString();
            // Logging opcional
            return token;
        } else {
            String errorMsg = "[SiigoTokenManager] Error al obtener token. Código: " + responseCode + " - " + readStream(conn.getErrorStream());
            System.err.println(errorMsg);
            throw new IOException(errorMsg);
        }
    }

    /**
     * Lee un {@link InputStream} y devuelve su contenido como una cadena de texto.
     * 
     * @param inputStream Flujo de datos de entrada.
     * @return Contenido completo del flujo como String.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Método principal de prueba para la obtención de tokens de Siigo.
     * <p>
     * Ejecuta la obtención de un token inmediatamente y luego vuelve a solicitarlo
     * tras 30 segundos, utilizando un {@link ScheduledExecutorService}.
     * </p>
     *
     * @param args Argumentos de línea de comando (no utilizados).
     * @throws Exception Si ocurre un error durante la ejecución de las solicitudes.
     */
    public static void main(String args[]) throws Exception {
        System.out.println("Inicio del programa. Token se solicitará en 30 segundos...");
        String token = getToken();
                System.out.println("Token recibido:\n" + token);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.schedule(() -> {
            try {
                System.out.println("Ejecutando solicitud 2 de token...");
                String token2 = getToken();
                System.out.println("Token recibido:\n" + token2);
            } catch (Exception e) {
                System.err.println("Error obteniendo el token:");
                e.printStackTrace();
            }
        }, 30, TimeUnit.SECONDS);

        // El hilo principal puede continuar aquí con otras tareas si deseas
        // Por ejemplo, simulamos que el programa sigue vivo
        Thread.sleep(60000); // Esperamos 60s para que veas el resultado
        scheduler.shutdown();
    }
}


