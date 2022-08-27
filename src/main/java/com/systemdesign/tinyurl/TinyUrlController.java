package com.systemdesign.tinyurl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("tiny-url/")
@Slf4j
public class TinyUrlController {

    private static final String HASH_SYMBOLS = "asdfghjklzxcvbnmqwertyuiop";

    private static final String TINY_URL_PREFIX = "http://localhost:8080/tiny-url/";
    private static final HttpStatus REDIRECT_STATUS = HttpStatus.PERMANENT_REDIRECT;

    private static final String VK = "https://vk.com/al_feed.php";
    private static final String YT = "https://www.youtube.com/";
    private static final String TG = "https://web.telegram.org/z/";
    private static final Map<String,String> inMemoryRepository = new HashMap<>();

    static {
        inMemoryRepository.put(intHashToStringHash(VK.hashCode()), VK);
        inMemoryRepository.put(intHashToStringHash(YT.hashCode()), YT);
        inMemoryRepository.put(intHashToStringHash(TG.hashCode()), TG);
    }

    @GetMapping("get-tiny-url")
    public ResponseEntity<String> getTinyUrl(@RequestParam String url) {
        var tinyUrlHash = String.valueOf(url.hashCode());
        inMemoryRepository.put(tinyUrlHash, url);
        return ResponseEntity.ok(TINY_URL_PREFIX + tinyUrlHash);
    }

    @GetMapping("/{tinyUrl}")
    public RedirectView redirectToUrl(@PathVariable String tinyUrl) {
        var redirect = new RedirectView();
        redirect.setStatusCode(REDIRECT_STATUS);
        log.debug("Redirecting to...");
        return new RedirectView(inMemoryRepository.get(tinyUrl));
    }

    @GetMapping("/showVault")
    public String showVault() {
        return inMemoryRepository.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    private static String intHashToStringHash(int hash) {
        var strHash =  String.valueOf(hash).replace("-", "1");
        var result = new StringBuilder();
        var hashSymbolsSize = HASH_SYMBOLS.length();

        for(int i = 0; i < strHash.length()-1; i++) {
            var curr = String.valueOf(strHash.charAt(i));
            var next = String.valueOf(strHash.charAt(i+1));
            var nextCurrDigit = Integer.parseInt(curr + next);
            if(nextCurrDigit > hashSymbolsSize-1) {
                result.append(HASH_SYMBOLS.charAt(Integer.parseInt(curr)));
            } else {
                result.append(HASH_SYMBOLS.charAt(nextCurrDigit));
                i++;
            }
        }
        return result.toString();
    }
}
