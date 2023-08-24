package me.shinsunyoung.Service;


import me.shinsunyoung.dto.ResponseDTO;
import me.shinsunyoung.dto.VITORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
//@Service
//public class VITOService {
//
//    @Autowired
//    private TokenService tokenService;
//
//    public String convertAudioToText(VITORequest vitoRequest) throws IOException {
//        URL url = new URL("https://openapi.vito.ai/v1/transcribe");
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//        httpConn.setRequestMethod("POST");
//        httpConn.setRequestProperty("accept", "application/json");
//        httpConn.setRequestProperty("Authorization", "Bearer "+ tokenService.getStoredToken()); // 토큰을 TokenService에서 가져옴
//        httpConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=authsample");
//        httpConn.setDoOutput(true);
//
//        DataOutputStream outputStream;
//        outputStream = new DataOutputStream(httpConn.getOutputStream());
//        File file = new File("./uploads/audio.mp3");
//
//        DataOutputStream outputStream = new DataOutputStream(httpConn.getOutputStream());
//
//        outputStream.writeBytes("--authsample\r\n");
//        outputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName() +"\"\r\n");
//        outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n");
//        outputStream.writeBytes("Content-Transfer-Encoding: binary" + "\r\n");
//        outputStream.writeBytes("\r\n");
//
//        FileInputStream in =new FileInputStream(file);
//        byte[] buffer = new byte[(int)file.length()];
//        int bytesRead = -1;
//        while ((bytesRead = in.read(buffer)) != -1) {
//            outputStream.write(buffer,0,bytesRead);
//            outputStream.writeBytes("\r\n");
//            outputStream.writeBytes("--authsample\r\n");
//        }
//        outputStream.writeBytes("\r\n");
//        outputStream.writeBytes("--authsample\r\n");
//        outputStream.writeBytes("Content-Disposition: form-data; name=\"config\"\r\n");
//        outputStream.writeBytes("Content-Type: application/json\r\n");
//        outputStream.writeBytes("\r\n");
//        outputStream.writeBytes("{}");
//        outputStream.writeBytes("\r\n");
//        outputStream.writeBytes("--authsample\r\n");
//        outputStream.flush();
//        outputStream.close();
//
//        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
//                ? httpConn.getInputStream()
//                : httpConn.getErrorStream();
//        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
//        String TRANSCRIBE = s.hasNext() ? s.next() : "";
//        s.close();
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ResponseDTO response = objectMapper.readValue(TRANSCRIBE, ResponseDTO.class);
//
//        String id = response.getId();
//        System.out.println(id);  // JXId4AqrRfesArP2s_BoWA
//        URL url2 = new URL("https://openapi.vito.ai/v1/transcribe/"+id);
//        HttpURLConnection httpConn2 = (HttpURLConnection) url2.openConnection();
//        httpConn2.setRequestMethod("GET");
//        httpConn2.setRequestProperty("accept", "application/json");
//        httpConn2.setRequestProperty("Authorization", "Bearer "+ tokenService.getStoredToken());
//        InputStream responseStream2 = httpConn2.getResponseCode() / 100 == 2
//                ? httpConn2.getInputStream()
//                : httpConn2.getErrorStream();
//        Scanner s2 = new Scanner(responseStream2).useDelimiter("\\A");
//        String response2 = s2.hasNext() ? s2.next() : "";
//        s2.close();
//        System.out.println(response2);
//        return response2;
//    }
//}


@Service
public class VITOService {

    @Autowired
    private TokenService tokenService;

    public String convertAudioToText(VITORequest vitoRequest) throws IOException {
        URL url = new URL("https://openapi.vito.ai/v1/transcribe");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Authorization", "Bearer "+ tokenService.getStoredToken()); // 토큰을 TokenService에서 가져옴
        httpConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=authsample");
        httpConn.setDoOutput(true);

        DataOutputStream outputStream = new DataOutputStream(httpConn.getOutputStream());
        File file = new File("./uploads/audio.mp3");

        outputStream.writeBytes("--authsample\r\n");
        outputStream.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName() +"\"\r\n");
        outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n");
        outputStream.writeBytes("Content-Transfer-Encoding: binary" + "\r\n");
        outputStream.writeBytes("\r\n");

        FileInputStream in =new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            outputStream.write(buffer,0,bytesRead);
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("--authsample\r\n");
        }
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("--authsample\r\n");
        outputStream.writeBytes("Content-Disposition: form-data; name=\"config\"\r\n");
        outputStream.writeBytes("Content-Type: application/json\r\n");
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("{}");
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("--authsample\r\n");
        outputStream.flush();
        outputStream.close();


        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String responseString = s.hasNext() ? s.next() : "";  // 이름 변경
        s.close();
        System.out.println(responseString);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(responseString, ResponseDTO.class); // 여기서 수정됨

        String id = responseDTO.getId();
        System.out.println(id);  // JXId4AqrRfesArP2s_BoWA

        URL url2 = new URL("https://openapi.vito.ai/v1/transcribe/"+id);
        HttpURLConnection httpConn2 = (HttpURLConnection) url2.openConnection();
        httpConn2.setRequestMethod("GET");
        httpConn2.setRequestProperty("accept", "application/json");
        httpConn2.setRequestProperty("Authorization", "Bearer "+ tokenService.getStoredToken());

        InputStream responseStream2 = httpConn2.getResponseCode() / 100 == 2
                ? httpConn2.getInputStream()
                : httpConn2.getErrorStream();
        Scanner s2 = new Scanner(responseStream2).useDelimiter("\\A");
        String response2 = s2.hasNext() ? s2.next() : "";
        s2.close();
        System.out.println(response2);
        return response2;
    }
}
