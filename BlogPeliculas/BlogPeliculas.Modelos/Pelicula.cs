namespace BlogPeliculas.Modelos
{
    public class Pelicula
    {
        public int Id { get; set; }
        public string Titulo { get; set; } = null!;
        public bool EnCines { get; set; }
        public DateTime FechaEstreno { get; set; }

        // Relación con la entidad Comentario
        public HashSet<Comentario> Comentarios { get; set; } = new HashSet<Comentario>();
        // Relación con la entidad Personaje
        public HashSet<Personaje> Personjes { get; set; } = new HashSet<Personaje>();
    }
}
