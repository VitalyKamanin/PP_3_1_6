import model.User;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

    public class App {

        public static void main( String[] args ) {
            final RestTemplate restTemplate = new RestTemplate();
            final String url = "http://94.198.50.185:7081/api/users";
            final User newUser = new User(3L, "James", "Brown", (byte) 23);
            final User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 23);
            ResponseEntity<String> response;
            HttpEntity<User> requestEntity;
            String answer = "";

            response = restTemplate.getForEntity(url, String.class);
            HttpHeaders headers = response.getHeaders();
            String set_cookie = headers.getFirst(HttpHeaders.SET_COOKIE);
            String id = set_cookie.substring(0, 43);

            System.out.println("Response: " + response);
            System.out.println("Headers: " + headers);
            System.out.println("Set_Cookie: " + set_cookie);
            System.out.println("ID: " + id);
            System.out.println("----------------------------");

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Cookie", id);

            System.out.println("requestHeaders: " + requestHeaders);
            System.out.println("----------------------------");

            requestEntity = new HttpEntity<>(newUser, requestHeaders);
            System.out.println("requestEntity: " + requestEntity);

            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            answer += response.getBody();
            System.out.println(response);
            System.out.println(response.getBody());
            System.out.println("----------------------------");

            requestEntity = new HttpEntity<>(updatedUser, requestHeaders);
            System.out.println("requestEntity: " + requestEntity);

            response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            answer += response.getBody();
            System.out.println(response);
            System.out.println(response.getBody());
            System.out.println("----------------------------");

            requestEntity = new HttpEntity<>(null, requestHeaders);
            System.out.println("requestEntity: " + requestEntity);

            response = restTemplate.exchange(url + "/3", HttpMethod.DELETE, requestEntity, String.class);
            answer += response.getBody();
            System.out.println(response);
            System.out.println(response.getBody());
            System.out.println("----------------------------");

            System.out.println("The answer is: " + answer);
        }
}
