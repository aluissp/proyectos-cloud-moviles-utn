namespace consume_api_ocr.Models
{
    public class DescribeImageResponse : ComputerVisionInterface
    {
        public Description description { get; set; }
        public string requestId { get; set; }
        public Metadata metadata { get; set; }
        public DateTime modelVersion { get; set; }

        public string ReadText()
        {
            return description.captions.First().text;
        }
    }

    public class Description
    {
        public List<string> tags { get; set; }
        public List<Caption> captions { get; set; }
    }

    public class Caption
    {
        public string text { get; set; }
        public double confidence { get; set; }
    }

    public class Metadata
    {
        public int height { get; set; }
        public int width { get; set; }
        public string format { get; set; }
    }
}
