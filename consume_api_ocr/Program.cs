
using consume_api_ocr;
using Newtonsoft.Json;
using System.Text;

var url = "https://eastus.api.cognitive.microsoft.com/vision/v3.2/ocr";
var key = "683f04d178814f49a0922fc52fa149b5";
var parameters = "?language=es&detectOrientation=true&model-version=latest";
var image = "https://www.campusmvp.es/recursos/image.axd?picture=/2018/4T/02.png";
var json = JsonConvert.SerializeObject(new { url = image });
var client = new HttpClient();

client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", key);
client.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Region", "eastus");
var response = await client.PostAsync(url + parameters,
    new StringContent(json, Encoding.UTF8, "application/json")
    );

var content = await response.Content.ReadAsStringAsync();
var ocrResponse = JsonConvert.DeserializeObject<OCRResponse>(content);
var text = getOcrText(ocrResponse!);
Console.WriteLine(text);

static string getOcrText(OCRResponse ocrResponse)
{
    var sb = new StringBuilder();
    foreach (var region in ocrResponse.regions)
    {
        foreach (var line in region.lines)
        {
            foreach (var word in line.words)
            {
                sb.Append(word.text);
                sb.Append(" ");
            }
            sb.AppendLine();
        }
    }
    return sb.ToString();
}
