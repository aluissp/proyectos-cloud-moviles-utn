
using System.Text;

namespace consume_api_ocr.Models
{
    public class OCRResponse: ComputerVisionInterface
    {
        public string language { get; set; }
        public double textAngle { get; set; }
        public string orientation { get; set; }
        public string modelVersion { get; set; }
        public List<Region> regions { get; set; }

        public string ReadText()
        {
            var sb = new StringBuilder();
            foreach (var region in regions)
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
    }
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
}
