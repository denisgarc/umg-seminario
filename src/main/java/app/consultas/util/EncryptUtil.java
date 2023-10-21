/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.consultas.util;

import javax.crypto.Cipher;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author denox
 */
public class EncryptUtil {
    private final String key = "ermita234";
    private final String vector = "ermita234";
    
    public String encrypt(String inputText) {
        
        /*SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
        IvParameterSpec iv = new IvParameterSpec(vectorInicial.getBytes("UTF-8"));

        // Configurar el cifrador
        Cipher cifrador = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta, iv);

        // Encriptar el texto
        byte[] textoEncriptado = cifrador.doFinal(textoOriginal.getBytes("UTF-8"));

        // Convertir el texto encriptado a una representación en Base64
        String textoEncriptadoBase64 = Base64.getEncoder().encodeToString(textoEncriptado);

        return textoEncriptadoBase64;*/
        return "";
        
    }
}
