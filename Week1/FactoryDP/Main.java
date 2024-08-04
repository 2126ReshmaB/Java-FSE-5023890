package FactoryDP;

public class Main{
  public static void main(String args[]){
    Document word = DocumentFactory.createDocument("WordDocument");
    word.create();
    Document pdf = DocumentFactory.createDocument("PdfDocument");
    pdf.create();
    Document excel = DocumentFactory.createDocument("ExcelDocument");
    excel.create();
  }
}


// javac FactoryDP/*.java
// java FactoryDP.Main  