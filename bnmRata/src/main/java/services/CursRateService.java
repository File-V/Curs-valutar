
package bnm.md/ro/content/ratele-de-schimb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;
import md.bnm.ratele-de-schimb.model.CachedCurrencyRates;
import md.bnm.ratele-de-schimb.config.CbrConfig;
import md.bnm.ratele-de-schimb.model.CurrencyRate;
import md.bnm.ratele-de-schimb.CurrencyRateParser;
import md.bnm.ratele-de-schimb.requester.CbrRequester;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyRateService {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final BnmRequester bnmRequester;
    private final CurrencyRateParser currencyRateParser;
    private final BnmConfig bnmConfig;
    private final Cache<LocalDate, CachedCurrencyRates> currencyRateCache;

    public CurrencyRate getCurrencyRate(String currency, LocalDate date) {
        log.info("getCurrencyRate. currency:{}, date:{}", currency, date);
        List<CurrencyRate> rates;

        var cachedCurrencyRates =  currencyRateCache.get(date);
        if (cachedCurrencyRates == null) {
            var urlWithParams = String.format("%s?date_req=%s", cbrConfig.getUrl(), DATE_FORMATTER.format(date));
            var ratesAsXml = bnmRequester.getRatesAsXml(urlWithParams);
            rates = currencyRateParser.parse(ratesAsXml);
            currencyRateCache.put(date, new CachedCurrencyRates(rates));
        } else {
            rates = cachedCurrencyRates.getCurrencyRates();
        }

        return rates.stream().filter(rate -> currency.equals(rate.getCharCode()))
                .findFirst()
                .orElseThrow(() -> new CurrencyRateNotFoundException("Currency Rate not found. Currency:" + currency + ", date:" + date));
    }
}
