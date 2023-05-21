using Microsoft.EntityFrameworkCore;
using BlogPeliculas.Modelos;

public class BlogPeliculasContext : DbContext
{
    public BlogPeliculasContext(DbContextOptions<BlogPeliculasContext> options) : base(options)
    {
    }

    public DbSet<Comentario> Comentarios => Set<Comentario>();
    public DbSet<Personaje> Personajes => Set<Personaje>();
    public DbSet<Pelicula> Peliculas => Set<Pelicula>();
}
