package com.pitang.mobile.infraestrutura.util;

import com.pitang.mobile.infraestrutura.exception.InfraException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

   public static String md5(String valor) {
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));

           return String.format("%32x", hash);
       } catch (NoSuchAlgorithmException e) {
           throw new InfraException(e);
       }
   }
}
