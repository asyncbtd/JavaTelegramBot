package by.btd.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Configuration
@RequiredArgsConstructor
public class LocalService {

    private static List<JsonObject> languageSettings;

    public static String getString(String language, String key) {
        log.info("Message localization request has been received");
        loadLanguageSettings(language);

        for (JsonObject jsonObject : languageSettings) {
            if (jsonObject.has(key)) {
                return jsonObject.get(key).getAsString();
            }
        }
        return "";
    }

    @SneakyThrows
    private static void loadLanguageSettings(String language) {
        languageSettings = new ArrayList<>();
        File resourcesFolder = new File("src/main/resources/languages");
        File[] files = resourcesFolder.listFiles();

        log.info("Started looking for language settings in the folder [{}]", resourcesFolder);
        if (resourcesFolder.exists() && resourcesFolder.isDirectory()) {
            boolean javaFilesExist = false;
            for (File file : files) {
                if (file.isFile()) {
                    javaFilesExist = true;
                    break;
                }
            }
            if (javaFilesExist) {
                for (File file : files) {
                    Assert.isTrue(file.getName().endsWith(language + ".json"), String.format("Language [%s] not found", language));
                    if (file.isFile() && file.getName().endsWith(language + ".json")) {
                        FileReader reader = new FileReader(file);
                        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                        languageSettings.add(jsonObject);
                    }
                }
            } else {
                log.error("There are no language files in the folder");
            }
        } else {
            log.error("The folder with the language files is missing");
        }
    }
}
