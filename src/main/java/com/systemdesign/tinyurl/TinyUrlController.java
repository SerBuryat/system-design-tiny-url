package com.systemdesign.tinyurl;

import static com.systemdesign.tinyurl.HashCoder.intHashToStringHash;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
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
@RequiredArgsConstructor
public class TinyUrlController {

    private static final HttpStatus REDIRECT_STATUS = HttpStatus.PERMANENT_REDIRECT;

    private final TinyUrlRepository tinyUrlRepository;

    private String tinyUrlPrefix;

    @Value("${server.port}")
    private Integer port;

    @Value("${api.host}")
    private String host;

    @PostConstruct
    @SneakyThrows
    public void init() {
        tinyUrlPrefix =
                "http://" + host + ":" + port + "/tiny-url/";
    }

    @GetMapping("get-tiny-url")
    public ResponseEntity<String> getTinyUrl(@RequestParam String url) {
        var tinyUrlHash = String.valueOf(url.hashCode());
        var tinyUrlHashCoded = intHashToStringHash(tinyUrlHash.hashCode());
        tinyUrlRepository.save(new TinyUrl(tinyUrlHashCoded, url));
        return ResponseEntity.ok(tinyUrlPrefix + tinyUrlHashCoded);
    }

    @GetMapping("/{tinyUrl}")
    public RedirectView redirectToUrl(@PathVariable String tinyUrl) {
        var redirect = new RedirectView();
        redirect.setStatusCode(REDIRECT_STATUS);
        log.debug("Redirecting to...");
        return new RedirectView(tinyUrlRepository.findById(tinyUrl).orElseThrow().getUrl());
    }

    @GetMapping("/show-vault")
    public String showVault() {
        return Streamable.of(tinyUrlRepository.findAll()).toList().toString();
    }
}
