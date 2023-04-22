package be.manaev.prayer;

//import be.manaev.prayer.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class PrayerApplication {
    public static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(PrayerApplication.class, args);
        Logger log = LoggerFactory.getLogger(PrayerApplication.class);

        //new Second().loadItUp("karina", 2);

    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}



//new Second().loadItUp("32489738321", 2);

//        for(int i = 0; i < 1_000_000;i++){
//             log.info("This is just a log to keep it alive");
//             //System.out.println("this is system uot println");
//             try {
//                 Thread.sleep(600_000);
//             } catch (InterruptedException e) {
//                 throw new RuntimeException(e);
//             }
//         }