namespace consume_api_ocr.Models
{
    public class DescribeImageResponse : ComputerVisionInterface
    {
        private Description description { get; set; }
        private string requestId { get; set; }
        private Metadata metadata { get; set; }
        private DateTime modelVersion { get; set; }

        public string ReadText()
        {
            return description.captions.First().text;
        }
    }

    internal class Description
    {
        public List<string> tags { get; set; }
        public List<Caption> captions { get; set; }
    }

    internal class Caption
    {
        public string text { get; set; }
        public double confidence { get; set; }
    }

    internal class Metadata
    {
        public int height { get; set; }
        public int width { get; set; }
        public string format { get; set; }
    }
}
