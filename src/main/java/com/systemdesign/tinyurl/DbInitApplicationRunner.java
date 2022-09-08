package com.systemdesign.tinyurl;

import static com.systemdesign.tinyurl.HashCoder.intHashToStringHash;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInitApplicationRunner implements ApplicationRunner {

    private static final String VK = "https://vk.com/al_feed.php";
    private static final String YT = "https://www.youtube.com/";
    private static final String TG = "https://web.telegram.org/z/";

    private final TinyUrlRepository tinyUrlRepository;

    @Override
    public void run(ApplicationArguments args) {
        tinyUrlRepository.save(new TinyUrl(intHashToStringHash(VK.hashCode()), VK));
        tinyUrlRepository.save(new TinyUrl(intHashToStringHash(YT.hashCode()), YT));
        tinyUrlRepository.save(new TinyUrl(intHashToStringHash(TG.hashCode()), TG));
    }
}
