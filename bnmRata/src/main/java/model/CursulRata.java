
package bnm.md/ro/content/ratele-de-schimb;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Builder
public class CurrencyRate {
    String numCode;
    String charCode;
    String nominal;
    String name;
    String value;
}
