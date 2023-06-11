using System.Net;
using System.Net.Http.Json;

namespace consume_api_ocr.Api
{
    public class ComputerVisionApi<T, K>
    {
        private static readonly HttpClient client = new();
        private readonly string apiUrl = "https://eastus.api.cognitive.microsoft.com/vision/v3.2";
        private readonly string parameters = "?language=es&detectOrientation=true&model-version=latest";


        public ComputerVisionApi(string key = "683f04d178814f49a0922fc52fa149b5", string region = "eastus")
        {
            client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", key);
            client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Region", region);
        }

        public async Task<T?> ConsumeOCRLocal(string filePath)
        {
            try
            {
                // File
                await using var stream = File.OpenRead(filePath);
                using var request = new HttpRequestMessage(HttpMethod.Post, "file");
                using var content = new MultipartFormDataContent
                {
                    { new StreamContent(stream), "file", "Request.jpg" }
                };

                request.Content = content;

                using HttpResponseMessage res = await client
                    .PostAsync($"{apiUrl}/ocr{parameters}", content);

                if (res.StatusCode != HttpStatusCode.OK)
                    throw new Exception("No se ha podido procesar la solicitud");

                return await res.Content.ReadFromJsonAsync<T>();
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }

        public async Task<T?> ConsumeOCRUrl(string imageUrl)
        {
            try
            {
                using HttpResponseMessage res = await client
                    .PostAsJsonAsync($"{apiUrl}/ocr{parameters}", new { url = imageUrl });

                if (res.StatusCode != HttpStatusCode.OK)
                    throw new Exception("No se ha podido procesar la solicitud");

                return await res.Content.ReadFromJsonAsync<T>();
            }
            catch (Exception e)
            {
                throw new Exception($"Ha sucedido un error inesperado ({e.Message})");
            }
        }
    }
}
