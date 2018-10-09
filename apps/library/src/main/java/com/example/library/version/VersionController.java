package com.example.library.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class VersionController {

    @Value("${spring.application.version:0.0.0}")
    private String version;

    @Value("${services.config.version:0.0.0}")
    private String configVersion;

    @RequestMapping("/version")
    public HashMap<String, String> getVersion() {
        return new HashMap<String, String>() {{
            put("version", version);
        }};
    }

    @RequestMapping("/version/config")
    public HashMap<String, String> getConfigVersion() {
        return new HashMap<String, String>() {{
            put("version", configVersion);
        }};
    }

}
