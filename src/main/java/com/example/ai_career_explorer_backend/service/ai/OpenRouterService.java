package com.example.ai_career_explorer_backend.service.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenRouterService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public OpenRouterService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String generateResponse(String prompt) {

        String apiKey = System.getenv("OPENROUTER_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException(
                    "OPENROUTER_API_KEY environment variable is not configured."
            );
        }

        try {

            ObjectNodeBuilder bodyBuilder =
                    new ObjectNodeBuilder(objectMapper);

            bodyBuilder.put(
                    "model",
                    "openrouter/free"
            );

            bodyBuilder.addMessage(
                    "user",
                    prompt
            );

            String requestBody =
                    bodyBuilder.toJson();

            System.out.println(
                    "Sending request to OpenRouter..."
            );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            "https://openrouter.ai/api/v1/chat/completions"
                                    )
                            )
                            .header(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .header(
                                    "HTTP-Referer",
                                    "http://localhost:4200"
                            )
                            .header(
                                    "X-Title",
                                    "AI Career Explorer"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            requestBody
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "OpenRouter HTTP status: "
                            + response.statusCode()
            );

            System.out.println(
                    "OpenRouter response: "
                            + response.body()
            );

            if (
                    response.statusCode() < 200 ||
                    response.statusCode() >= 300
            ) {

                throw new RuntimeException(
                        "OpenRouter request failed. HTTP "
                                + response.statusCode()
                                + ": "
                                + response.body()
                );
            }

            JsonNode root =
                    objectMapper.readTree(
                            response.body()
                    );

            JsonNode choices =
                    root.path("choices");

            if (
                    !choices.isArray() ||
                    choices.isEmpty()
            ) {

                throw new RuntimeException(
                        "OpenRouter response did not contain any choices."
                );
            }

            JsonNode firstChoice =
                    choices.get(0);

            JsonNode message =
                    firstChoice.path("message");

            JsonNode content =
                    message.path("content");

            if (
                    content.isMissingNode() ||
                    content.isNull() ||
                    !content.isTextual()
            ) {

                System.out.println(
                        "Unexpected OpenRouter response structure:"
                );

                System.out.println(
                        response.body()
                );

                throw new RuntimeException(
                        "OpenRouter response did not contain valid AI message content."
                );
            }

            String result =
                    content.asText().trim();

            if (result.isBlank()) {

                throw new RuntimeException(
                        "OpenRouter returned an empty AI response."
                );
            }

            System.out.println(
                    "AI content extracted successfully."
            );

            return result;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "OpenRouter request was interrupted.",
                    e
            );

        } catch (Exception e) {

            System.out.println(
                    "OpenRouter communication error: "
                            + e.getMessage()
            );

            throw new RuntimeException(
                    "Unable to communicate with OpenRouter.",
                    e
            );
        }
    }
    
    private static class ObjectNodeBuilder {

        private final ObjectMapper objectMapper;

        private final com.fasterxml.jackson.databind.node.ObjectNode root;

        private final com.fasterxml.jackson.databind.node.ArrayNode messages;

        ObjectNodeBuilder(
                ObjectMapper objectMapper
        ) {

            this.objectMapper =
                    objectMapper;

            this.root =
                    objectMapper.createObjectNode();

            this.messages =
                    objectMapper.createArrayNode();

            this.root.set(
                    "messages",
                    messages
            );
        }

        void put(
                String field,
                String value
        ) {

            root.put(
                    field,
                    value
            );
        }

        void addMessage(
                String role,
                String content
        ) {

            com.fasterxml.jackson.databind.node.ObjectNode message =
                    objectMapper.createObjectNode();

            message.put(
                    "role",
                    role
            );

            message.put(
                    "content",
                    content
            );

            messages.add(
                    message
            );
        }

        String toJson()
                throws Exception {

            return objectMapper.writeValueAsString(
                    root
            );
        }
    }
}