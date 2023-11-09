package radio.client.utils;

public   class CryptoDummy
{  
   public static String cifraTexto(byte[] text, int cifra){
      byte[] cifrado =  new byte[text.length];
      for(int i = 0; i<text.length; i++){
          cifrado[i] = (byte) (text[i] + cifra);
      }
      return new String(cifrado);
  }

  public static String decifraTexto(byte[] text, int cifra){
      byte[] decifrado =  new byte[text.length];
      for(int i = 0; i<text.length; i++){
          decifrado[i] = (byte) (text[i] - cifra);
      }
      return new String(decifrado);
  }
}

