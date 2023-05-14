using System.ComponentModel.DataAnnotations;

namespace EmpresaUTN.Modelos
{
    public class Pais
    {
        public int CodigoPais { get; set; }
        public string Nombre { get; set; } = null!;
        public int Poblacion { get; set; }
        public string CodigoISO { get; set; } = null!;
        public string Moneda { get; set; } = null!;
        public string Capital { get; set; } = null!;
        public string Idioma { get; set; } = null!;


        // Relationships
        public List<Provincia> Provincias { get; set; } = new List<Provincia>();
    }
}