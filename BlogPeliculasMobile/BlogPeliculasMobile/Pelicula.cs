using System;
using System.Collections.Generic;
using System.Text;

namespace BlogPeliculasMobile
{
    public class Pelicula
    {
        public int Id { get; set; }
        public string Titulo { get; set; } 
        public bool EnCines { get; set; }
        public DateTime FechaEstreno { get; set; }

    }
}
