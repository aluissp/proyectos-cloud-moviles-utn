namespace BlogPeliculas.Modelos
{
    public class Personaje
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = null!;

        // Relación con la entidad Pelicula
        public int PeliculaId { get; set; }  // Clave foránea
        public Pelicula Pelicula { get; set; } = null!; // Propiedad de navegación
    }
}
