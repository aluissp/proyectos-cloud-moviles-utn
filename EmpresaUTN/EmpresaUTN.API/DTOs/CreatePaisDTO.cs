using EmpresaUTN.Modelos;

namespace EmpresaUTN.API.DTOs
{
    public class CreatePaisDTO
    {
        public string Nombre { get; set; } = null!;
        public int Poblacion { get; set; }
        public string CodigoISO { get; set; } = null!;
        public string Moneda { get; set; } = null!;
        public string Capital { get; set; } = null!;
        public string Idioma { get; set; } = null!;
    }
}
