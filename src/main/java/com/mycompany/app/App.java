package com.mycompany.app;

import java.util.Arrays;
import java.util.List;

import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;

/**
 * Hello world!
 */
public class App {

    
    // replace <NAMESPACE NAME> with the name of your Event Hubs namespace.
    // Example: private static final String namespaceName = "contosons.servicebus.windows.net";
    private static final String namespaceName = "ehjavatest.servicebus.windows.net";

    // Replace <EVENT HUB NAME> with the name of your event hub. 
    // Example: private static final String eventHubName = "ordersehub";
    private static final String eventHubName = "hub01";


    public static void main(String[] args) {
        System.out.println("Hello World!");

        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
                .authorityHost(AzureAuthorityHosts.AZURE_PUBLIC_CLOUD)
                .build();

         // create a producer client        
        EventHubProducerClient producer = new EventHubClientBuilder()        
            .fullyQualifiedNamespace(namespaceName)
            .eventHubName(eventHubName)
            .credential(credential)
            .buildProducerClient();

        // sample events in an array
        List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));

        // create a batch
        EventDataBatch eventDataBatch = producer.createBatch();

        for (EventData eventData : allEvents) {
            // try to add the event from the array to the batch
            if (!eventDataBatch.tryAdd(eventData)) {
                // if the batch is full, send it and then create a new batch
                producer.send(eventDataBatch);
                eventDataBatch = producer.createBatch();

                // Try to add that event that couldn't fit before.
                if (!eventDataBatch.tryAdd(eventData)) {
                    throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
                        + eventDataBatch.getMaxSizeInBytes());
                }
            }
        }
        // send the last batch of remaining events
        if (eventDataBatch.getCount() > 0) {
            producer.send(eventDataBatch);
        }
        producer.close();
    }
}