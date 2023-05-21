using BlogPeliculas.Modelos;
using Microsoft.EntityFrameworkCore;

namespace BlogPeliculas.API.Data.Seeding
{
    public class SeedingInicial
    {
        public static void Seed(ModelBuilder modelBuilder)
        {
            var mario = new Pelicula
            {
                Id = 1,
                Titulo = "Super Mario Bros.: La película",
                EnCines = true,
                FechaEstreno = new DateTime(2023, 04, 05)
            };

            var drStrange = new Pelicula
            {
                Id = 2,
                Titulo = "Doctor Strange: En el multiverso de la locura",
                EnCines = false,
                FechaEstreno = new DateTime(2022, 03, 25)
            };

            var theBatman = new Pelicula
            {
                Id = 3,
                Titulo = "The Batman",
                EnCines = true,
                FechaEstreno = new DateTime(2022, 03, 04)
            };

            var theFlash = new Pelicula
            {
                Id = 4,
                Titulo = "The Flash",
                EnCines = false,
                FechaEstreno = new DateTime(2023, 11, 04)
            };

            var venom = new Pelicula
            {
                Id = 5,
                Titulo = "Venom: Habrá matanza",
                EnCines = true,
                FechaEstreno = new DateTime(2021, 10, 01)
            };

            modelBuilder
                .Entity<Pelicula>()
                .HasData(mario, drStrange, theBatman, theFlash, venom);

            var marioPersonaje = new Personaje
            {
                Id = 1,
                Nombre = "Mario",
                PeliculaId = mario.Id
            };

            var luigiPersonaje = new Personaje
            {
                Id = 2,
                Nombre = "Luigi",
                PeliculaId = mario.Id
            };

            var peachPersonaje = new Personaje
            {
                Id = 3,
                Nombre = "Peach",
                PeliculaId = mario.Id
            };

            var bowserPersonaje = new Personaje
            {
                Id = 4,
                Nombre = "Bowser",
                PeliculaId = mario.Id
            };

            var drStrangePersonaje = new Personaje
            {
                Id = 5,
                Nombre = "Doctor Strange",
                PeliculaId = drStrange.Id
            };

            var brujaEscarlataPersonaje = new Personaje
            {
                Id = 6,
                Nombre = "Bruja Escarlata",
                PeliculaId = drStrange.Id
            };

            var batmanPersonaje = new Personaje
            {
                Id = 7,
                Nombre = "Batman",
                PeliculaId = theBatman.Id
            };

            var flashPersonaje = new Personaje
            {
                Id = 8,
                Nombre = "Flash",
                PeliculaId = theFlash.Id
            };

            var venomPersonaje = new Personaje
            {
                Id = 9,
                Nombre = "Venom",
                PeliculaId = venom.Id
            };

            modelBuilder
                .Entity<Personaje>()
                .HasData(
                marioPersonaje,
                luigiPersonaje,
                peachPersonaje,
                bowserPersonaje,
                drStrangePersonaje,
                brujaEscarlataPersonaje,
                batmanPersonaje,
                flashPersonaje,
                venomPersonaje
                );

            var marioComentario1 = new Comentario
            {
                Id = 1,
                Contenido = "¡Es una película muy divertida!",
                Recomendar = true,
                PeliculaId = mario.Id
            };

            var marioComentario2 = new Comentario
            {
                Id = 2,
                Contenido = "¡Me encantó la trama!",
                Recomendar = true,
                PeliculaId = mario.Id
            };

            var marioComentario3 = new Comentario
            {
                Id = 3,
                Contenido = "¡La volvere a ver!",
                Recomendar = true,
                PeliculaId = mario.Id
            };

            var drStrangeComentario1 = new Comentario
            {
                Id = 4,
                Contenido = "¡Asombroso!",
                Recomendar = true,
                PeliculaId = drStrange.Id
            };

            var drStrangeComentario2 = new Comentario
            {
                Id = 5,
                Contenido = "¡Me encantó!",
                Recomendar = true,
                PeliculaId = drStrange.Id
            };

            var drStrangeComentario3 = new Comentario
            {
                Id = 6,
                Contenido = "Me gusto los personajes pero no la trama.",
                Recomendar = false,
                PeliculaId = drStrange.Id
            };

            var theBatmanComentario1 = new Comentario
            {
                Id = 7,
                Contenido = "¡Es una película muy divertida!",
                Recomendar = true,
                PeliculaId = theBatman.Id
            };

            var theBatmanComentario2 = new Comentario
            {
                Id = 8,
                Contenido = "¡Me encantó la trama!",
                Recomendar = true,
                PeliculaId = theBatman.Id
            };

            var venomComentario1 = new Comentario
            {
                Id = 9,
                Contenido = "¡Es una película muy divertida!",
                Recomendar = true,
                PeliculaId = venom.Id
            };

            modelBuilder
                .Entity<Comentario>()
                .HasData(
                marioComentario1,
                marioComentario2,
                marioComentario3,
                drStrangeComentario1,
                drStrangeComentario2,
                drStrangeComentario3,
                theBatmanComentario1,
                theBatmanComentario2,
                venomComentario1
                );
        }
    }
}
