
using consume_api_ocr.Api;
using consume_api_ocr.Models;


var localImage = "C:\\Users\\arman\\Downloads\\coffee.png";
var imageUrl = "https://www.campusmvp.es/recursos/image.axd?picture=/2018/4T/02.png";


var computerVisionApi = new ComputerVisionApi<OCRResponse, DescribeImageResponse>();
var ocrResponse = await computerVisionApi.ConsumeOCRLocal(localImage);
var ocrResponse2 = await computerVisionApi.ConsumeOCRUrl(imageUrl);
//Console.WriteLine(ocrResponse!.ReadText());
Console.WriteLine(ocrResponse2!.ReadText());

