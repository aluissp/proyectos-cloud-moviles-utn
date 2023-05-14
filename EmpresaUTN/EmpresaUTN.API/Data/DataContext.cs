using Microsoft.EntityFrameworkCore;
using EmpresaUTN.Modelos;
using System.Reflection;
using EmpresaUTN.API.Data.Seeding;

public class DataContext : DbContext
{
    public DataContext(DbContextOptions<DataContext> options) : base(options)
    {
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        modelBuilder.ApplyConfigurationsFromAssembly(Assembly.GetExecutingAssembly());

        SeedingInicial.Seed(modelBuilder);
    }

    // Pluralizar nombre a la hora de crear las tablas,
    // ese el nombre que adoptara la tabla en la base de datos
    public DbSet<Pais> Paises => Set<Pais>();
    public DbSet<Canton> Cantones => Set<Canton>();
    public DbSet<Provincia> Provincias => Set<Provincia>();
}
