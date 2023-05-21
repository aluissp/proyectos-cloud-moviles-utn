namespace BlogPeliculas.API.DTOs
{
    public class ComentarioCreacionDTO
    {
        public string? Contenido { get; set; }
        public bool Recomendar { get; set; }

        public int PeliculaId { get; set; }  // Clave foránea
    }
}
