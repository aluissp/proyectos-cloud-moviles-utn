using Microsoft.EntityFrameworkCore;
using BlogPeliculas.Modelos;
using BlogPeliculas.API.Data.Seeding;
using System.Reflection;

public class BlogPeliculasContext : DbContext
{
    public BlogPeliculasContext(DbContextOptions<BlogPeliculasContext> options) : base(options)
    {
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        SeedingInicial.Seed(modelBuilder);
    }

    public DbSet<Comentario> Comentarios => Set<Comentario>();
    public DbSet<Personaje> Personajes => Set<Personaje>();
    public DbSet<Pelicula> Peliculas => Set<Pelicula>();
}
