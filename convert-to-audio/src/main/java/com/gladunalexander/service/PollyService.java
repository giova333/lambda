package com.gladunalexander.service;

import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.VoiceId;
import com.gladunalexander.aws.AWSFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class PollyService {

    private final AmazonPollyClient client;

    public PollyService() {
        client = AWSFactory.getPolly();
    }

    public File convertTextToAudioFile(String text, String voice, String fileName) {
        SynthesizeSpeechRequest request =
                new SynthesizeSpeechRequest()
                        .withText(text)
                        .withVoiceId(VoiceId.fromValue(voice))
                        .withOutputFormat(OutputFormat.Mp3);

        SynthesizeSpeechResult synthesizedSpeech = client.synthesizeSpeech(request);

        return createAudioFile(synthesizedSpeech, fileName);
    }

    private File createAudioFile(SynthesizeSpeechResult synthesizedSpeech, String fileName) {
        InputStream audioStream = synthesizedSpeech.getAudioStream();
        File file = new File("/tmp/" + fileName);
        try {
            Files.copy(audioStream, file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
