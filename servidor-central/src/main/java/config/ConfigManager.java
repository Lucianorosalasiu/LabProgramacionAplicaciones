package config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

/**
 *
 * @author alexis
 */

/**
 * Clase para manejar la configuración de la aplicación.
 */
public class ConfigManager {
    // Códigos de color ANSI
    private static final String GREEN_COLOR_CODE = "\u001B[32m";
    private static final String RESET_COLOR_CODE = "\u001B[0m";
    
    // Ruta del directorio de configuración
    private static final String CONFIG_DIR_PATH = System.getProperty("user.home") + "/.turismoUy";

    // Ruta completa del archivo de configuración
    private static final String CONFIG_FILE_PATH = CONFIG_DIR_PATH + "/config.properties";

    // Instancia única de ConfigManager (Singleton)
    private static final ConfigManager instance = new ConfigManager();

    // Propiedades de configuración
    private Properties configProperties;

    /**
     * Constructor privado para evitar instanciación directa.
     */
    private ConfigManager() {
        initializeConfig();
    }

    /**
     * Inicializa la configuración, creando el directorio y copiando el archivo de configuración si es necesario.
     */
    private void initializeConfig() {
        try {
            File configDir = new File(CONFIG_DIR_PATH);
            File configFile = new File(CONFIG_FILE_PATH);
            
            if (configDir.mkdir()) {
                System.out.println(
                        GREEN_COLOR_CODE + 
                        "La carpeta de configuración no fue encontrada... creando carpeta por defecto en: " + 
                        CONFIG_DIR_PATH + 
                        RESET_COLOR_CODE
                );
                copyDefaultConfigFile();
            } else {
                if (!configFile.exists()) {
                    System.out.println(
                            GREEN_COLOR_CODE + 
                            "El archivo de configuración no fue encontrado... creando config por defecto en:" + 
                            CONFIG_FILE_PATH + 
                            RESET_COLOR_CODE
                    );
                    copyDefaultConfigFile();
                }
            }

            configProperties = new Properties();
            try (InputStream inputStream = Files.newInputStream(configFile.toPath())) {
                configProperties.load(inputStream);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Copia el archivo de configuración predeterminado desde los recursos del proyecto.
     */
    private void copyDefaultConfigFile() throws IOException {
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Files.copy(resourceStream, new File(CONFIG_FILE_PATH).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Obtiene la instancia única de ConfigManager (Singleton).
     *
     * @return Instancia única de ConfigManager.
     */
    public static ConfigManager getInstance() {
        return instance;
    }

    /**
     * Obtiene el valor de una propiedad de configuración.
     *
     * @param key Clave de la propiedad.
     * @return Valor de la propiedad o null si no se encuentra.
     */
    public String getConfigValue(String key) {
        return configProperties.getProperty(key);
    }
}