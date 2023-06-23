using System.Text.Json;
using Azure.Messaging.ServiceBus;
using ESBLibrary;

ServiceBusClient client;
ServiceBusProcessor processor;

var clientOptions = new ServiceBusClientOptions()
{
    TransportType = ServiceBusTransportType.AmqpWebSockets
};

var connectionString = "Endpoint=sb://servicebusutn.servicebus.windows.net/;SharedAccessKeyName=free-key;SharedAccessKey=wzglTpslHHEvDtF0+/bajQwB9EhxVf38d+ASbOCBRXo=;EntityPath=solicitudes01";

client = new ServiceBusClient(connectionString, clientOptions);

processor = client.CreateProcessor("solicitudes01", new ServiceBusProcessorOptions());

try
{
    // add handler to process messages
    processor.ProcessMessageAsync += MessageHandler;

    // add handler to process any errors
    processor.ProcessErrorAsync += ErrorHandler;

    // start processing 
    await processor.StartProcessingAsync();

    Console.WriteLine("Wait for a minute and then press any key to end the processing");
    Console.ReadKey();

    // stop processing 
    Console.WriteLine("\nStopping the receiver...");
    await processor.StopProcessingAsync();
    Console.WriteLine("Stopped receiving messages");
}
finally
{
    // Calling DisposeAsync on client types is required to ensure that network
    // resources and other unmanaged objects are properly cleaned up.
    await processor.DisposeAsync();
    await client.DisposeAsync();
}

async Task MessageHandler(ProcessMessageEventArgs args)
{
    string json = args.Message.Body.ToString();
    Console.WriteLine($"Received: {json}");

    // Here you handle the message
    var request = JsonSerializer.Deserialize<ESBRequest>(json)!;
    Console.WriteLine("-------------------------------------------");
    Console.WriteLine($"Peticion: {args.Message.SequenceNumber}");
    Console.WriteLine($"usuario: {request.Usuario}");
    Console.WriteLine($"producto: {request.NombreProducto}");
    Console.WriteLine($"cantidad: {request.Cantidad}");

    await args.CompleteMessageAsync(args.Message);
}


async Task<Task> ErrorHandler(ProcessErrorEventArgs args)
{
    Console.WriteLine(args.Exception.ToString());
    await Task.Yield();

    return Task.CompletedTask;
}