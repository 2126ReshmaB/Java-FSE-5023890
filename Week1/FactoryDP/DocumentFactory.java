package FactoryDP;

public class DocumentFactory {
  public static Document createDocument(String type){
    if(type.equalsIgnoreCase("WordDocument")){
      return new Word();
    }
    else if(type.equalsIgnoreCase("PdfDocument")){
      return new Pdf();
    }
    else if(type.equalsIgnoreCase("ExcelDocument")){
      return new Excel();
    }
    else{
      throw new IllegalArgumentException("invalid");
    }
  }
}
