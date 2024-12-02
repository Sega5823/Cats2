package org.example;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.github.cage.*;
import entity.Cat;
import entity.CatDTO;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
public class CaptchaService {
    private final Cage cage = new GCage();
    private ConcurrentHashMap<String, String> captchaMap = new ConcurrentHashMap<>();

    public String generate(HttpSession httpSession) {
        String text = cage.getTokenGenerator().next();
        String id = httpSession.getId();
        captchaMap.put(id, text);
        return id;
    }

    public String getTextByID(String id) {
        String text = captchaMap.get(id);
        if (text == null) {
            return "";
        }
        return text;
    }

    public byte[] generatePicture(String text) {
        return cage.draw(text);
//        BufferedImage bufferedImage = cage.drawImage(text);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public boolean validate(HttpSession httpSession, String text) {
        String id = captchaMap.get(httpSession.getId());
        if (id != null && id.equals(text)) {
            return true;
        }
        return false;
    }

}