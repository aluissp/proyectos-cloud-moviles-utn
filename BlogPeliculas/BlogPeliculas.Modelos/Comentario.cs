namespace BlogPeliculas.Modelos
{
    public class Comentario
    {
        public int Id { get; set; }
        public string? Contenido { get; set; }
        public bool Recomendar { get; set; }

        // Relación con la entidad Pelicula
        public int PeliculaId { get; set; }  // Clave foránea
        public Pelicula Pelicula { get; set; } = null!; // Propiedad de navegación
    }
}
