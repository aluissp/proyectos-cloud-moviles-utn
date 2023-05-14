using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EmpresaUTN.Modelos
{
    public class Provincia
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = null!;
        public int Area { get; set; }
        public string ActividadEconomica { get; set; } = null!;


        // Relationships
        public int PaisCodigoPais { get; set; }
        public Pais Pais { get; set; } = null!;
        public List<Canton> Cantones { get; set; } = new List<Canton>();
    }
}
