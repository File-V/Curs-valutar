package bnm.md/ro/content/ratele-de-schimb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bnm")
public class BnmConfig {
    String url;
}