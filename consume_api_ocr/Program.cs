
using consume_api_ocr.Api;
using consume_api_ocr.Models;


var localImage = "C:\\Users\\arman\\Downloads\\coffee.png";
var localImageDescribe = "C:\\Users\\arman\\Downloads\\marguerite.jpg";
var imageUrl = "https://www.campusmvp.es/recursos/image.axd?picture=/2018/4T/02.png";
var describeImageUrl = "https://i.pinimg.com/originals/41/b7/54/41b75468c8d05512ce1f2254583a3d75.jpg";

// API
var computerVisionApi = new ComputerVisionApi<OCRResponse, DescribeImageResponse>();

// OCR
var ocrResponse = await computerVisionApi.ConsumeOCRLocal(localImage);
var ocrResponse2 = await computerVisionApi.ConsumeOCRUrl(imageUrl);
//Console.WriteLine(ocrResponse!.ReadText());
//Console.WriteLine(ocrResponse2!.ReadText());

// Describe image
var describeImageResponse = await computerVisionApi.DescribeImageLocal(localImageDescribe);
var describeImageResponse2 = await computerVisionApi.DescribeImageUrl(describeImageUrl);
//Console.WriteLine(describeImageResponse!.ReadText());
Console.WriteLine(describeImageResponse2!.ReadText());


