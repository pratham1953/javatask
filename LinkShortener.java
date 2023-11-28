package prathamm;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
 private Map<String, String> shortToLongMap;
 private static final String BASE_URL = "http://short.url/";

 public LinkShortener() {
     this.shortToLongMap = new HashMap<>();
 }

 public String shortenLink(String longUrl) {
     if (shortToLongMap.containsValue(longUrl)) {
         // If the long URL is already shortened, return the existing short URL
         return getKeyByValue(shortToLongMap, longUrl);
     }

     String shortCode = generateShortCode();
     String shortUrl = BASE_URL + shortCode;

     shortToLongMap.put(shortCode, longUrl);

     return shortUrl;
 }

 private String generateShortCode() {
     // Implement your short code generation logic here
     // This is a simple example, and you might want to use a more sophisticated algorithm
     return String.valueOf(shortToLongMap.size() + 1);
 }

 private String getKeyByValue(Map<String, String> map, String value) {
     for (Map.Entry<String, String> entry : map.entrySet()) {
         if (value.equals(entry.getValue())) {
             return entry.getKey();
         }
     }
     return null; // Handle this case based on your specific requirements
 }

 public static void main(String[] args) {
     LinkShortener linkShortener = new LinkShortener();
     Scanner scanner = new Scanner(System.in);

     while (true) {
         System.out.println("Choose an option:");
         System.out.println("1. Shorten a link");
         System.out.println("2. Exit");

         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume the newline character

         switch (choice) {
             case 1:
                 System.out.print("Enter the long URL: ");
                 String longUrl = scanner.nextLine();
                 String shortUrl = linkShortener.shortenLink(longUrl);
                 System.out.println("Shortened URL: " + shortUrl);
                 break;
             case 2:
                 System.out.println("Exiting the link shortener.");
                 scanner.close();
                 System.exit(0);
             default:
                 System.out.println("Invalid option. Please try again.");
         }
     }
 }
}

