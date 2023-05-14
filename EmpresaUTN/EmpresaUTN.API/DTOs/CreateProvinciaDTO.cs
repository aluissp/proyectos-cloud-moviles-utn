using EmpresaUTN.Modelos;

namespace EmpresaUTN.API.DTOs
{
    public class CreateProvinciaDTO
    {
        public string Nombre { get; set; } = null!;
        public int Area { get; set; }
        public string ActividadEconomica { get; set; } = null!;
    }
}
