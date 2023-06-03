﻿
namespace consume_api_ocr
{
    public class Word
    {
        public string boundingBox { get; set; }
        public string text { get; set; }
    }

    public class Line
    {
        public string boundingBox { get; set; }
        public List<Word> words { get; set; }
    }

    public class Region
    {
        public string boundingBox { get; set; }
        public List<Line> lines { get; set; }
    }

    public class OCRResponse
    {
        public string language { get; set; }
        public double textAngle { get; set; }
        public string orientation { get; set; }
        public string modelVersion { get; set; }
        public List<Region> regions { get; set; }
    }
}
