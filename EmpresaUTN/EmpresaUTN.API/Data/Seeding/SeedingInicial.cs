using EmpresaUTN.Modelos;
using Microsoft.EntityFrameworkCore;

namespace EmpresaUTN.API.Data.Seeding
{
    public class SeedingInicial
    {
        public static void Seed(ModelBuilder modelBuilder)
        {
            var ecuador = new Pais
            {
                CodigoPais = 1,
                Nombre = "Ecuador",
                Poblacion = 17643054,
                CodigoISO = "EC",
                Moneda = "USD",
                Capital = "Quito",
                Idioma = "Español"
            };

            var argentina = new Pais
            {
                CodigoPais = 2,
                Nombre = "Argentina",
                Poblacion = 44938712,
                CodigoISO = "AR",
                Moneda = "ARS",
                Capital = "Buenos Aires",
                Idioma = "Español"
            };

            modelBuilder.Entity<Pais>().HasData(ecuador, argentina);

            var pichincha = new Provincia
            {
                Id = 1,
                Nombre = "Pichincha",
                PaisCodigoPais = ecuador.CodigoPais,
                ActividadEconomica = "Agricultura",
                Area = 10487
            };

            var guayas = new Provincia
            {
                Id = 2,
                Nombre = "Guayas",
                PaisCodigoPais = ecuador.CodigoPais,
                ActividadEconomica = "Pesca",
                Area = 15480
            };

            var carchi = new Provincia
            {
                Id = 3,
                Nombre = "Carchi",
                PaisCodigoPais = ecuador.CodigoPais,
                ActividadEconomica = "Ganaderia",
                Area = 3800
            };

            var imbabura = new Provincia
            {
                Id = 4,
                Nombre = "Imbabura",
                PaisCodigoPais = ecuador.CodigoPais,
                ActividadEconomica = "Turismo",
                Area = 4600
            };

            var buenosAires = new Provincia
            {
                Id = 5,
                Nombre = "Buenos Aires",
                PaisCodigoPais = argentina.CodigoPais,
                ActividadEconomica = "Agricultura",
                Area = 307571
            };

            modelBuilder.Entity<Provincia>().HasData(pichincha, guayas, carchi, imbabura, buenosAires);

            var quito = new Canton
            {
                Id = 1,
                Nombre = "Quito",
                ProvinciaId = pichincha.Id,
                CabeceraCantonal = "Distrito Metropolitano de Quito"
            };

            var guayaquil = new Canton
            {
                Id = 2,
                Nombre = "Guayaquil",
                ProvinciaId = guayas.Id,
                CabeceraCantonal = "Guayaquil"
            };

            var tulcan = new Canton
            {
                Id = 3,
                Nombre = "Tulcan",
                ProvinciaId = carchi.Id,
                CabeceraCantonal = "Tulcan"
            };

            var ibarra = new Canton
            {
                Id = 4,
                Nombre = "Ibarra",
                ProvinciaId = imbabura.Id,
                CabeceraCantonal = "Ibarra"
            };

            var laLibertad = new Canton
            {
                Id = 5,
                Nombre = "La Libertad",
                ProvinciaId = buenosAires.Id,
                CabeceraCantonal = "La Libertad"
            };

            var otavalo = new Canton
            {
                Id = 6,
                Nombre = "Otavalo",
                ProvinciaId = imbabura.Id,
                CabeceraCantonal = "Otavalo"
            };

            var pedroMoncayo = new Canton
            {
                Id = 7,
                Nombre = "Pedro Moncayo",
                ProvinciaId = pichincha.Id,
                CabeceraCantonal = "Tabacundo"
            };

            var quitoSur = new Canton
            {
                Id = 8,
                Nombre = "Quito Sur",
                ProvinciaId = pichincha.Id,
                CabeceraCantonal = "Quito"
            };

            var cotacachi = new Canton
            {
                Id = 9,
                Nombre = "Cotacachi",
                ProvinciaId = imbabura.Id,
                CabeceraCantonal = "Cotacachi"
            };

            modelBuilder.Entity<Canton>().HasData(
                quito,
                guayaquil,
                tulcan,
                ibarra,
                laLibertad,
                otavalo,
                pedroMoncayo,
                quitoSur,
                cotacachi
            );
        }
    }
}
