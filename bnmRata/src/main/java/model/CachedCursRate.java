package bnm.md/ro/content/ratele-de-schimb;

import lombok.Value;

import java.util.List;

@Value
public class CachedCurrencyRates {
    List<CurrencyRate> currencyRates;
}