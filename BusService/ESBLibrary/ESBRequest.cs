namespace ESBLibrary
{
    public class ESBRequest
    {
        public string Usuario { get; set; }
        public DateTime Fecha { get; set; }
        public string Aplicacion { get; set; }
        public string Maquina { get; set; }
        public string Modulo { get; set; }
        public string IdProducto { get; set; }
        public string NombreProducto { get; set; }
        public decimal Cantidad { get; set; }
        public decimal Precio { get; set; }
        public decimal Descuento { get; set; }
        public decimal Iva { get; set; }
    }
}