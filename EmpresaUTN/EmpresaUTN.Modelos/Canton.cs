using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EmpresaUTN.Modelos
{
    public class Canton
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = null!;
        public string CabeceraCantonal { get; set; } = null!;

        // Relationships
        public int ProvinciaId { get; set; }
        public Provincia Provincia { get; set; } = null!;
    }
}
